package my.project.chongieball.footballmatch.feature.detail

import android.database.sqlite.SQLiteConstraintException
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import my.project.chongieball.footballmatch.data.db.DatabaseHelper
import my.project.chongieball.footballmatch.data.db.Favorite
import my.project.chongieball.footballmatch.data.network.Service
import my.project.chongieball.footballmatch.data.network.response.events.DataEventResponse
import my.project.chongieball.footballmatch.data.network.response.team.DataTeamResponse
import my.project.chongieball.footballmatch.feature.base.BasePresenter
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import javax.inject.Inject

class DetailPresenterImpl @Inject constructor(val service: Service, val dbHelper: DatabaseHelper) : BasePresenter<DetailView>(),
        DetailPresenter {

    lateinit var dataEvent : DataEventResponse
    private var urlImageHome: String = ""
    private var stadiumNameAndTime: String = ""
    private var typeMatchP: Int = 0

    override fun getDetail(data: DataEventResponse, typeMatch: Int) {
        getView()?.showLoading()
        dataEvent = data
        typeMatchP = typeMatch

        //change every ';' to newline ('\n')
        if (typeMatch == 0) {
            dataEvent.homeGoalDetails?.replace(';', '\n')
            dataEvent.homeYellowCards?.replace(';', '\n')
            dataEvent.homeRedCards?.replace(';', '\n')
            dataEvent.awayGoalDetails?.replace(';', '\n')
            dataEvent.awayYellowCards?.replace(';', '\n')
            dataEvent.awayRedCards?.replace(';', '\n')
        }

        //call api team home and away to get image and stadium
        callHomeTeam(data.idHomeTeam, data.idAwayTeam)
    }

    private fun callHomeTeam(idHomeTeam: String?, idAwayTeam: String?) {
        service.getTeamDetails(idHomeTeam)
                .map {
                    team -> team.teams
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                    {data -> callAwayTeam(data, idAwayTeam)},
                    {error ->
                        run {
                            getView()?.hideLoading()
                            getView()?.onError(error.message)
                        }
                    }
                )
    }

    private fun callAwayTeam(data: List<DataTeamResponse>?, idAwayTeam: String?) {
        urlImageHome = data?.get(0)?.teamBadge!!
        stadiumNameAndTime = """${data[0].stadiumName}, ${dataEvent.strTime?.substring(0, 5)}"""

        service.getTeamDetails(idAwayTeam)
                .map {
                    team -> team.teams
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { data ->
                            run{
                                getView()?.hideLoading()
                                getView()?.showDetail(dataEvent, urlImageHome, data?.get(0)?.teamBadge, stadiumNameAndTime)
                            }
                        },
                        {error ->
                            run {
                                getView()?.hideLoading()
                                getView()?.onError(error.message)
                            }
                        }
                )
    }

    override fun insertToDb(event: DataEventResponse, typeMatch: Int) {
        try {
            dbHelper.use {
                insert(Favorite.TABLE_FAVORITE,
                        Favorite.EVENT_ID to event.idEvent,
                        Favorite.TYPE_MATCH to typeMatch,
                        Favorite.EVENT_DATE to event.strDateEvent,
                        Favorite.EVENT_TIME to event.strTime,
                        Favorite.HOME_TEAM_NAME to event.homeTeamName,
                        Favorite.HOME_SCORE to event.homeScore,
                        Favorite.HOME_GOAL_DETAILS to event.homeGoalDetails,
                        Favorite.HOME_RED_CARDS to event.homeRedCards,
                        Favorite.HOME_YELLOW_CARDS to event.homeYellowCards,
                        Favorite.HOME_FORMATION to event.homeFormation,
                        Favorite.ID_HOME to event.idHomeTeam,
                        Favorite.AWAY_TEAM_NAME to event.awayTeamName,
                        Favorite.AWAY_SCORE to event.awayScore,
                        Favorite.AWAY_GOAL_DETAILS to event.awayGoalDetails,
                        Favorite.AWAY_RED_CARDS to event.awayRedCards,
                        Favorite.AWAY_YELLOW_CARDS to event.awayYellowCards,
                        Favorite.AWAY_FORMATION to event.awayFormation,
                        Favorite.ID_AWAY to event.idAwayTeam
                        )
            }
            getView()?.onTransactionDb("Added to favorite")
        } catch (e: SQLiteConstraintException) {
            getView()?.onTransactionDb(e.localizedMessage)
        }
    }

    override fun removeToDb(id: String) {
        try {
            dbHelper.use {
                delete(Favorite.TABLE_FAVORITE, "(EVENT_ID = {id})",
                        "id" to id)
                getView()?.onTransactionDb("Removed to favorite")
            }
        } catch (e: SQLiteConstraintException) {
            getView()?.onTransactionDb(e.localizedMessage)
        }
    }

    override fun checkIsFavorite(id: String) {
        dbHelper.use {
            val result = select(Favorite.TABLE_FAVORITE)
                    .whereArgs("(EVENT_ID = {id})",
                            "id" to id)
            val favorite = result.parseList(classParser<Favorite>())
            getView()?.isFavorite(!favorite.isEmpty())
        }
    }
}
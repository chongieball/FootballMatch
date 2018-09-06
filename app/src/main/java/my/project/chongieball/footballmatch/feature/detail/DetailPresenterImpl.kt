package my.project.chongieball.footballmatch.feature.detail

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import my.project.chongieball.footballmatch.data.network.Service
import my.project.chongieball.footballmatch.data.network.response.events.DataEventResponse
import my.project.chongieball.footballmatch.data.network.response.team.DataTeamResponse
import my.project.chongieball.footballmatch.feature.base.BasePresenter
import javax.inject.Inject

class DetailPresenterImpl @Inject constructor(val service: Service) : BasePresenter<DetailView>(),
        DetailPresenter {

    lateinit var dataEvent : DataEventResponse
    private var urlImageHome: String = ""
    private var urlImageAway: String = ""
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
}
package my.project.chongieball.footballmatch.feature.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_detail.*
import my.project.chongieball.footballmatch.R
import my.project.chongieball.footballmatch.data.network.response.events.DataEventResponse
import my.project.chongieball.footballmatch.utils.GlideApp
import my.project.chongieball.footballmatch.utils.TimeUtils
import my.project.chongieball.footballmatch.utils.invisible
import my.project.chongieball.footballmatch.utils.visible
import org.jetbrains.anko.alert
import javax.inject.Inject

/**
 * Created by chongieball on 06/09/18.
 */
class DetailActivity : AppCompatActivity(), DetailView {

    @Inject
    lateinit var presenter: DetailPresenter

    private lateinit var dataEvent: DataEventResponse
    private var typeMatch: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        dataEvent = intent.getParcelableExtra("data")
        typeMatch = intent.getIntExtra("type_match", 0)

        presenter.setView(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.getDetail(dataEvent, typeMatch)
    }

    override fun showLoading() {
        cvGoalDetails.invisible()
        cvMatchDetail.invisible()
        pbDetail.visible()
    }

    override fun hideLoading() {
        cvGoalDetails.visible()
        cvMatchDetail.visible()
        pbDetail.invisible()
    }

    override fun showDetail(matchDetail: DataEventResponse, urlImageHome: String?, urlImageAway: String?, stadiumNameAndTime: String?) {
        if (typeMatch == 1) cvMatchDetail.invisible()
        else cvMatchDetail.visible()

        tvDate.text = TimeUtils.formatDate(TimeUtils.formatGMT(matchDetail.strDateEvent))
        tvStadiumTime.text = stadiumNameAndTime
        //home section
        GlideApp.with(this)
                .load(urlImageHome)
                .into(ivHome)
        tvTeamHomeName.text = matchDetail.homeTeamName
        tvHomeFormation.text = matchDetail.homeFormation
        tvTeamHomeScore.text = matchDetail.homeScore
        tvGoalHomeDetails.text = matchDetail.homeGoalDetails
        tvYellowHomeDetails.text = matchDetail.homeYellowCards
        tvRedHomeDetails.text = matchDetail.homeRedCards

        //away section
        GlideApp.with(this)
                .load(urlImageAway)
                .into(ivAway)
        tvTeamAwayName.text = matchDetail.awayTeamName
        tvAwayFormation.text = matchDetail.awayFormation
        tvTeamAwayScore.text = matchDetail.awayScore
        tvGoalAwayDetails.text = matchDetail.awayGoalDetails
        tvYellowAwayDetails.text = matchDetail.awayYellowCards
        tvRedAwayDetails.text = matchDetail.awayRedCards

    }

    override fun onError(message: String?) {
        alert(message.toString()).show()
    }
}
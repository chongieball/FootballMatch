package my.project.chongieball.footballmatch.feature.home.match

import my.project.chongieball.footballmatch.data.network.response.events.DataEventResponse
import my.project.chongieball.footballmatch.feature.base.BaseView

/**
 * Created by chongieball on 05/09/18.
 */
interface MatchView : BaseView {

    fun showMatch(matchsResponse: List<DataEventResponse>, typeMatch: Int)
}
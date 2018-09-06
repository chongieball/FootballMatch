package my.project.chongieball.footballmatch.feature.detail

import my.project.chongieball.footballmatch.data.network.response.events.DataEventResponse
import my.project.chongieball.footballmatch.feature.base.BaseView

/**
 * Created by chongieball on 06/09/18.
 */
interface DetailView : BaseView {

    fun showDetail(matchDetail: DataEventResponse, urlImageHome: String?, urlImageAway: String?,
                   stadiumNameAndTime: String?)
}
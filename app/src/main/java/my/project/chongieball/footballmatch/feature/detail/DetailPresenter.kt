package my.project.chongieball.footballmatch.feature.detail

import my.project.chongieball.footballmatch.data.network.response.events.DataEventResponse
import my.project.chongieball.footballmatch.feature.base.Presenter

/**
 * Created by chongieball on 06/09/18.
 */
interface DetailPresenter : Presenter<DetailView> {

    fun getDetail(data: DataEventResponse, typeMatch: Int)

    fun insertToDb(event: DataEventResponse, typeMatch: Int)

    fun removeToDb(id: String)

    fun checkIsFavorite(id: String)
}
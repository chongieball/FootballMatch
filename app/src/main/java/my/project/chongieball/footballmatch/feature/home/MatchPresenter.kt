package my.project.chongieball.footballmatch.feature.home

import my.project.chongieball.footballmatch.feature.base.Presenter

/**
 * Created by chongieball on 05/09/18.
 */
interface MatchPresenter : Presenter<MatchView> {

    fun getMatch(typeMatch: Int)
}
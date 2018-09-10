package my.project.chongieball.footballmatch.feature.home.favorite

import my.project.chongieball.footballmatch.feature.base.Presenter

/**
 * Created by chongieball on 10/09/18.
 */
interface FavoritePresenter : Presenter<FavoriteView> {

    fun showFavorites()
}
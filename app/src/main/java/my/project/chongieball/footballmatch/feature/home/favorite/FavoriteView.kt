package my.project.chongieball.footballmatch.feature.home.favorite

import my.project.chongieball.footballmatch.data.db.Favorite
import my.project.chongieball.footballmatch.feature.base.BaseView

/**
 * Created by chongieball on 09/09/18.
 */
interface FavoriteView : BaseView {

    fun showFavorites(favorites: List<Favorite>)

    fun onTransactionDb(message: String)

}
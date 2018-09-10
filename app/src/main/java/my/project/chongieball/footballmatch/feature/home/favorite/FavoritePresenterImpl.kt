package my.project.chongieball.footballmatch.feature.home.favorite

import my.project.chongieball.footballmatch.data.db.DatabaseHelper
import my.project.chongieball.footballmatch.data.db.Favorite
import my.project.chongieball.footballmatch.feature.base.BasePresenter
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import javax.inject.Inject

class FavoritePresenterImpl @Inject constructor(private val dbHelper: DatabaseHelper) :
        BasePresenter<FavoriteView>(), FavoritePresenter {

    override fun showFavorites() {
        getView()?.hideLoading()
        try {
            dbHelper.use {
                val result = select(Favorite.TABLE_FAVORITE)
                val favorite = result.parseList(classParser<Favorite>())
                getView()?.showFavorites(favorite)
            }
        } catch (e: Exception) {
            getView()?.onTransactionDb(e.localizedMessage)
        }
    }
}
package my.project.chongieball.footballmatch.feature.home.favorite

import dagger.Module
import dagger.Provides
import my.project.chongieball.footballmatch.data.db.DatabaseHelper

/**
 * Created by chongieball on 10/09/18.
 */
@Module
class FavoriteModule {

    @Provides
    fun providePresenter(dbHelper: DatabaseHelper) : FavoritePresenter = FavoritePresenterImpl(dbHelper)
}
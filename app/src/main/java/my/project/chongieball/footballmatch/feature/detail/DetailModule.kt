package my.project.chongieball.footballmatch.feature.detail

import dagger.Module
import dagger.Provides
import my.project.chongieball.footballmatch.data.db.DatabaseHelper
import my.project.chongieball.footballmatch.data.network.Service

/**
 * Created by chongieball on 06/09/18.
 */
@Module
class DetailModule {

    @Provides
    fun providePresenter(service: Service, dbHelper: DatabaseHelper) : DetailPresenter = DetailPresenterImpl(service,
            dbHelper)
}
package my.project.chongieball.footballmatch.feature.detail

import dagger.Module
import dagger.Provides
import my.project.chongieball.footballmatch.data.network.Service

/**
 * Created by chongieball on 06/09/18.
 */
@Module
class DetailModule {

    @Provides
    fun providePresenter(service: Service) : DetailPresenter = DetailPresenterImpl(service)
}
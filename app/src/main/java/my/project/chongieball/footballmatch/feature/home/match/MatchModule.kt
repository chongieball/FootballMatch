package my.project.chongieball.footballmatch.feature.home.match

import dagger.Module
import dagger.Provides
import my.project.chongieball.footballmatch.data.network.Service

/**
 * Created by chongieball on 05/09/18.
 */
@Module
class MatchModule {

    @Provides
    fun providePresenter(service: Service) : MatchPresenter = MatchPresenterImpl(service)
}
package my.project.chongieball.footballmatch.injection.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * Created by chongieball on 04/09/18.
 */
@Module
class AppModule {

    @Provides
    fun provideContext(application: Application) : Context = application
}
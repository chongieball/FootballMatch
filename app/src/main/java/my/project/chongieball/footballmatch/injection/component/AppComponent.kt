package my.project.chongieball.footballmatch.injection.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import my.project.chongieball.footballmatch.App
import my.project.chongieball.footballmatch.injection.module.ActivityBuilder
import my.project.chongieball.footballmatch.injection.module.AppModule
import my.project.chongieball.footballmatch.injection.module.NetworkModule
import javax.inject.Singleton

/**
 * Created by chongieball on 04/09/18.
 */
@Singleton
@Component(modules = arrayOf(ActivityBuilder::class, AppModule::class, NetworkModule::class,
        AndroidInjectionModule::class, AndroidSupportInjectionModule::class))
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun app(application: Application): Builder
        fun build() : AppComponent
    }

    abstract fun Inject(app: App)
}
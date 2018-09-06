package my.project.chongieball.footballmatch

import android.app.Activity
import android.app.Application
import android.support.v4.app.Fragment
import dagger.android.*
import dagger.android.support.HasSupportFragmentInjector
import my.project.chongieball.footballmatch.injection.component.AppComponent
import my.project.chongieball.footballmatch.injection.component.DaggerAppComponent
import javax.inject.Inject

/**
 * Created by chongieball on 04/09/18.
 */
class App : Application(), HasActivityInjector, HasSupportFragmentInjector{

    companion object {
        @JvmStatic lateinit var appComponent: AppComponent
    }

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var supportInjector: DispatchingAndroidInjector<android.support.v4.app.Fragment>

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().app(this).build()
        appComponent.Inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = supportInjector
}
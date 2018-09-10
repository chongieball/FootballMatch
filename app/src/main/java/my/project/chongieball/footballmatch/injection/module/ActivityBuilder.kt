package my.project.chongieball.footballmatch.injection.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import my.project.chongieball.footballmatch.feature.detail.DetailActivity
import my.project.chongieball.footballmatch.feature.detail.DetailModule
import my.project.chongieball.footballmatch.feature.home.favorite.FavoriteFragment
import my.project.chongieball.footballmatch.feature.home.favorite.FavoriteModule
import my.project.chongieball.footballmatch.feature.home.match.MatchFragment
import my.project.chongieball.footballmatch.feature.home.match.MatchModule

/**
 * Created by chongieball on 04/09/18.
 */
@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MatchModule::class])
    abstract fun bindMatchFragment(): MatchFragment

    @ContributesAndroidInjector(modules = [DetailModule::class])
    abstract fun bindDetailActivity() : DetailActivity

    @ContributesAndroidInjector(modules = [FavoriteModule::class])
    abstract fun bindFavoriteFragment() : FavoriteFragment
}
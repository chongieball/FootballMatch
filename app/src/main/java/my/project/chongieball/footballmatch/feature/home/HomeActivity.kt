package my.project.chongieball.footballmatch.feature.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import my.project.chongieball.footballmatch.R
import kotlinx.android.synthetic.main.activity_main.*
import my.project.chongieball.footballmatch.feature.home.favorite.FavoriteFragment
import my.project.chongieball.footballmatch.feature.home.match.MatchFragment
import org.jetbrains.anko.design.coroutines.onTabSelectedListener

/**
 * Created by chongieball on 05/09/18.
 */
class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(MatchFragment.newInstance(0))
        initTabListener()
    }

    private fun initTabListener() {
        tl.onTabSelectedListener {
            onTabSelected {
                if (it?.position == 0) replaceFragment(MatchFragment.newInstance(0))
                else if (it?.position == 1) replaceFragment(MatchFragment.newInstance(1))
                else replaceFragment(FavoriteFragment.newInstance())
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.flMatch, fragment)
                .commitAllowingStateLoss()
    }
}
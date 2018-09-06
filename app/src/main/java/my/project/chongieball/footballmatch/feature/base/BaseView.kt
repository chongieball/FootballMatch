package my.project.chongieball.footballmatch.feature.base

/**
 * Created by chongieball on 05/09/18.
 */
interface BaseView {

    fun showLoading()
    fun hideLoading()
    fun onError(message: String?)
}
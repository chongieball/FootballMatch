package my.project.chongieball.footballmatch.feature.base

/**
 * Created by chongieball on 05/09/18.
 */
interface Presenter<V : BaseView> {

    fun setView(view: V)
    fun getView() : V?
    fun destroyView()
}
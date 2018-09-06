package my.project.chongieball.footballmatch.feature.base

/**
 * Created by chongieball on 05/09/18.
 */
open class BasePresenter<V : BaseView> : Presenter<V> {

    var mView: V? = null

    override fun setView(view: V) {
        mView = view
    }

    override fun getView(): V? {
        return mView
    }

    override fun destroyView() {
        mView = null
    }
}
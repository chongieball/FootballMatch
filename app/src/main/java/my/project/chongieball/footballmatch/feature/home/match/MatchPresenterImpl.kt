package my.project.chongieball.footballmatch.feature.home.match

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import my.project.chongieball.footballmatch.data.network.Service
import my.project.chongieball.footballmatch.data.network.response.events.DataEventResponse
import my.project.chongieball.footballmatch.data.network.response.events.EventResponse
import my.project.chongieball.footballmatch.feature.base.BasePresenter
import javax.inject.Inject

class MatchPresenterImpl @Inject constructor(val service: Service): BasePresenter<MatchView>(), MatchPresenter {

    override fun getMatch(typeMatch: Int) {
        getView()?.showLoading()
        var getEvents : Observable<EventResponse> = if (typeMatch == 0) service.getPastEvents() else service.getNextEvent()

        getEvents.map {
            t: EventResponse -> t.events
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {data -> showMatch(data,typeMatch)},
                        {error ->
                            run {
                                getView()?.hideLoading()
                                getView()?.onError(error.message)
                            }
                        }
                )

    }

    private fun showMatch(data: List<DataEventResponse>, typeMatch: Int) {
        getView()?.hideLoading()
        getView()?.showMatch(data, typeMatch)
    }
}
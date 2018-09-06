package my.project.chongieball.footballmatch.feature.home

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_match.*
import my.project.chongieball.footballmatch.R
import my.project.chongieball.footballmatch.data.network.response.events.DataEventResponse
import my.project.chongieball.footballmatch.feature.detail.DetailActivity
import my.project.chongieball.footballmatch.utils.TimeUtils
import my.project.chongieball.footballmatch.utils.invisible
import my.project.chongieball.footballmatch.utils.visible
import net.idik.lib.slimadapter.SlimAdapter
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.alert
import javax.inject.Inject

/**
 * Created by chongieball on 05/09/18.
 */
class MatchFragment : Fragment(), MatchView {

    @Inject
    lateinit var presenter: MatchPresenter

    private var typeMatch = 0
    private var matchs: MutableList<DataEventResponse> = mutableListOf()

    private lateinit var adapter : SlimAdapter

    companion object {

        fun newInstance(typeMatch: Int) : MatchFragment {
            val args = Bundle()
            args.putInt("type_match", typeMatch)

            val fragment = MatchFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        typeMatch = arguments!!.getInt("type_match", 0)
    }

    override fun onResume() {
        super.onResume()
        presenter.setView(this)
        presenter.getMatch(typeMatch)
    }

    override fun onPause() {
        super.onPause()
        presenter.destroyView()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.fragment_match, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        srlMatch.setOnRefreshListener {
            presenter.getMatch(typeMatch)
        }

        setRecyclerView()
    }

    private fun setRecyclerView() {
        rvMatch.layoutManager = LinearLayoutManager(context)
        adapter = SlimAdapter.create()
                .register<DataEventResponse>(R.layout.item_match) { data, injector ->
                    injector.text(R.id.tvDate, TimeUtils.formatDate(TimeUtils.formatGMT(data.strDateEvent)))
                            .text(R.id.tvTeamHomeName, data.homeTeamName)
                            .text(R.id.tvTeamHomeScore, data.homeScore)
                            .text(R.id.tvTeamAwayScore, data.awayScore)
                            .text(R.id.tvTeamAwayName, data.awayTeamName)
                            .clicked(R.id.cvMatch, View.OnClickListener {
                                context?.startActivity<DetailActivity>(
                                        "data" to data,
                                        "type_match" to typeMatch
                                )
                            })
                }.attachTo(rvMatch)
        adapter.updateData(matchs)
    }

    override fun showLoading() {
        pbMatch.visible()
    }

    override fun hideLoading() {
        pbMatch.invisible()
    }

    override fun showMatch(matchsResponse: List<DataEventResponse>, typeMatch: Int) {
        srlMatch.isRefreshing = false
        matchs.clear()
        matchs.addAll(matchsResponse)
        adapter.updateData(matchsResponse)
    }

    override fun onError(message: String?) {
        alert(message.toString()).show()
    }
}
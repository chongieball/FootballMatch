package my.project.chongieball.footballmatch.feature.home.favorite

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
import my.project.chongieball.footballmatch.data.db.Favorite
import my.project.chongieball.footballmatch.data.network.response.events.DataEventResponse
import my.project.chongieball.footballmatch.feature.detail.DetailActivity
import my.project.chongieball.footballmatch.utils.TimeUtils
import my.project.chongieball.footballmatch.utils.invisible
import net.idik.lib.slimadapter.SlimAdapter
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.startActivity
import javax.inject.Inject

/**
 * Created by chongieball on 10/09/18.
 */
class FavoriteFragment : Fragment(), FavoriteView {

    @Inject
    lateinit var presenter: FavoritePresenter

    private var favorites: MutableList<Favorite> = mutableListOf()

    private lateinit var adapter : SlimAdapter

    companion object {
        fun newInstance() : FavoriteFragment {
            val args = Bundle()

            val fragment = FavoriteFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onResume() {
        super.onResume()
        presenter.setView(this)
        presenter.showFavorites()
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

        setRecyclerView()
    }

    private fun setRecyclerView() {
        rvMatch.layoutManager = LinearLayoutManager(context)
        adapter = SlimAdapter.create()
                .register<Favorite>(R.layout.item_match) { data, injector ->
                    injector.text(R.id.tvDate, TimeUtils.formatDate(TimeUtils.formatGMT(data.dateEvent)))
                            .text(R.id.tvTeamHomeName, data.homeTeamName)
                            .text(R.id.tvTeamHomeScore, data.homeScore)
                            .text(R.id.tvTeamAwayScore, data.awayScore)
                            .text(R.id.tvTeamAwayName, data.awayTeamName)
                            .clicked(R.id.cvMatch, View.OnClickListener {
                                parseAndGoToDetail(data)
                            })
                }.attachTo(rvMatch)
        adapter.updateData(favorites)
    }

    private fun parseAndGoToDetail(data: Favorite) {
        val typeMatch = data.typeMatch
        val match = DataEventResponse()
        match.idEvent = data.eventId
        match.strDateEvent = data.dateEvent
        match.strTime = data.timeEvent
        match.homeTeamName = data.homeTeamName
        match.homeScore = data.homeScore
        match.homeGoalDetails = data.homeGoalDetails
        match.homeRedCards = data.homeRedCards
        match.homeYellowCards = data.homeYellowCards
        match.homeFormation = data.homeFormation
        match.idHomeTeam = data.idHome
        match.awayTeamName = data.awayTeamName
        match.awayScore = data.awayScore
        match.awayGoalDetails = data.awayGoalDetails
        match.awayRedCards = data.awayRedCards
        match.awayYellowCards = data.awayYellowCards
        match.awayFormation = data.awayFormation
        match.idAwayTeam = data.idAway

        context?.startActivity<DetailActivity>(
                "data" to match,
                "type_match" to typeMatch
        )
    }

    override fun showLoading() {

    }

    override fun hideLoading() {
        pbMatch.invisible()
    }

    override fun onError(message: String?) {

    }

    override fun onTransactionDb(message: String) {
        snackbar(rvMatch, message).show()
    }

    override fun showFavorites(favorites: List<Favorite>) {
        this.favorites.clear()
        this.favorites.addAll(favorites)
        adapter.updateData(favorites)
    }
}
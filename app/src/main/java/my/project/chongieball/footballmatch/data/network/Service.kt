package my.project.chongieball.footballmatch.data.network

import io.reactivex.Observable
import my.project.chongieball.footballmatch.data.network.response.events.EventResponse
import my.project.chongieball.footballmatch.data.network.response.team.TeamResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by chongieball on 04/09/18.
 */
interface Service {

    @GET("https://www.thesportsdb.com/api/v1/json/1/eventspastleague.php?id=4328")
    fun getPastEvents() : Observable<EventResponse>

    @GET("https://www.thesportsdb.com/api/v1/json/1/eventsnextleague.php?id=4328")
    fun getNextEvent() : Observable<EventResponse>

    @GET("https://www.thesportsdb.com/api/v1/json/1/lookupteam.php")
    fun getTeamDetails(@Query("id") idTeam: String?) : Observable<TeamResponse>
}
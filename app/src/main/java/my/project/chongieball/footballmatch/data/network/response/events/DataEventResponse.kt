package my.project.chongieball.footballmatch.data.network.response.events

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by chongieball on 05/09/18.
 */
@Parcelize
data class DataEventResponse(
        @SerializedName("idEvent")
        var idEvent: String? = "Not Found",

        @SerializedName("strEvent")
        var eventName: String? = "Not Found",

        @SerializedName("strHomeTeam")
        var homeTeamName: String? = "Not Found",

        @SerializedName("strAwayTeam")
        var awayTeamName: String? = "Not Found",

        @SerializedName("intHomeScore")
        var homeScore: String? = null,

        @SerializedName("intAwayScore")
        var awayScore: String? = null,

        @SerializedName("strHomeGoalDetails")
        var homeGoalDetails: String? = "",

        @SerializedName("strHomeRedCards")
        var homeRedCards: String? = "",

        @SerializedName("strHomeYellowCards")
        var homeYellowCards: String? = "",

        @SerializedName("strHomeFormation")
        var homeFormation: String? = "Not Found",

        @SerializedName("strAwayGoalDetails")
        var awayGoalDetails: String? = "",

        @SerializedName("strAwayRedCards")
        var awayRedCards: String? = "",

        @SerializedName("strAwayYellowCards")
        var awayYellowCards: String? = "",

        @SerializedName("strAwayFormation")
        var awayFormation: String? = "Not Found",

        @SerializedName("dateEvent")
        var dateEvent: String? = "Not Found",

        @SerializedName("strDate")
        var strDateEvent: String? = "Not Found",

        @SerializedName("strTime")
        var strTime: String? = "Not Found",

        @SerializedName("idHomeTeam")
        var idHomeTeam: String? = null,

        @SerializedName("idAwayTeam")
        var idAwayTeam: String? = null
) : Parcelable
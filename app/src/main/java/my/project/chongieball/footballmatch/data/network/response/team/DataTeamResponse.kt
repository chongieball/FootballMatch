package my.project.chongieball.footballmatch.data.network.response.team

import com.google.gson.annotations.SerializedName

/**
 * Created by chongieball on 05/09/18.
 */
data class DataTeamResponse(
        @SerializedName("strStadium")
        var stadiumName: String = "Not Found",

        @SerializedName("strTeamBadge")
        var teamBadge: String = "",

        @SerializedName("strTeamJersey")
        var teamJersey: String? = null,

        @SerializedName("strTeamLogo")
        var teamLogo: String? = null
)
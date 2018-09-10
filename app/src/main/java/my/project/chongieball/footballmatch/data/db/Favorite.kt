package my.project.chongieball.footballmatch.data.db

/**
 * Created by chongieball on 09/09/18.
 */
data class Favorite(val id: Long?, val typeMatch: Int, val eventId: String, val dateEvent: String?,
                    val timeEvent: String?, val homeTeamName: String?, val homeScore: String?,
                    val homeGoalDetails: String?, val homeRedCards: String?, val homeYellowCards: String?,
                    val homeFormation: String?, val idHome: String?, val awayTeamName: String?, val awayScore: String?,
                    val awayGoalDetails: String?, val awayRedCards: String?, val awayYellowCards: String?,
                    val awayFormation: String?, val idAway: String?) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID"
        const val TYPE_MATCH = "TYPE_MATCH"
        const val EVENT_ID = "EVENT_ID"
        const val EVENT_DATE = "EVENT_DATE"
        const val EVENT_TIME = "EVENT_TIME"
        const val HOME_TEAM_NAME = "HOME_TEAM_NAME"
        const val HOME_SCORE = "HOME_SCORE"
        const val HOME_GOAL_DETAILS = "HOME_GOAL_DETAILS"
        const val HOME_RED_CARDS = "HOME_RED_CARDS"
        const val HOME_YELLOW_CARDS = "HOME_YELLOW_CARDS"
        const val HOME_FORMATION = "HOME_FORMATION"
        const val ID_HOME = "ID_HOME"
        const val AWAY_TEAM_NAME = "AWAY_TEAM_NAME"
        const val AWAY_SCORE = "AWAY_SCORE"
        const val AWAY_GOAL_DETAILS = "AWAY_GOAL_DETAILS"
        const val AWAY_RED_CARDS = "AWAY_RED_CARDS"
        const val AWAY_YELLOW_CARDS = "AWAY_YELLOW_CARDS"
        const val AWAY_FORMATION = "AWAY_FORMATION"
        const val ID_AWAY = "ID_AWAY"
    }
}


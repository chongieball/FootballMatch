package my.project.chongieball.footballmatch.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*
import javax.inject.Inject

/**
 * Created by chongieball on 09/09/18.
 */
class DatabaseHelper @Inject constructor(context: Context) : ManagedSQLiteOpenHelper(context, "FavoriteTeam.db", null, 4) {

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable("TABLE_FAVORITE", true,
                "ID" to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                "TYPE_MATCH" to INTEGER,
                "EVENT_ID" to TEXT + UNIQUE,
                "EVENT_DATE" to TEXT,
                "EVENT_TIME" to TEXT,
                "HOME_TEAM_NAME" to TEXT,
                "HOME_SCORE" to TEXT,
                "HOME_GOAL_DETAILS" to TEXT,
                "HOME_RED_CARDS" to TEXT,
                "HOME_YELLOW_CARDS" to TEXT,
                "HOME_FORMATION" to TEXT,
                "ID_HOME" to TEXT,
                "AWAY_TEAM_NAME" to TEXT,
                "AWAY_SCORE" to TEXT,
                "AWAY_GOAL_DETAILS" to TEXT,
                "AWAY_RED_CARDS" to TEXT,
                "AWAY_YELLOW_CARDS" to TEXT,
                "AWAY_FORMATION" to TEXT,
                "ID_AWAY" to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable("TABLE_FAVORITE", true)
    }
}
package com.kucingselfie.kadesubmission.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.kucingselfie.kadesubmission.model.LastMatchFavorite
import com.kucingselfie.kadesubmission.model.NextMatchFavorite
import com.kucingselfie.kadesubmission.model.TeamFavorite
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteTeams.db", null, 2) {

    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context) : MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }
    override fun onCreate(db: SQLiteDatabase?) {
        //Create table
        db?.createTable(
            NextMatchFavorite.TABLE_NEXT_MATCH_FAVORITE,
            true,
            NextMatchFavorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            NextMatchFavorite.MATCH_ID to TEXT + UNIQUE,
            NextMatchFavorite.MATCH_NAME to TEXT,
            NextMatchFavorite.MATCH_PICTURE to TEXT,
            NextMatchFavorite.MATCH_TIME to TEXT,
            NextMatchFavorite.HOME_TEAM_ID to TEXT,
            NextMatchFavorite.AWAY_TEAM_ID to TEXT
        )

        db?.createTable(
            LastMatchFavorite.TABLE_LAST_MATCH_FAVORITE,
            true,
            LastMatchFavorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            LastMatchFavorite.MATCH_ID to TEXT + UNIQUE,
            LastMatchFavorite.MATCH_NAME to TEXT,
            LastMatchFavorite.MATCH_PICTURE to TEXT,
            LastMatchFavorite.MATCH_TIME to TEXT,
            LastMatchFavorite.HOME_TEAM_ID to TEXT,
            LastMatchFavorite.AWAY_TEAM_ID to TEXT
        )

        db?.createTable(
            TeamFavorite.TABLE_TEAM_FAVORITE,
            true,
            TeamFavorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            TeamFavorite.TEAM_ID to TEXT + UNIQUE,
            TeamFavorite.TEAM_NAME to TEXT,
            TeamFavorite.TEAM_BADGE to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //Upgrade table
        db?.dropTable(NextMatchFavorite.TABLE_NEXT_MATCH_FAVORITE, true)
        db?.dropTable(LastMatchFavorite.TABLE_LAST_MATCH_FAVORITE, true)
        db?.dropTable(TeamFavorite.TABLE_TEAM_FAVORITE, true)
    }
}

//Access property for context
val Context.database: MyDatabaseOpenHelper
get() = MyDatabaseOpenHelper.getInstance(applicationContext)
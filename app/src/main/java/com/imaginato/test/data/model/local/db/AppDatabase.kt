package com.imaginato.test.data.model.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.imaginato.test.util.DatabaseConstant

/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 2/1/21
 */

@Database(entities = [User::class], version = DatabaseConstant.DB_VERSION, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
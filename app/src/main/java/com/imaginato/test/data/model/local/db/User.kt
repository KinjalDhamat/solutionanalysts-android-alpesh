package com.imaginato.test.data.model.local.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.imaginato.test.util.DatabaseConstant

/**
 * Purpose -
 *
 * @author Alpesh Rathod
 *
 * Created on 2/1/21
 */
@Entity(tableName = DatabaseConstant.TABLE_USER)
data class User(
    @PrimaryKey
    val user_id: Long,
    val user_name: String,
    val x_acc: String
)
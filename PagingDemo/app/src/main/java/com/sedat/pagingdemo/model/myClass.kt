package com.sedat.pagingdemo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PAGING_ROOM")
data class myClass(
        @ColumnInfo(name = "ID")
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        @ColumnInfo(name = "NUM")
        val number: Int
)
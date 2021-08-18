package com.sedat.pagingdemo.room

import androidx.paging.PagingSource
import androidx.room.*
import androidx.room.Dao
import com.sedat.pagingdemo.model.myClass
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun SaveData(vararg data: myClass)

    @Query("DELETE FROM PAGING_ROOM")
    suspend fun Delete()

    @Query("SELECT * FROM PAGING_ROOM")
    suspend fun GetAllData(): List<myClass>

    @Query("SELECT * FROM PAGING_ROOM")
    fun GetAllData2(): PagingSource<Int, myClass>
}
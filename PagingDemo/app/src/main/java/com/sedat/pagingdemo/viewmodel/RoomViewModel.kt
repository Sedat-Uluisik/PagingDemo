package com.sedat.pagingdemo.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sedat.pagingdemo.model.myClass
import com.sedat.pagingdemo.paging.RoomPagingSource
import com.sedat.pagingdemo.room.Dao
import com.sedat.pagingdemo.room.Database
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(
    private val dao: Dao,
    @ApplicationContext private val application: Context
): BaseViewModel(application as Application) {

    fun SaveData(myClassList: List<myClass>){
        launch {
            dao.Delete()
            dao.SaveData(*myClassList.toTypedArray())  //listedeki verileri sırayla kaydetmek için bu yöntem kullanıldı.
        }
    }

    fun data(): Flow<PagingData<myClass>>{  //Çalışıyor.
        return Pager(
            config = PagingConfig(
                pageSize = 5,
                enablePlaceholders = false,
                maxSize = 100
            ),
            pagingSourceFactory = {
                RoomPagingSource(dao)
            }
        ).flow.cachedIn(viewModelScope)
    }

    fun data2(): Flow<PagingData<myClass>>{ //Çalışıyor.
        return Pager(
            config = PagingConfig(
                pageSize = 5,
                enablePlaceholders = false, //Son veriye gelinidiğine tekrar başa dönmeyi engelledik.
                maxSize = 100
            ),
            pagingSourceFactory = {
                dao.GetAllData2()
            }
        ).flow.cachedIn(viewModelScope)
    }
}
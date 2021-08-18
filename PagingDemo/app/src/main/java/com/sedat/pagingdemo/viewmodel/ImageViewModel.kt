package com.sedat.pagingdemo.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sedat.pagingdemo.api.ImageApi
import com.sedat.pagingdemo.model.ImageResponse
import com.sedat.pagingdemo.model.ImageResult
import com.sedat.pagingdemo.paging.ImagePagingSource
import com.sedat.pagingdemo.repo.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(
    private val repository: ImageRepository,
    private val imageApi: ImageApi,
    @ApplicationContext private val application: Context
): BaseViewModel(application as Application) {

    /*val flow = Pager(
            config = PagingConfig(20, 100),
            pagingSourceFactory = {
                ImagePagingSource(repository)
            }
    ).flow.cachedIn(viewModelScope)*/

    fun data(): kotlinx.coroutines.flow.Flow<PagingData<ImageResult>>{
        return Pager(
                config = PagingConfig(20),
                pagingSourceFactory = {
                    ImagePagingSource(repository)
                }
        ).flow.cachedIn(viewModelScope)
    }
}
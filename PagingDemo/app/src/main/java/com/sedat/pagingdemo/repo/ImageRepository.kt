package com.sedat.pagingdemo.repo

import com.sedat.pagingdemo.api.ImageApi
import com.sedat.pagingdemo.model.ImageResponse
import com.sedat.pagingdemo.model.ImageResult
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class ImageRepository @Inject constructor(
    private val imageApi: ImageApi
) {
    suspend fun getData(page: Int): Response<ImageResponse>{
        return imageApi.getImages(page)
    }


}
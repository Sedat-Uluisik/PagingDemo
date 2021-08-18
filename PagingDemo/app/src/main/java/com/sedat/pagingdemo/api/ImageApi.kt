package com.sedat.pagingdemo.api

import com.sedat.pagingdemo.model.ImageResponse
import com.sedat.pagingdemo.util.API_KEY
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageApi {

    /*RxJava asenkron çalışır ayriyetten coroutine scope kullanmaya gerek yoktur.*/

    @GET("/api/")
    suspend fun getImages(
            @Query("page") per_page: Int,
            @Query("key") api_key: String = API_KEY
    ): Response<ImageResponse>

}
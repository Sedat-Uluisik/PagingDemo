package com.sedat.pagingdemo.hilt

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sedat.pagingdemo.R
import com.sedat.pagingdemo.api.ImageApi
import com.sedat.pagingdemo.repo.ImageRepository
import com.sedat.pagingdemo.room.Database
import com.sedat.pagingdemo.util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun injectRetrofit(): ImageApi{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ImageApi::class.java)
    }

    @Singleton
    @Provides
    fun injectRepository(api: ImageApi) =
        ImageRepository(api)

    @Singleton
    @Provides
    fun injectGlide(@ApplicationContext context: Context) =
        Glide.with(context)
            .setDefaultRequestOptions(
                RequestOptions().placeholder(R.drawable.ic_baseline_downloading_24).error(R.drawable.ic_baseline_error_24)
            )

    @Singleton
    @Provides
    fun injectDatabase(@ApplicationContext context: Context) = Database(context).Dao()
}
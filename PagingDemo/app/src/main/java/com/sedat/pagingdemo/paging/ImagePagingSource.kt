package com.sedat.pagingdemo.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sedat.pagingdemo.model.ImageResult
import com.sedat.pagingdemo.repo.ImageRepository
import kotlinx.coroutines.coroutineScope
import java.lang.Exception
import javax.inject.Inject

class ImagePagingSource @Inject constructor(
        private val repository: ImageRepository
): PagingSource<Int, ImageResult>() {
    override fun getRefreshKey(state: PagingState<Int, ImageResult>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageResult> {
        return try {
            coroutineScope {
                val nextPage = params.key ?: 1
                val response = repository.getData(nextPage)

                LoadResult.Page(
                        data = response.body()!!.hits,
                        prevKey = if(nextPage == 1) null else nextPage - 1,
                        nextKey =  if (response.body()!!.hits.isEmpty()) null else nextPage + 1
                )
            }

        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }

}
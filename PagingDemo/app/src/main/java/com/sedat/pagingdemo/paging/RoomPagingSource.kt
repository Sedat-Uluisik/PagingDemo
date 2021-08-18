package com.sedat.pagingdemo.paging

import androidx.lifecycle.asLiveData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sedat.pagingdemo.model.myClass
import com.sedat.pagingdemo.room.Dao
import javax.inject.Inject

class RoomPagingSource @Inject constructor(
    private val dao: Dao
): PagingSource<Int, myClass>() {
    override fun getRefreshKey(state: PagingState<Int, myClass>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, myClass> {
       return try {
           val nextPage = params.key ?: 1
           val response = dao.GetAllData()

           LoadResult.Page(
               data = response,
               prevKey = if(nextPage == 1) null else nextPage - 1,
               nextKey = if(response.isEmpty()) null else nextPage + 1
           )
       }catch (e: Exception){
           LoadResult.Error(e)
       }
    }

}
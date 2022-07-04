package com.mytestprogram.rickmortyapplication

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mytestprogram.rickmortyapplication.data.remote.CharacterRetrofitService
import kotlinx.coroutines.CoroutineScope
import java.lang.Exception

//class CharacterPageSource(
//    private val retrofitService: CharacterRetrofitService
//) : PagingSource<Int, SingleCharacter>() {
//
//    override fun getRefreshKey(state: PagingState<Int, SingleCharacter>): Int? {
//        return state.anchorPosition
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SingleCharacter> {
//        return try {
//            val currentPage = params.key ?: 1
//            val response = retrofitService.loadAllCharacters()
//            var nextPageNumber: Int? = null
//            val uri = Uri.parse(response.info.next)
//            val nextPageQuery = uri.getQueryParameter("page")
//            nextPageNumber = nextPageQuery?.toInt()
//
//            LoadResult.Page(
//                data = response.results,
//                prevKey = null,
//                nextKey = nextPageNumber
//            )
//
//        } catch (e: Exception) {
//            LoadResult.Error(e)
//        }
//    }
//
////    private fun getPageIndexFromNext(next: String?): Int? {
////        return next?.split("?page=")?.get(1)?.toInt()
////    }
//}
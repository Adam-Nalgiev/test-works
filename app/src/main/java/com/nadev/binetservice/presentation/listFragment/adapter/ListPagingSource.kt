package com.nadev.binetservice.presentation.listFragment.adapter

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nadev.binetservice.domain.GetDrugsListUseCase
import com.nadev.binetservice.entity.dto.DrugsDto
import javax.inject.Inject

class ListPagingSource @Inject constructor(private val getDrugsListUseCase: GetDrugsListUseCase) : PagingSource<Int, DrugsDto>() {

    override fun getRefreshKey(state: PagingState<Int, DrugsDto>): Int = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DrugsDto> {
        val page = params.key ?: 0

        return runCatching {
            getDrugsListUseCase.execute(page)
        }.fold(
            onFailure = { LoadResult.Error(it) },
            onSuccess = {
                LoadResult.Page(
                    data = it,
                    nextKey = page + 1,
                    prevKey = null
                )
            }
        )
    }
}
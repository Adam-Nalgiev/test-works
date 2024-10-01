package com.nadev.binetservice.presentation.listFragment.adapter

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nadev.binetservice.domain.GetFoundDrugsUseCase
import com.nadev.binetservice.entity.dto.DrugsDto
import javax.inject.Inject

class ListFoundDrugsPagingSource @Inject constructor(
    private val getFoundDrugsUseCase: GetFoundDrugsUseCase,
    private val requestText: String
) : PagingSource<Int, DrugsDto>() {

    override fun getRefreshKey(state: PagingState<Int, DrugsDto>): Int = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DrugsDto> {
        val page = params.key ?: 0

        return runCatching {
            getFoundDrugsUseCase.execute(page, requestText)
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
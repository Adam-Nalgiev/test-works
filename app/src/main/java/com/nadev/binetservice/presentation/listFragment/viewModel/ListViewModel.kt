package com.nadev.binetservice.presentation.listFragment.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.nadev.binetservice.domain.GetDrugsListUseCase
import com.nadev.binetservice.domain.GetFoundDrugsUseCase
import com.nadev.binetservice.entity.dto.DrugsDto
import com.nadev.binetservice.presentation.listFragment.adapter.ListFoundDrugsPagingSource
import com.nadev.binetservice.presentation.listFragment.adapter.ListPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ListViewModel @Inject constructor(private val getDrugsListUseCase: GetDrugsListUseCase, private val getFoundDrugsUseCase: GetFoundDrugsUseCase): ViewModel() {

    var drugs: Flow<PagingData<DrugsDto>> =
        Pager(
            config = PagingConfig(10),
            initialKey = null,
            pagingSourceFactory = { ListPagingSource(getDrugsListUseCase) }
        ).flow.cachedIn(viewModelScope)


    fun changeRecyclerData(requestText: String?) {
        drugs =
            if (requestText != null) {
                Pager(
                    config = PagingConfig(10),
                    initialKey = null,
                    pagingSourceFactory = {
                        ListFoundDrugsPagingSource(getFoundDrugsUseCase, requestText)
                    }
                ).flow.cachedIn(viewModelScope)
            } else {
                Pager(
                    config = PagingConfig(10),
                    initialKey = null,
                    pagingSourceFactory = { ListPagingSource(getDrugsListUseCase) }
                ).flow.cachedIn(viewModelScope)
            }
    }

}
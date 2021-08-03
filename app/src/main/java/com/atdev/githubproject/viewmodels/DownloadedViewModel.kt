package com.atdev.githubproject.viewmodels

import androidx.lifecycle.*
import com.atdev.githubproject.helpers.MainRepository
import com.atdev.githubproject.room.RepositoryDownloadedEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DownloadedViewModel @Inject constructor(
    private val mainRepository: MainRepository,
) : ViewModel() {

    val downloadedListRepositoryEntity: Flow<List<RepositoryDownloadedEntity>> =
        mainRepository.getAllDownloadedRepository()

    fun deleteItemDao(item: RepositoryDownloadedEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            mainRepository.deleteItemDao(item)
        }
    }

    val groupEmptyListVisibility: LiveData<Boolean> =
        downloadedListRepositoryEntity.asLiveData(viewModelScope.coroutineContext)
            .map { it.isEmpty() }
    val recyclerVisibility: LiveData<Boolean> =
        downloadedListRepositoryEntity.asLiveData(viewModelScope.coroutineContext)
            .map { !it.isNullOrEmpty() }

}
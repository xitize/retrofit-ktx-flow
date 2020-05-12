package id.kshitiz.testkotlinapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MainViewModal : ViewModel() {
    private var mainRepository: MainRepository = MainRepository()
    fun getTopStories() = liveData(Dispatchers.IO) {
        val topStory = mainRepository.getTopStories()
        emit(topStory)
    }

    @ExperimentalCoroutinesApi
    fun getStory(id: List<Int>) = flow {
        id.forEach {
            this@MainViewModal.mainRepository.getStory(it).collect { value ->
                emit(value)
            }
        }
    }.flowOn(Dispatchers.IO)


}
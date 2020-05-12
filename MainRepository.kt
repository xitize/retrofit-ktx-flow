package id.kshitiz.testkotlinapp

import id.kshitiz.testkotlinapp.api.RetrofitBuilder
import id.kshitiz.testkotlinapp.modal.NewsItem
import kotlinx.coroutines.flow.flow

class MainRepository() {

    suspend fun getTopStories() = RetrofitBuilder.apiService.topStories()

    fun getStory(id: Int) = flow {
        val newsItem = RetrofitBuilder.apiService.story(id)
        emit(newsItem)
    }

}

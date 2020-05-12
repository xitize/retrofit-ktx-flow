package id.kshitiz.testkotlinapp.api

import id.kshitiz.testkotlinapp.modal.NewsItem
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("topstories.json")
    suspend fun topStories(): List<Int>

    @GET("item/{id}.json")
    suspend fun story(@Path("id") id: Int): NewsItem

}
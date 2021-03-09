package com.mrz.apiwork.api

import com.mrz.apiwork.model.Post
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface SimpleApi {

    //Получает простой элемент
    @GET("posts/1")
    suspend fun getPost(): Response<Post>

    //получает список элементов
    @GET("posts")
    suspend fun getCustomPost(
            @Query("userId") userId: Int,
            @Query("_sort") sort: String,
            @Query("_order") order: String
    ): Response<List<Post>>

    //Запрос В АПИ, а не из него, всегда заполняется только так
    @POST("posts")
    suspend fun pushPost(
            @Body post: Post
    ): Response<Post>

}
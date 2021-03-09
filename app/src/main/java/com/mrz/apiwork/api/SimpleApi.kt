package com.mrz.apiwork.api

import com.mrz.apiwork.model.Post
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface SimpleApi {

    @GET("posts/1")
    suspend fun getPost(): Response<Post>

    @GET("posts")
    suspend fun getCustomPost(
            @Query("userId") userId: Int,
            @Query("_sort") sort: String,
            @Query("_order") order: String
    ): Response<List<Post>>

    @POST("posts")
    suspend fun pushPost(
            @Body post: Post
    ): Response<Post>

}
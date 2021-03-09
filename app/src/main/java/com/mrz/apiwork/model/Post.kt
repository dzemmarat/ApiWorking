package com.mrz.apiwork.model
//Здесь пропиши все данные, что получишь из json
data class Post (
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)
package com.example.blogspace.models

data class Article(
    val id: String = "",
    val title: String = "",
    val content: String = "",
    val imageUrl: String = "",
    val authorId: String = "",
    val timestamp: Long = 0
)
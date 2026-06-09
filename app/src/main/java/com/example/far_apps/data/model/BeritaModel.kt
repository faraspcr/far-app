package com.example.far_apps.data.model

// Response dari NewsAPI
data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)


// Model untuk satu berita
data class Article(
    val source: Source?,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?
)


// Model untuk sumber berita
data class Source(
    val id: String?,
    val name: String
)

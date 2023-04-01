package com.shuklansh.inspination_jetpackcompose.Model

data class SearchResponse(
    val next_page: String,
    val page: Int,
    val per_page: Int,
    val photos: List<Photo>,
    val total_results: Int
)
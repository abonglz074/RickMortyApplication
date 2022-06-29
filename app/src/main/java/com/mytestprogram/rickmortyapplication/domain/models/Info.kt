package com.mytestprogram.rickmortyapplication.domain.models

data class Info(
    val count: Int,
    val next: String,
    val pages: Int,
    val prev: Any
)
package com.mytestprogram.rickmortyapplication.utils

interface Mapper<SRC, DST> {
    fun transform(data: SRC): DST
}
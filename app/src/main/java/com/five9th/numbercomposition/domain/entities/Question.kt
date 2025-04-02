package com.five9th.numbercomposition.domain.entities

data class Question(
    val sum: Int,
    val firstValue: Int,
    val secondValueOptions: List<Int>
)

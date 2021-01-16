package com.chao2zhang

@ExplicitParams
fun add(a: Int, b: Int): Int = a + b

fun callAdd() {
    add(2, 3)
    add(a = 2, b = 3)
}
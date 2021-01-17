package com.chao2zhang

import io.github.chao2zhang.RequireNamedArgument

@RequireNamedArgument
fun add(a: Int, b: Int): Int = a + b

fun callAdd() {
    add(2, 3)
    add(a = 2, b = 3)
}
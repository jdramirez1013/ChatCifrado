package com.jdr.model

data class Message(
    val message: String,
    var read: Boolean = false,
    var readDate: String = "",
    val sendDate: String,
    val type: String
)

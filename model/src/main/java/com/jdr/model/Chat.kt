package com.jdr.model

data class Chat(
    val user: User,
    val messages : List<Message>
)

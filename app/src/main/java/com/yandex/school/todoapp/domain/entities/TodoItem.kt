package com.yandex.school.todoapp.domain.entities

import java.util.Date

data class TodoItem(
    val id: String,
    val text: String,
    val importance: String,
    val isCompleted: Boolean,
    val createdAt: Date,
    val deadline: Date? = null,
    val modifiedAt: Date? = null,
)
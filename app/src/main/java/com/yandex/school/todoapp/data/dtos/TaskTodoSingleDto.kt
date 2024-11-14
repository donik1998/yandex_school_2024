package com.yandex.school.todoapp.data.dtos

import com.google.gson.annotations.SerializedName

data class TaskTodoSingleDto (
    @SerializedName("status")
    val status: String,
    @SerializedName("element")
    val element: TaskDto,
    @SerializedName("revision")
    val revision: Int,
)
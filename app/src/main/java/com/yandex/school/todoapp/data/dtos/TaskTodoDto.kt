package com.yandex.school.todoapp.data.dtos

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class TaskTodoDto (
    @SerializedName("status")
    val status: String,
    @SerializedName("list")
    val list: List<TaskDto>,
    @SerializedName("revision")
    val revision: Int
)

data class TaskDto(
    @SerializedName("uuid")
    val uuid: UUID,
    @SerializedName("text")
    val text: String,
    @SerializedName("importance")
    val importance: String,
    @SerializedName("deadline")
    val deadline: Long? = null,
    @SerializedName("done")
    val done: Boolean,
    @SerializedName("color")
    val color: String? = null,
    @SerializedName("created_at")
    val createdAt: Long,
    @SerializedName("changed_at")
    val changedAt: Long? = null,
    @SerializedName("last_updated_by")
    val lastUpdatedBy: String
)
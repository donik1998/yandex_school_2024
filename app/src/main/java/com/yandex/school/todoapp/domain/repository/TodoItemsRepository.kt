package com.yandex.school.todoapp.domain.repository

import com.yandex.school.todoapp.data.dtos.TaskDto
import com.yandex.school.todoapp.data.dtos.TaskTodoDto
import com.yandex.school.todoapp.data.dtos.TaskTodoSingleDto
import com.yandex.school.todoapp.data.taskApi
import com.yandex.school.todoapp.domain.entities.TodoItem
import java.util.Date
import java.util.UUID

class TodoItemsRepository {
    suspend fun fetchTodoItems(): List<TodoItem> {
        val taskDtos: TaskTodoDto = taskApi.fetchTasks()
        return taskDtos.list.map { taskDto ->
            TodoItem(
                id = taskDto.uuid.toString(),
                text = taskDto.text,
                isCompleted = taskDto.done,
                importance = taskDto.importance,
                createdAt = Date(taskDto.createdAt),
                deadline = taskDto.deadline?.let { Date(it) },
                modifiedAt = taskDto.changedAt?.let { Date(it) },
                revision = taskDtos.revision,
            )
        }
    }

    suspend  fun addTodoItem(todoItem: TodoItem) {
        taskApi.addTask(TaskTodoSingleDto(
            status = "OK",
            element = TaskDto(
                uuid = UUID.fromString(todoItem.id),
                text = todoItem.text,
                done = todoItem.isCompleted,
                importance = todoItem.importance,
                createdAt = todoItem.createdAt.time,
                deadline = todoItem.deadline?.time,
                changedAt = todoItem.modifiedAt?.time,
                lastUpdatedBy = "user",
            ),
            revision = todoItem.revision,
        ),)
    }

    suspend fun updateTodoItem(todoItem: TodoItem) {
        taskApi.updateTask(TaskTodoSingleDto(
            status = "OK",
            element = TaskDto(
                uuid = UUID.fromString(todoItem.id),
                text = todoItem.text,
                done = todoItem.isCompleted,
                importance = todoItem.importance,
                createdAt = todoItem.createdAt.time,
                deadline = todoItem.deadline?.time,
                changedAt = todoItem.modifiedAt?.time,
                lastUpdatedBy = "user",
            ),
            revision = todoItem.revision,
        ),)
    }
}
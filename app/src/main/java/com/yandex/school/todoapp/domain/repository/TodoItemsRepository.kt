package com.yandex.school.todoapp.domain.repository

import com.yandex.school.todoapp.domain.entities.TodoItem

class TodoItemsRepository {
    fun fetchTodoItems(): List<TodoItem> {
        return (1..20).map {
            TodoItem(
                id = it.toString(),
                text = "Task $it",
                importance = "High",
                isCompleted = false,
                createdAt = java.util.Date()
            )
        }
    }

    fun addTodoItem(todoItem: TodoItem) {
        // Add todoItem to the repository
    }

    fun updateTodoItem(todoItem: TodoItem) {
        // Update todoItem in the repository
    }
}
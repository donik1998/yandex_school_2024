package com.yandex.school.todoapp.ui.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yandex.school.todoapp.domain.entities.TodoItem
import com.yandex.school.todoapp.domain.repository.TodoItemsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class TasksViewModel: ViewModel() {
    val loadedTasks = MutableStateFlow(emptyList<TodoItem>())
    private val todoItemsRepository: TodoItemsRepository =  TodoItemsRepository();

     init {
        viewModelScope.launch {
            val data = loadTasks()
            loadedTasks.value = data
        }
    }


    suspend  fun loadTasks(): List<TodoItem> {
       todoItemsRepository.fetchTodoItems().let {
           loadedTasks.value = it
       }
        return loadedTasks.value
    }
}
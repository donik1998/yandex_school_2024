package com.yandex.school.todoapp.ui.elements

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.lifecycle.viewmodel.viewModelFactory
import com.yandex.school.todoapp.ui.view_models.TasksViewModel
import kotlinx.coroutines.flow.asStateFlow

@Composable
fun TasksListBlock(onTaskTapped: (Int) -> Unit, paddingValues: PaddingValues, tasksViewModel: TasksViewModel) {
val tasks = tasksViewModel.loadedTasks.collectAsState()


    LazyColumn {
        items(tasks.value.size) { index ->
            TaskItem(taskName = tasks.value.get(index).text)
        }
    }
}


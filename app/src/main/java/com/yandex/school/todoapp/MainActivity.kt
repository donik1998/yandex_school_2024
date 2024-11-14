package com.yandex.school.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.yandex.school.todoapp.ui.elements.MainPageLargeHeaderBlock
import com.yandex.school.todoapp.ui.elements.MainPageSmallHeaderBlock
import com.yandex.school.todoapp.ui.elements.TaskItem
import com.yandex.school.todoapp.ui.theme.TodoAppTheme
import com.yandex.school.todoapp.ui.view_models.TasksViewModel

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        enableEdgeToEdge()
        setContent {
            TodoAppTheme {
                TodoAppMainScreen()
            }
    }
}

@Composable
fun TodoAppMainScreen(){

    val doneTasksCount: MutableState<Int> = remember{ mutableIntStateOf(0) }
    val visibility: MutableState<Boolean> = remember{ mutableStateOf(true) }
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    val hasCollapsed =scrollBehavior.state.collapsedFraction>=0.8f
    val viewModel: TasksViewModel by viewModels()

    Surface(color = Color(color = 0xffF7F6F2) ) { Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        containerColor = Color(
            color = 0xfff7f6f2,
        ),
        topBar = {
    LargeTopAppBar(
        colors = TopAppBarColors(
            containerColor = Color(color = 0xffF7F6F2),
            scrolledContainerColor = Color(color = 0xffF7F6F2),
            navigationIconContentColor = Color(color = 0xff000000),
            titleContentColor = Color(color = 0xff000000),
            actionIconContentColor = Color(color = 0xff000000),
        ),
        windowInsets = WindowInsets(
            top = if(hasCollapsed) 24.dp else 128.dp.times(1- scrollBehavior.state.collapsedFraction),
            bottom = 0.dp,
            left = 0.dp,
            right = 0.dp
        ),
        title = {
            if(hasCollapsed)
                MainPageSmallHeaderBlock(
                title = "Мои дела",
                visibility = visibility.value,
                onVisibilityChange = { visibility.value = it }
            )

            if(!hasCollapsed)
                MainPageLargeHeaderBlock(
                title = "Мои дела",
                hideDoneTasks = hasCollapsed,
                doneTasksCount = doneTasksCount.value,
                visibility = visibility.value,
                onVisibilityChange = { visibility.value = it }
            )
        },
        scrollBehavior = scrollBehavior,
    )
        },
    ) { paddingValues->
        Box(
            modifier = Modifier

                .windowInsetsPadding(
                    WindowInsets(top=0.dp,
                    bottom=0.dp,
                    left=8.dp,
                    right=8.dp,)
                ).clip(RoundedCornerShape(8.dp))
        ) {
            LazyColumn(
                contentPadding = paddingValues,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(color = 0xffffffff))
            ) {
                items(viewModel.loadedTasks.value.size) { index ->
                    TaskItem(taskName = viewModel.loadedTasks.collectAsState().value.get(index).text)
                }
            }
        }
    } }

}}

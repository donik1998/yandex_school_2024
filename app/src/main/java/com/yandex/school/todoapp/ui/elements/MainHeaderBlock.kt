package com.yandex.school.todoapp.ui.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yandex.school.todoapp.ui.theme.MyFonts

@Composable
fun  MainPageLargeHeaderBlock(
    title: String,
    doneTasksCount: Int,
    visibility: Boolean,
    onVisibilityChange: (Boolean) -> Unit,
    hideDoneTasks: Boolean,
) {
    Column {
        Text(text = title, style = TextStyle(
            fontSize = 32.sp,
            color = Color.Black,
            fontFamily = MyFonts,
        ), modifier = Modifier.padding( start = 60.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(end = 24.dp)
        ) {

                Text(
                text = if(!hideDoneTasks) "Выполнено - $doneTasksCount" else "", style = TextStyle(
                        fontSize = 16.sp,
                        color = Color.Black,
                        fontFamily = MyFonts
                    ), modifier = Modifier.padding(start = 60.dp),
            )

                IconButton(onClick = {
                    onVisibilityChange(!visibility)
                }) {
                    if(visibility) {
                        Icon(
                            Icons.Default.Visibility,
                            contentDescription = "Visibility",
                            tint = Color(color = 0xff007AFF),
                        )
                    } else {
                        Icon(
                            Icons.Default.VisibilityOff,
                            contentDescription = "Visibility",
                            tint = Color(color = 0xff007AFF),
                        )
                    }
                }

        }
    }
}
package com.example.todoapp_2nd.ui.theme.todo_list

import android.graphics.drawable.VectorDrawable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.todoapp_2nd.data.Todo
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.todoapp_2nd.R

@Composable
fun TodoItem(
    todo: Todo,
    onEvent: (TodoListEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Row (
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ){
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            ListItem(

                headlineContent = {
                    Text(
                        text = todo.title,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                },
                trailingContent = {
                                  Checkbox(
                                      checked = todo.isDone,
                                      onCheckedChange = {
                                          isChecked -> onEvent(TodoListEvent.OnDoneChange(todo , isChecked))

                                      })
                },
                leadingContent = {
                    IconButton(onClick = {
                        onEvent(TodoListEvent.DeleteTodo(todo))
                    }) {
                        //val drawable = painterResource(id = R.drawable.close)  as ImageVector
                        Icon(imageVector = Icons.Default.Delete ,
                            contentDescription = "Delete" )

                    }
                },
                supportingContent =
                {
                    todo.description?.let{
                        Text(
                            text = it
                        )

                    }
                },




            )
            HorizontalDivider()

        }

    }
}
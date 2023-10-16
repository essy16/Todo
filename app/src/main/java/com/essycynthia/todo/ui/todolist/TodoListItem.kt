package com.essycynthia.todo.ui.todolist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.essycynthia.todo.data.Todo

@Composable
fun TodoListItem(
    toDo: Todo,
    event: (TodolistEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Checkbox(
            checked = toDo.isDone,
            onCheckedChange = { isChecked -> event(TodolistEvent.OnDoneChanged(toDo, isChecked)) })
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(text = toDo.title)
            toDo.description?.let {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = it)

            }


        }
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "Delete",
            Modifier.clickable {
                event(TodolistEvent.DeleteTodo(toDo))
            }
        )

    }
}
package com.essycynthia.todo.ui.todolist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.essycynthia.todo.data.Todo
import com.essycynthia.todo.ui.add_edit_todo.AddEditViewModel



@Composable
fun TodoListItem(
    toDo: Todo,
    event: (TodolistEvent) -> Unit,
    modifier: Modifier = Modifier

) {
    Card(
        Modifier
            .fillMaxWidth()
            .height(140.dp)
            .padding(horizontal = 16.dp),
        elevation =8.dp ) {
        Column (){
            Row(
                Modifier
                    .fillMaxWidth()
                    .background(
                        color = when (toDo.priority) {
                            0 -> Color.White
                            1 -> Color(0xFF24A19C)
                            2 -> Color(0xFF1B1C1F)
                            3 -> Color(0xFFEA4335)
                            4 -> Color(0xFF1877F2)

                            else -> {
                                Color.White
                            }
                        }
                    )
            ) {
                Text(text = "Priority task ${toDo.priority}")
            }
            Row {
                Checkbox(

                        colors = CheckboxDefaults.colors( when (toDo.priority) {
                            0 -> Color.White
                            1 -> Color(0xFF24A19C)
                            2 -> Color(0xFF1B1C1F)
                            3 -> Color(0xFFEA4335)
                            4 -> Color(0xFF1877F2)
                            else -> {
                                Color.White
                            }
                        }
                        ),
                    checked = toDo.isDone,
                    onCheckedChange = { isChecked -> event(TodolistEvent.OnDoneChanged(toDo, isChecked)) })
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Row {
                        Text(text = toDo.title)

                    }
                    Row {
                        toDo.description?.let { Text(text = it) }

                    }
                }
            }

        }

    }
}
//it@Composable
//fun TodoListItem(
//    toDo: Todo,
//    event: (TodolistEvent) -> Unit,
//    modifier: Modifier = Modifier
//) {
//    Row(modifier = Modifier.fillMaxWidth()) {
//        Checkbox(
//            checked = toDo.isDone,
//            onCheckedChange = { isChecked -> event(TodolistEvent.OnDoneChanged(toDo, isChecked)) })
//        Spacer(modifier = Modifier.width(8.dp))
//        Column {
//            Text(text = toDo.title)
//            toDo.description?.let {
//                Spacer(modifier = Modifier.height(8.dp))
//                Text(text = it)
//
//            }
//
//
//        }
//        Spacer(modifier = Modifier.weight(1f))
//        Icon(
//            imageVector = Icons.Default.Delete,
//            contentDescription = "Delete",
//            Modifier.clickable {
//                event(TodolistEvent.DeleteTodo(toDo))
//            }
//        )
//
//    }
//}
package com.essycynthia.todo.ui.todolist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.outlined.Flag
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.essycynthia.todo.ui.add_edit_todo.AddEditEvent
import com.essycynthia.todo.ui.add_edit_todo.AddEditViewModel
import com.essycynthia.todo.util.UiEvent
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TodoListScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: TodolistViewModel = hiltViewModel(),
    addEditViewModel: AddEditViewModel = hiltViewModel()

) {
    val todos = viewModel.todos.collectAsState(initial = emptyList())
    val bottomSheetScaffoldState =
        rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState =
        rememberBottomSheetScaffoldState(bottomSheetState = bottomSheetScaffoldState)
    val coroutineScope = rememberCoroutineScope()
    var isDialogVisible by remember { mutableStateOf(false) }
    LaunchedEffect(viewModel.sheetState) {
        coroutineScope.launch {
            if (viewModel.sheetState) {
                bottomSheetScaffoldState.isExpanded
            } else {
                bottomSheetScaffoldState.isCollapsed
            }
        }
    }

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.ShowSnackBar -> {
                    val result = scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )
                    if (result == SnackbarResult.ActionPerformed) {
                        viewModel.onEvent(TodolistEvent.UndoDelete)
                    }
                }

                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }

    }
    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            TextField(
                value = addEditViewModel.title,
                onValueChange = {
                    addEditViewModel.onEvent(AddEditEvent.OnTitleChanged(it))
                },
                placeholder = {
                    Text(text = "Title")
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = addEditViewModel.description,
                onValueChange = {
                    addEditViewModel.onEvent(AddEditEvent.OnDescriptionChanged(it))
                },
                placeholder = {
                    Text(text = "Description")
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = false,
                maxLines = 5
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(Modifier.fillMaxWidth()) {
                IconButton(onClick = { /* do something */ }) {
                    Icon(Icons.Filled.DateRange, contentDescription = "Calendar")
                }
                IconButton(onClick = { /* do something */ }) {
                    Icon(Icons.Filled.AccessTime, contentDescription = "Time")
                }
                IconButton(onClick = {
                    isDialogVisible = true
                }) {
                    Icon(Icons.Outlined.Flag, contentDescription = "Priority")
                }
                IconButton(onClick = { /* do something */ }) {
                    Icon(Icons.Outlined.Send, contentDescription = "Save")
                }
            }
        }

    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()

        ) {
            items(todos.value) { todo ->
                TodoListItem(
                    toDo = todo,
                    event = viewModel::onEvent,
                     modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            viewModel.onEvent(TodolistEvent.OnClickTodo(todo))
                        }
                )

            }

        }
        FloatingActionButton(onClick = {
            viewModel.onEvent(TodolistEvent.OnAddTodo)
        }) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add"
            )
        }
    }


//    Scaffold(
//        scaffoldState = scaffoldState,
//        floatingActionButton = {
//            FloatingActionButton(onClick = {
//                viewModel.onEvent(TodolistEvent.OnAddTodo)
//            }) {
//                Icon(
//                    imageVector = Icons.Default.Add,
//                    contentDescription = "Add"
//                )
//            }
//        },
//        content = { padding ->
//            LazyColumn(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(padding)
//            ) {
//                items(todos.value) { todo ->
//                    TodoListItem(
//                        toDo = todo,
//                        event = viewModel::onEvent,
//                        modifier = Modifier.fillMaxWidth()
//                            .clickable {
//                                viewModel.onEvent(TodolistEvent.OnClickTodo(todo))
//                            }
//                    )
//
//                }
//
//            }
//
//        }
//    )
    if (isDialogVisible) {
        MinimalDialog(onDismissRequest = { isDialogVisible = false })
    }
}

@Composable
fun MinimalDialog(onDismissRequest: () -> Unit) {
    Dialog(onDismissRequest = { onDismissRequest }) {
        PriorityTasks()
    }

}

//@Preview
@Composable
fun PriorityTasks(
    addEditViewModel: AddEditViewModel = hiltViewModel()
) {
    LazyColumn {
        item {
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = "\uD83D\uDCA5 Priority task 1 ", modifier = Modifier
                .padding(horizontal = 16.dp)
                .clickable { addEditViewModel.onEvent(AddEditEvent.OnPriorityUpdated(1)) })
            Spacer(modifier = Modifier.height(2.dp))
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

        }
        item {
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "\uD83D\uDCAA Priority task 2 ",
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .clickable { addEditViewModel.onEvent(AddEditEvent.OnPriorityUpdated(2)) })
            Spacer(modifier = Modifier.height(2.dp))
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
        }
        item {
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "\uD83D\uDC4C Priority task 3",
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .clickable { addEditViewModel.onEvent(AddEditEvent.OnPriorityUpdated(3)) })

            Spacer(modifier = Modifier.height(2.dp))
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
        }
        item {
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = "\u270CPriority task 4", modifier = Modifier
                .padding(horizontal = 16.dp)
                .clickable { addEditViewModel.onEvent(AddEditEvent.OnPriorityUpdated(4)) })

        }
    }
}


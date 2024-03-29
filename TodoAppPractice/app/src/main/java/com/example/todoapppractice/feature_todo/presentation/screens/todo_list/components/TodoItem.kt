package com.example.todoapppractice.feature_todo.presentation.screens.todo_list.components

import android.R
import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapppractice.feature_todo.domain.model.Todo
import com.example.todoapppractice.feature_todo.presentation.screens.todo_list.utils.TodoListEvents

@Composable
fun TodoItem(
    todo: Todo,
    onEvent: (TodoListEvents) -> Unit,
    modifier: Modifier = Modifier
){
  Row(
      modifier = modifier,
      verticalAlignment = Alignment.CenterVertically
  ){
      Column(
          modifier = Modifier.weight(1f),
          verticalArrangement = Arrangement.Center
      ){
        Row(verticalAlignment = Alignment.CenterVertically){
            Text(
                text = todo.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(onClick = {onEvent(TodoListEvents.onDeleteTodoClick(todo))}) {
                Icon(imageVector = Icons.Default.Delete ,
                    contentDescription = "Delete Todo")

            }

        }
          todo.description?.let { description ->
              Spacer(modifier = Modifier.height(8.dp))
              Text(text = description)
          }
      }
      Checkbox(checked = todo.isDone,
                onCheckedChange = { isChecked ->
                    onEvent(TodoListEvents.OnDoneChange(todo, isChecked))
                })


  }
}
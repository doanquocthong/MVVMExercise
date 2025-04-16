package com.example.mvvmexercise

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun AddScreen(navController: NavController, taskViewModel: taskViewModel) {
    var title by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var description by remember {
        mutableStateOf(TextFieldValue(""))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp)
    ) {
        HeaderAdd(navController)
        Spacer(modifier = Modifier.height(30.dp))
        BodyAdd(title, description, onTitleChange = { title = it }, onDescChange = { description = it })
        Spacer(modifier = Modifier.height(30.dp))
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            ButtonAdd(title, description, taskViewModel, navController)
        }
    }
}

@Composable
fun HeaderAdd(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            color = colorResource(R.color.primary_color),
            shape = RoundedCornerShape(18.dp),
            modifier = Modifier
                .width(45.dp)
                .height(45.dp)
                .clickable { navController.popBackStack() }
        ) {
            Image(
                painter = painterResource(R.drawable.baseline_arrow_back_ios_24),
                contentDescription = "Back",
                modifier = Modifier.padding(10.dp)
            )
        }

        Text(
            "Add New",
            color = colorResource(R.color.primary_color),
            modifier = Modifier
                .weight(1f)
                .padding(5.dp),
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun BodyAdd(
    title: TextFieldValue,
    description: TextFieldValue,
    onTitleChange: (TextFieldValue) -> Unit,
    onDescChange: (TextFieldValue) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            "Task",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        Surface(
            shadowElevation = 2.dp,
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.padding(bottom = 10.dp)
        ) {
            TextField(
                value = title,
                onValueChange = onTitleChange,
                textStyle = TextStyle(color = Color.Gray, fontSize = 17.sp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    unfocusedContainerColor = Color.White,
                ),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Text(
            "Description",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        Surface(
            shadowElevation = 2.dp,
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.padding(bottom = 10.dp)
        ) {
            TextField(
                value = description,
                onValueChange = onDescChange,
                textStyle = TextStyle(
                    color = Color.Gray,
                    fontSize = 17.sp,
                    textAlign = TextAlign.Start
                ),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                ),
                singleLine = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
        }
    }
}

@Composable
fun ButtonAdd(
    title: TextFieldValue,
    description: TextFieldValue,
    taskViewModel: taskViewModel,
    navController: NavController
) {
    val context = LocalContext.current
    Button(
        onClick = {
            if (title.text.isEmpty() || description.text.isEmpty()) {
                Toast.makeText(context, "Dữ liệu không được trống", Toast.LENGTH_SHORT).show()
            }
            else {
            navController.popBackStack()
            taskViewModel.addTask(title.text, description.text)
            }
        },
        modifier = Modifier.width(150.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(R.color.primary_color)
        )
    ) {
        Text("Add", modifier = Modifier.padding(5.dp))
    }
}

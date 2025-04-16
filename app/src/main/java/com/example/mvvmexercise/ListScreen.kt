package com.example.mvvmexercise

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ListScreen(navController: NavController, taskViewModel: taskViewModel){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp)
    ){
        HeaderList(navController)
        Spacer(
            modifier = Modifier.padding(top = 40.dp)
        )
        ButtonSpam(taskViewModel)
        Spacer(
            modifier = Modifier.padding(top = 40.dp)
        )
        BodyList(taskViewModel)
    }

}

@Composable
fun HeaderList(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box {
                Surface(
                    color = colorResource(R.color.primary_color),
                    shape = RoundedCornerShape(18.dp),
                    modifier = Modifier
                        .width(45.dp)
                        .height(45.dp)
                        .clickable {

                        }
                ) {
                    Image(
                        painter = painterResource(R.drawable.baseline_arrow_back_ios_24),
                        contentDescription = "Back",
                        modifier = Modifier.padding(
                            top = 10.dp,
                            start = 15.dp,
                            end = 10.dp,
                            bottom = 10.dp
                        )
                    )
                }
            }

            Text(
                "List",
                color = colorResource(R.color.primary_color),
                modifier = Modifier
                    .weight(1f)
                    .padding(5.dp),
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Surface (
                shape = CircleShape,
                color = colorResource(R.color.primary_color),
                modifier = Modifier
                    .size(50.dp)
                    .clickable{
                        navController.navigate("add")
                    }
            ){
                Image(
                    painter = painterResource(R.drawable.baseline_add_24),
                    contentDescription = null,
                    Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun BodyList(taskViewModel: taskViewModel){
    val tasks by taskViewModel.taskList.observeAsState()
    tasks?.let {
        LazyColumn(
            modifier = Modifier,
            content = {
                itemsIndexed(it){index: Int, item: DataType ->
                    CardItem(
                        task = item, taskViewModel
                    )
                }
            }
        )
    }?: Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        text = "No items yet",
        fontSize = 16.sp
    )
}

@Composable
fun CardItem(task : DataType, taskViewModel: taskViewModel){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 15.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.secondary_color)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = task.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    modifier = Modifier
                )
                Text(
                    text = task.description,
                    fontSize = 17.sp,
                    modifier = Modifier
                )
            }
            Surface(
                shape = CircleShape,
                modifier = Modifier
                    .size(40.dp)
                    .clickable {
                        taskViewModel.deleteTask(task.id)
                    }
            ) {
                Image(
                    painter = painterResource(R.drawable.baseline_delete_outline_24),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(5.dp)
                )
            }

        }


    }
}

@Composable
fun ButtonSpam(taskViewModel: taskViewModel) {
    Row (
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ){
        Button(
            onClick = {
                for (i in 1..10) {
                    taskViewModel.addTask("Index ${i}", "${i}| Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua...")
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.primary_color)
            )
        ) {
            Text(
                "Spam 10 tasks",
                fontSize = 15.sp,
                modifier = Modifier.padding(10.dp)
            )
        }
        Button(
            onClick = {
                taskViewModel.deleteAllTask()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.primary_color)
            )
        ) {
            Text(
                "Delete All Task",
                fontSize = 15.sp,
                modifier = Modifier.padding(10.dp)
            )
        }
    }



}
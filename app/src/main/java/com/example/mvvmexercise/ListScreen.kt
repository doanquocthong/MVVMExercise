package com.example.mvvmexercise

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun ListScreen(navController: NavController, jobViewModel: JobViewModel){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp)
    ){
        HeaderList(navController)
        Spacer(
            modifier = Modifier.padding(top = 40.dp)
        )
        BodyList(jobViewModel)
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
fun BodyList(jobViewModel: JobViewModel){
    val jobs by jobViewModel.jobs
    LazyColumn {
        items(jobs.size) { index ->
            CardItem(job = jobs[index])
        }
    }
}

@Composable
fun CardItem(job: Job){
    val cardColors = listOf(
        colorResource(R.color.medium_color), // LightPink
        colorResource(R.color.secondary_color), // LightBlue
        colorResource(R.color.low_color) // LightGreen
    )
    val randomColor = cardColors.random()
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 15.dp),
        colors = CardDefaults.cardColors(
            containerColor = randomColor
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Column(

            ) {
                Text(
                    text = job.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    modifier = Modifier
                )
                Text(
                    text = job.description,
                    fontSize = 17.sp,
                    modifier = Modifier
                )

            }
        }


    }
}
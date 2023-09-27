package com.example.level4_task_ex.ui.theme.screens

import android.graphics.Color
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.level4_task_ex.R

const val TYPE_MATH = "\uD83E\uDDEE  Random math"
const val TYPE_YEAR = "\uD83D\uDCC5  Random year"
const val TYPE_TRIVIA = "\uD83D\uDE01  Trivia"

@Composable
@Preview
fun NumberTypeScreen(
    onClickDetail: (numberType: String) -> Unit = {},
) {
    //for the overview screen we use a hardcoded list base on the constants defined above
    val numberTypes by remember { mutableStateOf(listOf(TYPE_TRIVIA, TYPE_YEAR, TYPE_MATH)) }

    Column(
        Modifier
            .fillMaxHeight()
            .padding(top = 80.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
    )
    {
        Text(
            text = stringResource(R.string.overview_header),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 16.dp)
                .align(Alignment.CenterHorizontally)
        )

        LazyColumn {
            items(numberTypes) { numberType: String ->
                Surface(modifier = Modifier.clickable { onClickDetail(numberType) }) {
                    NumberTypeCard(numberType = numberType)
                }
            }
        }
    }
}

@Composable
fun NumberTypeCard(numberType: String) {
    Card(
        colors = CardDefaults.cardColors(containerColor = androidx.compose.ui.graphics.Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Text(
            text = numberType,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(16.dp),
            style = TextStyle(fontSize = 22.sp)
        )
    }
}

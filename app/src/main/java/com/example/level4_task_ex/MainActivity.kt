package com.example.level4_task_ex

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.level4_task_ex.ui.theme.LEVEL4_TASK_EXTheme
import com.example.level4_task_ex.ui.theme.screens.NumberDetailScreen
import com.example.level4_task_ex.ui.theme.screens.NumberTypeScreen
import com.example.level4_task_ex.ui.theme.screens.NumbersScreens
import com.example.level4_task_ex.ui.theme.screens.TYPE_TRIVIA

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LEVEL4_TASK_EXTheme {
                NumbersApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NumbersApp() {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Blue)
            )
        }
    ) { innerPadding ->
        NumbersNavHost(navController, modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun NumbersNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = NumbersScreens.NumbersOverview.name,
        modifier = modifier
    ) {
        //this one is from the previous step
        composable(NumbersScreens.NumbersOverview.name) {
            NumberTypeScreen(
                onClickDetail = {
                    navController.navigate("${NumbersScreens.NumberDetail.name}/${it}")
                },
            )
        }

        //add this one!
        val screenName = NumbersScreens.NumberDetail.name
        composable(
            route = "$screenName/{numberType}",
            arguments = listOf(
                navArgument("numberType") {
                    type = NavType.StringType
                }
            )
        ) { entry ->
            val numberType = entry.arguments?.getString("numberType")
            NumberDetailScreen(numberType ?: TYPE_TRIVIA)
        }
    }
}

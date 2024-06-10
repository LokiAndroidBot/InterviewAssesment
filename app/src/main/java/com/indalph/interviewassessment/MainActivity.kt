package com.indalph.interviewassessment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Logger
import com.indalph.interviewassessment.ui.theme.InterviewAssessmentTheme
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLEncoder
import kotlin.reflect.full.memberProperties

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseDatabase.getInstance().setLogLevel(Logger.Level.DEBUG)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val state by viewModel.state.collectAsState()

            LaunchedEffect(Unit) {
                viewModel.intents.send(MainIntent.LoadData)
            }

            NavHost(navController = navController, startDestination = "main") {
                composable("main") {
                    MainScreen(state, viewModel, navController)
                }
                composable(
                    "details/{url}",
                    arguments = listOf(navArgument("url") { type = NavType.StringType })
                ) { backStackEntry ->
                    val urlListJson = backStackEntry.arguments?.getString("url") ?: ""
                    val urlList = Json.decodeFromString<List<String>>(urlListJson)
                    DetailScreen(urlList, viewModel, navController)
                }
                composable(
                    "detailScreen/{url}",
                    arguments = listOf(navArgument("url") { type = NavType.StringType })
                ) { backStackEntry ->
                    val url = backStackEntry.arguments?.getString("url") ?: ""
                    WebViewExample(url)
                }
            }
        }
    }
}

@Composable
fun MainScreen(state: MainState, mainViewModel: MainViewModel, navController: NavHostController) {
    when (state) {
        is MainState.Idle -> {
            Text("Idle")
        }

        is MainState.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }

        is MainState.Data -> {
            ExpandableList(state.response, mainViewModel, navController)
        }

        is MainState.Error -> {
            Text("Error: ${state.error}")
        }
    }
}

@Composable
fun ExpandableList(response: Response, viewModel: MainViewModel, navController: NavHostController) {
    val expandedState = remember { mutableStateMapOf<String, Boolean>() }
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        response.parent.forEach { parent ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = parent, fontSize = 20.sp, modifier = Modifier
                    .clickable {
                        expandedState[parent] = !(expandedState[parent] ?: false)
                        expandedState.keys
                            .filter { it != parent }
                            .forEach { expandedState[it] = false }
                    }
                    .padding(16.dp))
                val isExpanded = expandedState[parent] ?: false
                if (isExpanded) {
                    val children =
                        (getProperty(response, parent) as? Collection<*>)?.filterIsInstance<Item>()
                            ?: emptyList()
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 32.dp)
                    ) {
                        children.forEach { child ->
                            Box(modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    val urlListJson = Json.encodeToString(child.refList)
                                    val encodedUrlListJson = URLEncoder.encode(urlListJson, "UTF-8")
                                    navController.navigate("details/${encodedUrlListJson}")
                                }) {
                                Text(
                                    text = child.message,
                                    modifier = Modifier.padding(vertical = 4.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DetailScreen(
    urlList: List<String>,
    viewModel: MainViewModel,
    navController: NavHostController,
) {
    Column {
        Text("Details Screen")
        urlList.forEach { url ->
            Text(modifier = Modifier.clickable {
                val encodedUrlListJson = URLEncoder.encode(url, "UTF-8")
                navController.navigate("detailScreen/${encodedUrlListJson}")
            }, text = url)
        }
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewExample(url: String) {
    val context = LocalContext.current
    val webView = remember {
        WebView(context).apply {
            settings.javaScriptEnabled = true
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = WebViewClient()
        }
    }

    AndroidView({ webView }) {
        it.loadUrl(url)
    }
}

fun <T : Any> getProperty(instance: T, propertyName: String): Any? {
    return instance::class.memberProperties.firstOrNull {
        it.name.equals(propertyName, ignoreCase = true)
    }?.getter?.call(instance)
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    InterviewAssessmentTheme {
        //ExpandableList(response = Response())
    }
}

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Logger
import com.indalph.interviewassessment.ui.theme.InterviewAssessmentTheme
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.reflect.full.memberProperties


class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseDatabase.getInstance().setLogLevel(Logger.Level.DEBUG)
        enableEdgeToEdge()
        setContent {
            val state by viewModel.state.collectAsState()

            LaunchedEffect(Unit) {
                viewModel.intents.send(MainIntent.LoadData)
            }

            MainScreen(state, viewModel)
        }
    }

}

@Composable
fun MainScreen(state: MainState, mainViewModel: MainViewModel) = when (state) {
    is MainState.Idle -> {
        Text("Idle")
    }

    is MainState.Loading -> {
        Column(
            modifier = Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }

    }

    is MainState.Data -> {
        ExpandableList(state.response, mainViewModel)
    }

    is MainState.Error -> {
        Text("Error: ${state.error}")
    }

    is MainState.CHILD -> {
        Column(
            modifier = Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally
        ) {
            ExpandableList(state.response)
        }

    }
}

@Composable
fun ExpandableList(response: List<String>) {
    var selectedURL by remember { mutableStateOf("") }
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Column(
            modifier = Modifier

                .weight(0.3f)
                .padding(top = 20.dp, bottom = 20.dp)
        ) {
            response.forEach { parent ->
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        selectedURL = parent
                    }) {
                    Text(text = parent, fontSize = 20.sp, modifier = Modifier.padding(8.dp))
                }
            }

        }
        Box(
            modifier = Modifier
                .weight(0.7f)
                .padding(top = 20.dp, bottom = 20.dp)

        ) {
            WebViewExample(selectedURL)
        }
    }
}

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun ExpandableList(response: Response, viewModel: MainViewModel) {

    val expandedState = remember { mutableStateMapOf<String, Boolean>() }
    var selectedURL by remember { mutableStateOf("") }
    Column(modifier = Modifier) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
                .padding(top = 20.dp, bottom = 20.dp)

        ) {
            response.parent.forEach { parent ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = parent, fontSize = 20.sp, modifier = Modifier
                        .clickable {
                            expandedState[parent] = !(expandedState[parent] ?: false)
                            // Close all other parent children
                            expandedState.keys
                                .filter { it != parent }
                                .forEach { expandedState[it] = false }
                        }
                        .padding(16.dp))
                    val isExpanded = expandedState[parent] ?: false
                    if (isExpanded) {
                        val children = (getProperty(
                            response, parent
                        ) as? Collection<*>)?.filterIsInstance<Item>() ?: emptyList()
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 32.dp)
                        ) {
                            children.forEach { child ->
                                Box(modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        GlobalScope.launch {
                                            viewModel.intents.send(
                                                MainIntent.LoadChildData(
                                                    child.refList
                                                )
                                            )
                                        }
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
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(top = 20.dp, bottom = 20.dp)

        ) {
            WebViewExample(selectedURL)
        }
    }
}

fun <T : Any> getProperty(instance: T, propertyName: String): Any? {
    return instance::class.memberProperties.firstOrNull {
        it.name.equals(
            propertyName, ignoreCase = true
        )
    }?.getter?.call(instance)
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


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    InterviewAssessmentTheme {
        //ExpandableList(response = Response())
    }
}

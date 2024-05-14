package com.indalph.interviewassessment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.indalph.interviewassessment.ui.theme.InterviewAssessmentTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InterviewAssessmentTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MyScreen(modifier: Modifier) {
    val parentList = getParent()
    val childMap = remember {
        parentList.associateWith { parent ->
            when (parent) {
                "Variables", "Class", "String" -> child()
                else -> emptyList()
            }
        }
    }
    val expandedParent = remember { mutableMapOf<String, Boolean>() }
    val expandedChild = remember { mutableMapOf<String, Boolean>() }

    LazyColumn(modifier = modifier.padding(20.dp)) {
        items(parentList) { parent ->
            Box(modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    expandedParent[parent] = !(expandedParent[parent] ?: false)
                }) {
                Text(
                    text = parent,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(8.dp)
                )
            }

            if (expandedParent[parent] == true) {
                childMap[parent]?.forEach { childResult ->
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 32.dp)
                        .clickable {
                            expandedChild[childResult.message] =
                                !(expandedChild[childResult.message] ?: false)
                        }) {
                        Text(
                            text = childResult.message, modifier = Modifier.padding(8.dp)
                        )
                    }

                    if (expandedChild[childResult.message] == true) {
                        Button(
                            onClick = childResult.function,
                            modifier = Modifier.padding(start = 64.dp)
                        ) {
                            Text("Run")
                        }
                    }
                }
            }
        }
    }
}

fun getParent(): List<String> = listOf("Variables", "Class", "String")

fun child(): List<Result> = listOf(
    Result.Data("Variables", "DataType", ::dataTypeSize),
    Result.Data("Class", "DoLazy", ::doLazy),
    Result.Data("String", "DoLazy", ::dataTypeSize)
)

sealed class Result {
    data class Data(val parent: String, val message: String, val function: () -> Unit) : Result()
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

    AndroidView({ webView }) { webView ->
        webView.loadUrl(url)
    }
}


enum class TaskList {
    DATATYPE_SIZE, LAZY, LATE_INIT, DOUBLE_BANG_OPERATOR, NULLABLE_OPERATOR, ELVIS_OPERATOR, ANY_TYPE, MUTABLE_IMMUTABLE, MUTABLE_IMMUTABLE_LIST, STRING_REVERSE, CHAR_OCCURRENCE, DATA_CLASS, ENUM_CLASS, NESTED_CLASS, INNER_CLASS, SINGLETON_CLASS, INIT_BLOCK, EQUALITY_CHECK, GENERIC, GENERIC_EXTENSION, INFIX_FUNCTION, INLINE_FUNCTION
}

fun doWork(data: TaskList): String {
    return when (data) {
        TaskList.DATATYPE_SIZE -> dataTypeSize()
        TaskList.LAZY -> doLazy()
        else -> ""
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    InterviewAssessmentTheme {
        MyScreen(modifier = Modifier.fillMaxSize())
    }
}

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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.indalph.interviewassessment.ui.theme.InterviewAssessmentTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InterviewAssessmentTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android", modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val taskListItems = remember { TaskList.entries }
    var selectedUrl by remember { mutableStateOf("https://pl.kotl.in/6broB_6Bn") }

    Column(modifier = modifier.fillMaxSize(1f)) {
        LazyColumn(modifier = modifier.weight(0.5f)) {
            items(taskListItems) { taskListItem ->
                Box(Modifier.clickable {
                    selectedUrl = doWork(taskListItem)
                }) {
                    Text(text = taskListItem.name, modifier = Modifier.padding(16.dp))
                }
            }
        }
        Column(modifier = modifier.weight(0.5f)) {
            WebViewExample(selectedUrl)
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

    AndroidView({ webView }) { webView ->
        webView.loadUrl(url)
    }
}

enum class TaskList {
    DATATYPE_SIZE, LAZY, LATE_INIT, DOUBLE_BANG_OPERATOR, NULLABLE_OPERATOR, ELVIS_OPERATOR, ANY_TYPE, MUTABLE_IMMUTABLE, MUTABLE_IMMUTABLE_LIST, STRING_REVERSE, CHAR_OCCURRENCE, DATA_CLASS, ENUM_CLASS, NESTED_CLASS, INNER_CLASS, SINGLETON_CLASS, INIT_BLOCK, EQUALITY_CHECK, GENERIC, GENERIC_EXTENSION, INFIX_FUNCTION, INLINE_FUNCTION
}

fun doWork(data: TaskList): String {
    return return when (data) {
        TaskList.DATATYPE_SIZE -> dataTypeSize()/*TaskList.LAZY -> doLazy()
        TaskList.LATE_INIT -> doLateInit()
        TaskList.DOUBLE_BANG_OPERATOR -> doDoubleBang()
        TaskList.NULLABLE_OPERATOR -> doNullable()
        TaskList.ELVIS_OPERATOR -> doElvis()
        TaskList.ANY_TYPE -> doAnyType()
        TaskList.MUTABLE_IMMUTABLE -> doMutableImmutable()
        TaskList.MUTABLE_IMMUTABLE_LIST -> doMutableImmutableList()
        TaskList.STRING_REVERSE -> doStringReverse()
        TaskList.CHAR_OCCURRENCE -> doFindRepeatedCharacterOccurrence()
        TaskList.INIT_BLOCK -> doInitBlock()
        TaskList.DATA_CLASS -> doDataClass()
        TaskList.ENUM_CLASS -> doEnumClass()
        TaskList.NESTED_CLASS -> doNestedClass()
        TaskList.INNER_CLASS -> doInnerClass()
        TaskList.SINGLETON_CLASS -> doSingletonClass()
        TaskList.EQUALITY_CHECK -> doEqualsOperator()
        TaskList.GENERIC -> doGeneric()
        TaskList.GENERIC_EXTENSION -> "".doGenericExtension("Loki")
        TaskList.INFIX_FUNCTION -> doInfix()
        TaskList.INLINE_FUNCTION -> doInline()*/
        else -> ""
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    InterviewAssessmentTheme {
        Greeting("Android")
    }
}

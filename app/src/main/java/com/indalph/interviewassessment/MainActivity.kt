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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.indalph.interviewassessment.ui.theme.InterviewAssessmentTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InterviewAssessmentTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ExpandableList(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


enum class ParentList {
    VARIABLES, CLASS, STRING
}

fun getParent(): List<String> =
    listOf(ParentList.VARIABLES.name, ParentList.CLASS.name, ParentList.STRING.name)

data class Data(val parent: String, val message: String, val function: () -> String)

fun getVariablesList() = listOf(
    Data(ParentList.VARIABLES.name, "Data Type Size", ::dataTypeSize),
    Data(ParentList.VARIABLES.name, "Variable Type", ::doVariableType),
    Data(ParentList.VARIABLES.name, "Lazy (by lazy{})", ::doLazy),
    Data(ParentList.VARIABLES.name, "Late Init", ::doLateInit),
    Data(ParentList.VARIABLES.name, "Nullable", ::doNullable),
    Data(ParentList.VARIABLES.name, "Safe Call (?.)", ::doSafeCall),
    Data(ParentList.VARIABLES.name, "Elvis Operator (?:)", ::doElvis),
    Data(ParentList.VARIABLES.name, "Double Bang (!!)", ::doDoubleBang),
    Data(ParentList.VARIABLES.name, "by keyword", ::doBy),
)

fun getClassList() = listOf(
    Data(ParentList.CLASS.name, "DoLazy", ::dataTypeSize)
)

fun getStringList() = listOf(
    Data(ParentList.STRING.name, "DoLazy", ::dataTypeSize),

    )

fun child(): List<Data> {
    val mutableChild = mutableListOf<Data>()
    mutableChild.addAll(getVariablesList())
    mutableChild.addAll(getClassList())
    mutableChild.addAll(getStringList())
    return mutableChild

}

@Composable
fun ExpandableList(modifier: Modifier) {
    val parentItems = remember { getParent() }
    val childItems = remember { child() }
    val expandedState = remember { mutableStateMapOf<String, Boolean>() }
    var selectedURL by remember { mutableStateOf("https://www.google.com") }
    Column(modifier = modifier) {

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(top = 20.dp, bottom = 20.dp)

        ) {
            parentItems.forEach { parent ->
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
                        val children = childItems.filter { it.parent == parent }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 32.dp)
                        ) {
                            children.forEach { child ->
                                Box(modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        selectedURL = child.function.invoke()
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    InterviewAssessmentTheme {
        ExpandableList(modifier = Modifier.fillMaxSize())
    }
}

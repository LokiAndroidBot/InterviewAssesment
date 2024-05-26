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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Logger
import com.indalph.interviewassessment.parent.dataTypeSize
import com.indalph.interviewassessment.parent.doAnyType
import com.indalph.interviewassessment.parent.doBy
import com.indalph.interviewassessment.parent.doDataClass
import com.indalph.interviewassessment.parent.doDiffEquality
import com.indalph.interviewassessment.parent.doDoubleBang
import com.indalph.interviewassessment.parent.doElvis
import com.indalph.interviewassessment.parent.doEnumClass
import com.indalph.interviewassessment.parent.doExtensionString
import com.indalph.interviewassessment.parent.doInit
import com.indalph.interviewassessment.parent.doInnerClass
import com.indalph.interviewassessment.parent.doIt
import com.indalph.interviewassessment.parent.doLateInit
import com.indalph.interviewassessment.parent.doLazy
import com.indalph.interviewassessment.parent.doMutableAndImmutable
import com.indalph.interviewassessment.parent.doMutableImmutableList
import com.indalph.interviewassessment.parent.doNestedClass
import com.indalph.interviewassessment.parent.doNullable
import com.indalph.interviewassessment.parent.doPrivateVisibility
import com.indalph.interviewassessment.parent.doProtectedVisibility
import com.indalph.interviewassessment.parent.doPublicVisibility
import com.indalph.interviewassessment.parent.doSafeCall
import com.indalph.interviewassessment.parent.doVariableType
import com.indalph.interviewassessment.ui.theme.InterviewAssessmentTheme
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.reflect.full.memberProperties


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseDatabase.getInstance().setLogLevel(Logger.Level.DEBUG)
        enableEdgeToEdge()
        setContent {
            val viewModel: MainViewModel = viewModel()
            val state by viewModel.state.collectAsState()

            LaunchedEffect(Unit) {
                viewModel.intents.send(MainIntent.LoadData)
            }

            MainScreen(state, viewModel)
        }
    }
}

@Composable
fun MainScreen(state: MainState, mainViewModel: MainViewModel) {
    when (state) {
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
}


enum class ParentList {
    BASIC, VARIABLES, CLASS, FUNCTION, STRING,
}

fun getParent(): List<String> = listOf(
    ParentList.BASIC.name,
    ParentList.VARIABLES.name,
    ParentList.CLASS.name,
    ParentList.FUNCTION.name,
    ParentList.STRING.name
)

data class Data(val parent: String, val message: String, val function: () -> String)

fun getBasicList() = listOf(
    Data(ParentList.BASIC.name, "Visibility (Public)", ::doPublicVisibility),
    Data(ParentList.BASIC.name, "Visibility (Private)", ::doPrivateVisibility),
    Data(ParentList.BASIC.name, "Visibility (Protected)", ::doProtectedVisibility),
    Data(ParentList.BASIC.name, "Diff == & ===", ::doDiffEquality),
    Data(ParentList.BASIC.name, "Init Block", ::doInit)
)


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
    Data(ParentList.VARIABLES.name, "it keyword", ::doIt),
    Data(ParentList.VARIABLES.name, "Mutable & Immutable", ::doMutableAndImmutable),
    Data(ParentList.VARIABLES.name, "MutableList & ImmutableList", ::doMutableImmutableList),
    Data(ParentList.VARIABLES.name, "Any (Variables & Member Function", ::doAnyType),
)


fun getClassList() = listOf(
    Data(ParentList.CLASS.name, "Data Class", ::doDataClass),
    Data(ParentList.CLASS.name, "Enum Class", ::doEnumClass),
    Data(ParentList.CLASS.name, "Nested Class", ::doNestedClass),
    Data(ParentList.CLASS.name, "Inner Class", ::doInnerClass)
)

fun getStringList() = listOf(
    Data(
        ParentList.STRING.name, "DoLazy", ::dataTypeSize
    )
)

fun getFunctionList() = listOf(
    Data(ParentList.FUNCTION.name, "Extension (String)", ::doExtensionString)
)


fun child(): List<Data> {
    val mutableChild = mutableListOf<Data>()
    mutableChild.addAll(getVariablesList())
    mutableChild.addAll(getClassList())
    mutableChild.addAll(getStringList())
    mutableChild.addAll(getFunctionList())
    mutableChild.addAll(getBasicList())

    return mutableChild

}

@Composable
fun ExpandableList(response: List<String>) {
    var selectedURL by remember { mutableStateOf("") }
    Column(modifier = Modifier) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
                .padding(top = 20.dp, bottom = 20.dp)
        ) {
            response.forEach { parent ->
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        selectedURL = parent
                    }) {
                    Text(text = parent, fontSize = 20.sp, modifier = Modifier
                        .clickable {
                        }
                        .padding(8.dp))
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

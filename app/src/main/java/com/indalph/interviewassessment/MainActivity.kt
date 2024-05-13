package com.indalph.interviewassessment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.indalph.interviewassessment.ui.theme.InterviewAssessmentTheme
import kotlin.reflect.full.*
import kotlin.reflect.KFunction
import kotlin.reflect.jvm.javaMethod
import kotlin.reflect.jvm.reflect


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
    // Define a list of TaskList items
    val taskListItems = TaskList.entries
    var text by remember { mutableStateOf("") }

    LazyColumn(modifier = modifier.fillMaxHeight(0.7f)) {
        items(taskListItems) { taskListItem ->
            Box(Modifier.clickable { text = doWork(taskListItem) }) {
                Text(text = taskListItem.name, modifier = Modifier.padding(16.dp))
            }
        }
    }
    Text(modifier = Modifier.fillMaxHeight(0.3f), text = text)
}

enum class TaskList {
    DATATYPE_SIZE, LAZY, LATE_INIT, DOUBLE_BANG_OPERATOR, NULLABLE_OPERATOR, ELVIS_OPERATOR, ANY_TYPE, MUTABLE_IMMUTABLE, MUTABLE_IMMUTABLE_LIST, STRING_REVERSE, CHAR_OCCURRENCE, DATA_CLASS, ENUM_CLASS, NESTED_CLASS, INNER_CLASS, SINGLETON_CLASS, INIT_BLOCK, EQUALITY_CHECK, GENERIC, GENERIC_EXTENSION, INFIX_FUNCTION, INLINE_FUNCTION
}

/*fun generateFunctionCode(function: () -> Unit): String {

    val funSpec = FunSpec.builder(functionName.)
        .addCode("println(\"This is an example function.\")") // You can add any custom code here
        .build()

    val fileSpec =
        FileSpec.builder("generated", functionName.toString()).addFunction(funSpec).build()

    return fileSpec.toString()
}*/
fun doWork(data: TaskList): String {

    return ""/*return when (data) {
        TaskList.DATATYPE_SIZE -> generateFunctionCode { ::dataTypeSize } TaskList.LAZY -> doLazy()
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
        TaskList.INLINE_FUNCTION -> doInline()
        else -> ""
    }*/
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    InterviewAssessmentTheme {
        Greeting("Android")
    }
}

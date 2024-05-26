package com.indalph.interviewassessment

sealed class MainIntent {
    object LoadData : MainIntent()
    data class LoadChildData(var childItem: List<String>) : MainIntent()
}

sealed class MainState {
    object Idle : MainState()
    object Loading : MainState()
    data class Data(val response: Response) : MainState()
    data class CHILD(val response: List<String>) : MainState()
    data class Error(val error: String) : MainState()
}

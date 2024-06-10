package com.indalph.interviewassessment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.gson.Gson
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val intents = Channel<MainIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<MainState>(MainState.Idle)
    val state: StateFlow<MainState> get() = _state

    private var combinedData: Response? = null

    init {
        handleIntents()
    }

    private fun handleIntents() {
        viewModelScope.launch {
            intents.consumeAsFlow().collect {
                when (it) {
                    is MainIntent.LoadData -> fetchData()
                }
            }
        }
    }

    private fun fetchData() {

        _state.value = MainState.Loading
        val database =
            FirebaseDatabase.getInstance("https://interviewassessment-21335-default-rtdb.asia-southeast1.firebasedatabase.app")
        val myRef = database.getReference("data")
        myRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val json: String = Gson().toJson(dataSnapshot.value)
                combinedData = Gson().fromJson(json, Response::class.java)
                if (combinedData != null) {
                    println("Data retrieved successfully: $combinedData")
                    // Process the retrieved data as needed
                } else {
                    println("CombinedData is null")
                }
                val parentArray = arrayListOf("BASIC", "VARIABLES", "CLASS", "FUNCTION", "STRING")
                (combinedData?.parent?.addAll(parentArray))
                _state.value =
                    combinedData?.let { MainState.Data(it) } ?: MainState.Error("Data is null")
            }

            override fun onCancelled(databaseError: DatabaseError) {
                _state.value = MainState.Error(databaseError.message)
            }
        })
    }


}

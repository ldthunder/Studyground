package com.example.sg.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class CoroutinesViewModel @Inject constructor() : ViewModel() {
    private var myMatrix = listOf(mutableListOf(0,0,0), mutableListOf(0,0,0), mutableListOf(0,0,0))
    // LiveData
    private val _state = MutableLiveData<String>()
    val state: LiveData<String>
        get() = _state

    suspend fun startCoroutine(){
        withContext(Dispatchers.Default){
            do {
                val randomNumber = Random.nextInt(Random.nextInt(0, 12), Random.nextInt(12, 123))
                // Increment first diagonal
                myMatrix[0][0] = myMatrix[0][0] + randomNumber
                myMatrix[1][1] = myMatrix[1][1] + randomNumber
                myMatrix[2][2] = myMatrix[2][2] + randomNumber
                // Increment second diagonal
                myMatrix[0][2] = myMatrix[0][2] + randomNumber
                myMatrix[1][1] = myMatrix[1][1] + randomNumber
                myMatrix[2][0] = myMatrix[2][0] + randomNumber
                // Post the new matrix
                _state.postValue(myMatrix.toProperString())
                delay(20)
            } while (true)
        }
    }

}

private fun <E> List<MutableList<E>>.toProperString(): String {
    return """${this[0][0]} ${this[0][1]} ${this[0][2]}
            |${this[1][0]} ${this[1][1]} ${this[1][2]}
            |${this[2][0]} ${this[2][1]} ${this[2][2]}""".trimMargin()
}

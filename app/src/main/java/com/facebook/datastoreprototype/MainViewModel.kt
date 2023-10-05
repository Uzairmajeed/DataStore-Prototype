package com.facebook.datastoreprototype

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//This defines a class named MainViewModel that extends AndroidViewModel and takes an Application as a constructor parameter.
//This ViewModel is designed to be used with an Android application and it has access to the application context.
class MainViewModel(application: Application): AndroidViewModel(application) {

    private val repository = ProtoRepository(application)

    val firstName = repository.readProto.asLiveData()//This creates a LiveData named firstName that holds the result of the readProto flow from the ProtoRepository.
    // This allows UI components to observe changes in the first name.


    //This is a function for updating the values in the data store.
    fun updateValue(firstName: String, lastName: String, age: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateValue(firstName, lastName, age)
    }
}

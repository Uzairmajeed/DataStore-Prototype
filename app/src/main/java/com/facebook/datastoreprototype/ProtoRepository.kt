package com.facebook.datastoreprototype

import android.content.Context
import android.util.Log
import androidx.datastore.DataStore
import androidx.datastore.createDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.io.IOException

//This defines a class named ProtoRepository that takes a Context as a constructor parameter.
// The Context is used for creating the DataStore.
class ProtoRepository(context: Context) {

    private val dataStore: DataStore<Person> = context.createDataStore(
        "my_data",
        serializer = MySerializer()//specifies a custom serializer MySerializer for handling serialization and deserialization.
    )
//This creates a Flow named readProto that emits Person objects.
    val readProto: Flow<Person> = dataStore.data
        .catch { exception->
            if(exception is IOException){
                Log.d("Error", exception.message.toString())
                emit(Person.getDefaultInstance())
            }else{
                throw exception
            }
        }
//This is a suspend function for updating the values in the data store. It takes
    suspend fun updateValue(firstName: String, lastName: String, age: Int){
        dataStore.updateData { preference->
            preference.toBuilder().setFirstName(firstName).setLastName(lastName).setAge(age).build()
        }
    }

}

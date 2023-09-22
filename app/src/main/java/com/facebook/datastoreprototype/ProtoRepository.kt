package com.facebook.datastoreprototype

import android.content.Context
import android.util.Log
import androidx.datastore.DataStore
import androidx.datastore.createDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.io.IOException


class ProtoRepository(context: Context) {

    private val dataStore: DataStore<Person> = context.createDataStore(
        "my_data",
        serializer = MySerializer()
    )

    val readProto: Flow<Person> = dataStore.data
        .catch { exception->
            if(exception is IOException){
                Log.d("Error", exception.message.toString())
                emit(Person.getDefaultInstance())
            }else{
                throw exception
            }
        }

    suspend fun updateValue(firstName: String, lastName: String, age: Int){
        dataStore.updateData { preference->
            preference.toBuilder().setFirstName(firstName).setLastName(lastName).setAge(age).build()
        }
    }

}

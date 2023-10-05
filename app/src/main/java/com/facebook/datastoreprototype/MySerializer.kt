package com.facebook.datastoreprototype

import androidx.datastore.CorruptionException
import androidx.datastore.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

//This defines a class named MySerializer that implements the Serializer interface for the Person class.
// It means that MySerializer is responsible for converting Person objects to and from their serialized form.
class MySerializer : Serializer<Person> {
    //It takes an InputStream as input and returns a Person object.
    // It attempts to parse a Person object from the input stream and handles any potential exceptions.
    override fun readFrom(input: InputStream): Person {
        try {
            return Person.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }
//It takes a Person object t and an OutputStream as input.
// It writes the serialized form of the Person object to the output stream.
    override fun writeTo(t: Person, output: OutputStream) {
        t.writeTo(output)
    }
}


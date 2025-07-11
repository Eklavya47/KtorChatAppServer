package com.example.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class Message(
    val text: String,
    val userName: String,
    val timeStamp: Long,
    @SerialName("_id")
    val id: String = ObjectId().toString()
)

package com.example.data

import com.example.data.model.Message
import com.mongodb.client.model.Sorts
import com.mongodb.kotlin.client.coroutine.MongoCollection
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.withContext

class MessageDataSourceImpl(
    private val db: MongoDatabase
): MessageDataSource {

    private suspend fun getMessageCollection(): MongoCollection<Message>{
        return withContext(Dispatchers.IO){
            db.getCollection("messages", Message::class.java)
        }
    }

    override suspend fun getAllMessages(): List<Message> {
        val messages = getMessageCollection()
        return withContext(Dispatchers.IO){
            messages.find()
                .sort(Sorts.descending("timestamp"))
                .toList()
        }
    }

    override suspend fun insertMessage(message: Message) {
        val messages = getMessageCollection()
        withContext(Dispatchers.IO){
            messages.insertOne(message)
        }
    }
}
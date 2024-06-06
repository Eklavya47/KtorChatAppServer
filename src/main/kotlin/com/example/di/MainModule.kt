package com.example.di

import com.example.data.MessageDataSource
import com.example.data.MessageDataSourceImpl
import com.example.room.RoomController
import com.mongodb.kotlin.client.coroutine.MongoClient
import org.koin.dsl.module

val mainModule = module {
    single {
        MongoClient.create().getDatabase("message_db")
    }
    single<MessageDataSource> {
        MessageDataSourceImpl(get())
    }
    single {
        RoomController(get())
    }
}
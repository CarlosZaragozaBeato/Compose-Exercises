package com.example.areader.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.PropertyName


data class MBook(
    @Exclude var id:String? = null,
    var title:String? = null,
    var authors: String? = null,
    var description:String? = null,
    var notes: String? = null,
    @get:PropertyName("book_photo_url")
    @set:PropertyName("book_photo_url")
    var photoUr:String? =null,
    var categories:String? = null,

    @get:PropertyName("publish_date")
    @set:PropertyName("publish_date")
    var publishedDate:String? = null,
    var rating:Double? = null,

    @get:PropertyName("page_count")
    @set:PropertyName("page_count")
    var pageCount:String? = null,

    @get:PropertyName("started_reading")
    @set:PropertyName("started_reading")
    var startedReading: Timestamp? = null,

    @get:PropertyName("finished_reading")
    @set:PropertyName("finished_reading")
    var finishedReading:Timestamp? = null,

    @get:PropertyName("user_id")
    @set:PropertyName("user_id")
    var userId:String? = null,

    @get:PropertyName("book_id")
    @set:PropertyName("book_id")
    var googleBookId:String? = null,



    )

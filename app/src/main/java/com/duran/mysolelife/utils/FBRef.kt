package com.duran.mysolelife.utils

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FBRef {

    companion object {

        val database = Firebase.database

        val bookmarkRef = database.getReference("bookmark_list")

    }

}
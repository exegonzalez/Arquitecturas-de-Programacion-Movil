package com.example.tp_final_mobile.network

import com.example.tp_final_mobile.model.BeeUser
import kotlin.collections.List
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

const val BEE_USERS_COLLECTION_NAME = "beeusers"

class FirestoreService {
    val firebaseFirestore = FirebaseFirestore.getInstance()
    val setting = FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build()

    init {
        firebaseFirestore.firestoreSettings = setting
    }

    fun getBeeUsers(callback: Callback<List<BeeUser>>) {
            firebaseFirestore.collection(BEE_USERS_COLLECTION_NAME)
                .orderBy("name")
                .get()
                .addOnSuccessListener { result ->
                    for (doc in result) {
                        // obtengo una lista el resultado de firestore
                        val list = result.toObjects(BeeUser:: class.java) // tenemos toda la lista de las collection
                        callback.onSucces(list)
                        break
                    }
                }
        }
}
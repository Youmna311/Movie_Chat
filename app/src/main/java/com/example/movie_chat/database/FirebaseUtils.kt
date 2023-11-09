package com.example.movie_chat.database

import com.example.movie_chat.modelclasses.AppUser
import com.example.movie_chat.modelclasses.Message
import com.example.movie_chat.modelclasses.Room
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

fun getCollection(CollectionName:String) :CollectionReference{
    val db = Firebase.firestore
    return db.collection(CollectionName)
}


//users
fun addusertoFireStore(user: AppUser, onsuccessListener: OnSuccessListener<Void>,
                       onfailureListener: OnFailureListener){

    val userCollesction = getCollection(AppUser.COLLECTION_NAME)
    val userDocument = userCollesction.document(user.id!!) //hena ana kont b generate el document bnfs el user id f kont bdelo el userid
    userDocument.set(user)
        .addOnSuccessListener(onsuccessListener)
        .addOnFailureListener(onfailureListener)
}

fun signinwithUser(uid:String,onsuccessListener: OnSuccessListener<DocumentSnapshot>,onfailureListener: OnFailureListener){
    val userCollesction= getCollection(AppUser.COLLECTION_NAME)
    userCollesction.document(uid).get()
        .addOnSuccessListener (onsuccessListener)
        .addOnFailureListener(onfailureListener)
}


//rooms
fun addroomtoFireStore(room:Room, onsuccessListener: OnSuccessListener<Void>, onfailureListener: OnFailureListener){

    val roomCollection = getCollection(Room.COLLECTION_NAME)
    val roomDocument =roomCollection.document()  // hena el document id gem=nerate automatically and set room id equal it
    room.id = roomDocument.id

    roomDocument.set(room)
        .addOnSuccessListener(onsuccessListener)
        .addOnFailureListener(onfailureListener)






}

fun getroomFromFireStore(onsuccessListener: OnSuccessListener<QuerySnapshot>,onfailureListener: OnFailureListener){

    val roomCollection = getCollection(Room.COLLECTION_NAME)
    roomCollection.get()
        .addOnSuccessListener (onsuccessListener)
        .addOnFailureListener(onfailureListener)



}

// messages

fun getMessageRef(roomId: String):CollectionReference{
    // function that take room id to return collection reference of messages of that  belongs to this room id
    val messageCollectionRef = getCollection(Room.COLLECTION_NAME)
    val messageDoc = messageCollectionRef.document(roomId!!)
    return messageDoc.collection(Message.COLLECTION_NAME)
}
fun addmessagestoFireStore(message: Message , onsuccessListener: OnSuccessListener<Void>, onfailureListener: OnFailureListener){

    val messageCollectionRef = getMessageRef(message.roomId!!)
    val messageRef = messageCollectionRef.document() // kda hwa 7tly random id ll message
    message.id = messageRef.id
    messageRef.set(message)
        .addOnSuccessListener(onsuccessListener)
        .addOnFailureListener(onfailureListener)



}
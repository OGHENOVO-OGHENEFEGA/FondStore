package com.fondstore.profile.data.local

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.BsonObjectId
import org.mongodb.kbson.ObjectId

class ProfileObject : RealmObject {
    @PrimaryKey
    var _id: ObjectId = BsonObjectId()
    var id: Int = -1
    var image: String = ""
    var username: String = ""
    var email: String = ""
    var firstname: String = ""
    var lastname: String = ""
    var phoneNumber: String = ""
}
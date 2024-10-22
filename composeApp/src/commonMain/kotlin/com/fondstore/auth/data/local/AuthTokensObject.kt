package com.fondstore.auth.data.local

import com.fondstore.auth.domain.models.AuthTokensState
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class AuthTokensObject : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var access: String = ""
    var refresh: String = ""
    var state = AuthTokensState.UNAVAILABLE.name
}
package com.fondstore.launcher.data.local

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class LauncherObject : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var isUserOnboard: Boolean = false
}
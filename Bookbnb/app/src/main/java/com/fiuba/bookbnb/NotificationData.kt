package com.fiuba.bookbnb

import java.io.Serializable

data class NotificationData(val userId: String, val msgTitle: String, val msgBody: String) : Serializable
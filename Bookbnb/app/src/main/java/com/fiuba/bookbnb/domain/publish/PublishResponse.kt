package com.fiuba.bookbnb.domain.publish

import java.io.Serializable

class PublishResponse(val userId: String, val price: String, val date: String, val type: String,
                      val id: String, val isBlocked: Boolean, val title: String, val description: String) : Serializable
package com.fiuba.bookbnb

object GuestAndHost {
    private var guest: String? = null
    private var host : String? = null

    fun setGuest(guest: String) {
        GuestAndHost.guest = guest
    }

    fun getGuest() = GuestAndHost.guest

    fun setHost(host: String) {
        GuestAndHost.host = host
    }

    fun getHost() = GuestAndHost.host
}
package com.fondstore.helpCentre.utils

expect class HelpCentreManager {
    fun openEmail(address: String)
    fun openPhone(number: String)
    fun openAddress(address: String)
    fun openUrl(url: String)
}
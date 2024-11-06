package com.fondstore.helpCentre.utils

import android.content.Context
import android.content.Intent
import android.net.Uri

actual class HelpCentreManager(private val context: Context) {
    actual fun openEmail(address: String) {
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("mailto:$address")))
    }

    actual fun openPhone(number: String) {
        context.startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number")))
    }

    actual fun openAddress(address: String) {
        context.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.co.in/maps?q=$address")
            )
        )
    }

    actual fun openUrl(url: String) {
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }
}
package com.fondstore.helpCentre.utils

import platform.Foundation.NSURL
import platform.UIKit.UIApplication

actual class HelpCentreManager {
    actual fun openEmail(address: String) {
        val emailUrl = NSURL.URLWithString("mailto:$address")

        if (emailUrl != null && UIApplication.sharedApplication.canOpenURL(emailUrl)) {
            UIApplication.sharedApplication.openURL(emailUrl)
        }
    }

    actual fun openPhone(number: String) {
        val phoneUrl = NSURL.URLWithString("tel:$number")

        if (phoneUrl != null && UIApplication.sharedApplication.canOpenURL(phoneUrl)) {
            UIApplication.sharedApplication.openURL(phoneUrl)
        }
    }

    actual fun openAddress(address: String) {
        val mapsUrl = NSURL.URLWithString("http://maps.apple.com/?q=$address")

        if (mapsUrl != null && UIApplication.sharedApplication.canOpenURL(mapsUrl)) {
            UIApplication.sharedApplication.openURL(mapsUrl)
        }
    }

    actual fun openUrl(url: String) {
        val webUrl = NSURL.URLWithString(url)

        if (webUrl != null && UIApplication.sharedApplication.canOpenURL(webUrl)) {
            UIApplication.sharedApplication.openURL(webUrl)
        }
    }
}
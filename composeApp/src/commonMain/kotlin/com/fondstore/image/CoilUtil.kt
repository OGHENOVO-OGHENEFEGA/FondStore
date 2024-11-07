package com.fondstore.image

import androidx.compose.runtime.Composable
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.request.CachePolicy
import coil3.svg.SvgDecoder

@Composable
fun WithCoilConfig(content: @Composable () -> Unit) {
    setSingletonImageLoaderFactory { context ->
        ImageLoader.Builder(context).diskCachePolicy(CachePolicy.DISABLED).components {
            add(SvgDecoder.Factory())
        }.build()
    }

    content()
}
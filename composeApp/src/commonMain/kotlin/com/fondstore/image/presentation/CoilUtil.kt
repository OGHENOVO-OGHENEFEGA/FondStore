package com.fondstore.image.presentation

import androidx.compose.runtime.Composable
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.svg.SvgDecoder

@Composable
fun WithCoilConfig(content: @Composable () -> Unit) {
    setSingletonImageLoaderFactory { context ->
        ImageLoader.Builder(context).components {
            add(SvgDecoder.Factory())
        }.build()
    }

    content()
}
package com.fondstore.product.domain.models

import fondstore.composeapp.generated.resources.Res
import fondstore.composeapp.generated.resources.best_deals
import fondstore.composeapp.generated.resources.best_deals_description
import fondstore.composeapp.generated.resources.explore
import fondstore.composeapp.generated.resources.explore_description
import fondstore.composeapp.generated.resources.new_arrivals
import fondstore.composeapp.generated.resources.new_arrivals_description
import fondstore.composeapp.generated.resources.popular
import fondstore.composeapp.generated.resources.popular_description
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.StringResource

@Serializable
enum class ProductGroup(val title: StringResource, val description: StringResource) {
    EXPLORE(title = Res.string.explore, description = Res.string.explore_description),
    BEST_DEALS(title = Res.string.best_deals, description = Res.string.best_deals_description),
    POPULAR(title = Res.string.popular, description = Res.string.popular_description),
    NEW_ARRIVALS(
        title = Res.string.new_arrivals,
        description = Res.string.new_arrivals_description
    );
}
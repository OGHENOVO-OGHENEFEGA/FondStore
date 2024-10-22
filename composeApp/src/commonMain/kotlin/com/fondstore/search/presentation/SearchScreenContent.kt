package com.fondstore.search.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.fondstore.core.presentation.BackNavTopAppBar
import com.fondstore.core.presentation.screenBackground
import com.fondstore.core.presentation.screenPadding
import com.fondstore.image.presentation.DrawablePaths
import com.fondstore.resources.presentation.fontFamilyResource
import com.fondstore.ui.presentation.appColors
import fondstore.composeapp.generated.resources.DMSans_Regular
import fondstore.composeapp.generated.resources.Res
import fondstore.composeapp.generated.resources.search
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SearchScreenContent(
    state: SearchScreenState,
    onEvent: (SearchScreenEvent) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {

            BackNavTopAppBar(
                onNavigateBack = {
                    onEvent(SearchScreenEvent.CloseScreen)
                },
                actions = {
                    AsyncImage(
                        model = Res.getUri(DrawablePaths.SEARCH),
                        contentDescription = stringResource(Res.string.search),
                        modifier = Modifier.size(18.dp),
                        colorFilter = ColorFilter.tint(MaterialTheme.appColors.color100)
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().screenBackground().padding(innerPadding)
                .screenPadding()
        ) {
            val focusManager = LocalFocusManager.current

            OutlinedTextField(
                value = state.query,
                onValueChange = { query ->
                    onEvent(SearchScreenEvent.SetSearchQuery(query))
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = stringResource(Res.string.search)
                    )
                },
                placeholder = {
                    Text(
                        text = stringResource(Res.string.search),
                        fontFamily = fontFamilyResource(Res.font.DMSans_Regular),
                        fontSize = 16.sp
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions {
                    focusManager.clearFocus()
                    onEvent(SearchScreenEvent.SearchProducts)
                },
                maxLines = 1,
                singleLine = true,
                shape = CircleShape,
                colors = OutlinedTextFieldDefaults.searchTextFieldColors()
            )

            Spacer(modifier = Modifier.height(20.dp))

//            if (productsState.searchedProducts != null) {
//                ProductLazyColumn(
//                    products = productsState.searchedProducts,
//                    modifier = Modifier.fillMaxSize(),
//                    isLoading = productsState.isSearchingProducts,
//                    favourites = favouritesState.favourites,
//                    favouritesRequestLoadingList = favouritesState.requestLoadingList,
//                    onToggleProductFavouritesState = { id ->
//                        onEvent(SearchScreenEvent.ToggleProductFavouritesState(id))
//                    },
//                    onProductSelected = { product ->
//                        onEvent(
//                            SearchScreenEvent
//                                .Navigate(SearchScreenDestination.ProductScreen(productId = product.id))
//                        )
//                    }
//                )
//            }
        }
    }
}

@Composable
private fun OutlinedTextFieldDefaults.searchTextFieldColors(): TextFieldColors {
    return colors(
        disabledContainerColor = MaterialTheme.appColors.searchTextFieldDisabledContainerColor,
        unfocusedContainerColor = MaterialTheme.appColors.searchTextFieldDisabledContainerColor,
        focusedContainerColor = Color.Transparent,
        disabledBorderColor = Color.Transparent,
        unfocusedBorderColor = Color.Transparent,
        focusedBorderColor = MaterialTheme.appColors.color50,
        disabledLeadingIconColor = MaterialTheme.appColors.color30,
        unfocusedLeadingIconColor = MaterialTheme.appColors.color30,
        focusedLeadingIconColor = MaterialTheme.appColors.color30,
        disabledPlaceholderColor = MaterialTheme.appColors.color30,
        unfocusedPlaceholderColor = MaterialTheme.appColors.color30,
        focusedPlaceholderColor = MaterialTheme.appColors.color30,
        cursorColor = MaterialTheme.appColors.color100,
        disabledTextColor = MaterialTheme.appColors.color100,
        unfocusedTextColor = MaterialTheme.appColors.color100,
        focusedTextColor = MaterialTheme.appColors.color100,
    )
}
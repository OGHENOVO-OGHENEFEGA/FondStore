package com.fondstore.profile.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fondstore.core.domain.utils.isInvalidNumber
import com.fondstore.core.domain.utils.isValidNumber
import com.fondstore.core.presentation.CircleLoadingAnimation
import com.fondstore.core.presentation.LoadingText
import com.fondstore.profile.presentation.components.ProfileImage
import com.fondstore.profile.presentation.utils.ProfileScreenUtil
import com.fondstore.resources.presentation.fontFamilyResource
import com.fondstore.ui.presentation.appColors
import fondstore.composeapp.generated.resources.DMSans_Medium
import fondstore.composeapp.generated.resources.DMSans_Regular
import fondstore.composeapp.generated.resources.Res
import fondstore.composeapp.generated.resources.done
import fondstore.composeapp.generated.resources.edit
import fondstore.composeapp.generated.resources.email_address
import fondstore.composeapp.generated.resources.first_name
import fondstore.composeapp.generated.resources.last_name
import fondstore.composeapp.generated.resources.mobile_number
import fondstore.composeapp.generated.resources.name
import fondstore.composeapp.generated.resources.personal_information
import fondstore.composeapp.generated.resources.sign_in
import fondstore.composeapp.generated.resources.sign_out
import fondstore.composeapp.generated.resources.username_placeholder
import org.jetbrains.compose.resources.stringResource

@Composable
fun ProfileScreenContent(state: ProfileScreenState, onEvent: (ProfileScreenEvent) -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val profile = state.result?.dataOrNull

        if (profile == null) {
            Button(
                onClick = {
                    onEvent(ProfileScreenEvent.Navigate(ProfileScreenDestination.AuthScreen))
                },
                modifier = Modifier.height(45.dp)
            ) {
                Text(
                    text = stringResource(Res.string.sign_in),
                    fontFamily = fontFamilyResource(Res.font.DMSans_Medium),
                    fontSize = 14.sp,
                    modifier = Modifier.padding(horizontal = 15.dp)
                )
            }
        } else {
            val isProfileRequestLoading =
                state.isGettingProfile || state.isUpdatingProfile

            val isInEditMode = state.isInEditMode

            Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
                Row(
                    modifier = Modifier.fillMaxWidth().height(75.dp),
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    ProfileImage(size = 75.dp) {

                    }

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        LoadingText(
                            isLoading = false,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            val name = "${profile.firstname} ${profile.lastname}".ifBlank {
                                stringResource(Res.string.name)
                            }

                            Text(
                                text = name,
                                color = MaterialTheme.appColors.color100,
                                fontFamily = fontFamilyResource(Res.font.DMSans_Medium),
                                fontSize = 18.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }

                        LoadingText(
                            isLoading = isProfileRequestLoading && profile.username.isBlank(),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            val username = profile.username.ifBlank {
                                stringResource(Res.string.username_placeholder)
                            }

                            Text(
                                text = username,
                                color = MaterialTheme.appColors.color50,
                                fontFamily = fontFamilyResource(Res.font.DMSans_Regular),
                                fontSize = 14.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(Res.string.personal_information),
                        color = MaterialTheme.appColors.color100,
                        fontFamily = fontFamilyResource(Res.font.DMSans_Medium),
                        fontSize = 20.sp
                    )

                    Button(
                        onClick = {
                            if (
                                isInEditMode
                                && (state.firstname != profile.firstname
                                        || state.lastname != profile.lastname
                                        || state.phoneNumber != profile.phoneNumber)
                            ) {
                                if (state.phoneNumber.isBlank() || state.phoneNumber.isValidNumber()) {
                                    onEvent(ProfileScreenEvent.UploadProfile)
                                }
                            } else {
                                onEvent(ProfileScreenEvent.ToggleEditMode)
                            }
                        },
                        enabled = !isProfileRequestLoading,
                        border = BorderStroke(1.dp, MaterialTheme.appColors.color50),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = MaterialTheme.appColors.color50
                        )
                    ) {
                        val stringResource = if (isInEditMode) Res.string.done else Res.string.edit

                        val icon = if (isInEditMode) {
                            Icons.Filled.Done
                        } else {
                            Icons.Filled.Edit
                        }

                        Text(
                            text = stringResource(stringResource),
                            fontFamily = fontFamilyResource(Res.font.DMSans_Medium),
                            fontSize = 12.sp
                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        if (isProfileRequestLoading) {
                            CircleLoadingAnimation(
                                modifier = Modifier.size(12.dp),
                                strokeWidth = 2.dp
                            )
                        } else {
                            Icon(
                                imageVector = icon,
                                contentDescription = stringResource(Res.string.edit),
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                ProfileInfoItem(
                    name = stringResource(Res.string.email_address),
                    value = profile.email,
                    onValueChange = {
                    },
                    modifier = Modifier.fillMaxWidth(),
                    isInEditMode = false,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                )

                Spacer(modifier = Modifier.height(10.dp))

                ProfileInfoItem(
                    name = stringResource(Res.string.first_name),
                    value = if (isInEditMode) state.firstname else profile.firstname,
                    onValueChange = { name ->
                        onEvent(ProfileScreenEvent.SetFirstName(name))
                    },
                    modifier = Modifier.fillMaxWidth(),
                    isInEditMode = isInEditMode,
                    enabled = isInEditMode && !isProfileRequestLoading,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                )

                Spacer(modifier = Modifier.height(10.dp))

                ProfileInfoItem(
                    name = stringResource(Res.string.last_name),
                    value = if (isInEditMode) state.lastname else profile.lastname,
                    onValueChange = { name ->
                        onEvent(ProfileScreenEvent.SetLastName(name))
                    },
                    modifier = Modifier.fillMaxWidth(),
                    isInEditMode = isInEditMode,
                    enabled = isInEditMode && !isProfileRequestLoading,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                )

                Spacer(modifier = Modifier.height(10.dp))

                val phoneNumber = if (isInEditMode) state.phoneNumber else profile.phoneNumber

                ProfileInfoItem(
                    name = stringResource(Res.string.mobile_number),
                    value = phoneNumber,
                    onValueChange = { number ->
                        if (number.length <= 11) {
                            onEvent(ProfileScreenEvent.SetPhoneNumber(number))
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    isInEditMode = isInEditMode,
                    enabled = isInEditMode && !isProfileRequestLoading,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Phone
                    ),
                    maxTextLength = ProfileScreenUtil.MAX_PHONE_LENGTH,
                    showTextCount = isInEditMode,
                    error = phoneNumber.isInvalidNumber()
                )

                Spacer(modifier = Modifier.height(20.dp))

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = {
                            onEvent(ProfileScreenEvent.ShowSignOutDialog)
                        },
                        modifier = Modifier.height(45.dp)
                    ) {
                        Text(
                            text = stringResource(Res.string.sign_out),
                            fontFamily = fontFamilyResource(Res.font.DMSans_Medium),
                            fontSize = 14.sp,
                            modifier = Modifier.padding(horizontal = 15.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ProfileInfoItem(
    name: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isInEditMode: Boolean = false,
    enabled: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    maxTextLength: Int = Int.MAX_VALUE,
    showTextCount: Boolean = false,
    error: Boolean = false,
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(
            text = name,
            color = MaterialTheme.appColors.color50,
            fontFamily = fontFamilyResource(Res.font.DMSans_Medium),
            fontSize = 14.sp
        )

        val textCount: @Composable (() -> Unit)? = if (showTextCount) {
            @Composable {
                Text(
                    text = "${value.length}/$maxTextLength",
                    fontFamily = fontFamilyResource(Res.font.DMSans_Regular),
                    color = MaterialTheme.appColors.color50
                )
            }
        } else {
            null
        }

        val borderColor =
            if (error) {
                MaterialTheme.colorScheme.error
            } else if (isInEditMode) {
                MaterialTheme.appColors.color30
            } else {
                Color.Transparent
            }

        TextField(
            value = value,
            onValueChange = { newValue ->
                val filteredValue =
                    if (keyboardOptions.keyboardType == KeyboardType.Phone) {
                        newValue.filter {
                            it.isDigit()
                        }
                    } else {
                        newValue
                    }

                if (filteredValue.length in 0..maxTextLength) {
                    onValueChange(filteredValue)
                }
            },
            modifier = Modifier.fillMaxWidth()
                .border(1.dp, borderColor, CardDefaults.shape),
            placeholder = {
                Text(
                    text = name,
                    fontFamily = fontFamilyResource(Res.font.DMSans_Medium),
                    fontSize = 16.sp
                )
            },
            suffix = textCount,
            enabled = enabled,
            maxLines = 1,
            singleLine = true,
            keyboardOptions = keyboardOptions,
            shape = CardDefaults.shape,
            colors = TextFieldDefaults.profileInfoItemTextFieldColors()
        )
    }
}

@Composable
private fun TextFieldDefaults.profileInfoItemTextFieldColors(): TextFieldColors {
    return colors(
        disabledContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,
        focusedContainerColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
        disabledPlaceholderColor = MaterialTheme.appColors.color100,
        unfocusedPlaceholderColor = MaterialTheme.appColors.color100,
        focusedPlaceholderColor = MaterialTheme.appColors.color100,
        disabledTextColor = MaterialTheme.appColors.color100,
        unfocusedTextColor = MaterialTheme.appColors.color100,
        focusedTextColor = MaterialTheme.appColors.color100
    )
}
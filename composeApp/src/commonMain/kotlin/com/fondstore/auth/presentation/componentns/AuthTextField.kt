package com.fondstore.auth.presentation.componentns

import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fondstore.core.presentation.numbClickable
import com.fondstore.resources.fontFamilyResource
import com.fondstore.ui.appColors
import fondstore.composeapp.generated.resources.DMSans_Medium
import fondstore.composeapp.generated.resources.Res
import fondstore.composeapp.generated.resources.hide_password
import fondstore.composeapp.generated.resources.show_password
import org.jetbrains.compose.resources.stringResource

@Composable
fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    readOnly: Boolean = true,
    textAlign: TextAlign = TextAlign.Start,
    placeholder: String? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    error: String? = null,
) {
    val placeholderComposable: (@Composable () -> Unit)? = placeholder?.let { text ->
        @Composable {
            Text(
                text = text,
                modifier = Modifier.fillMaxWidth().basicMarquee(),
                fontFamily = fontFamilyResource(Res.font.DMSans_Medium),
                fontSize = 16.sp,
                textAlign = textAlign
            )
        }
    }

    val supportingText: (@Composable () -> Unit)? = error?.let { text ->
        @Composable {
            Text(
                text = text,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 12.sp,
                textAlign = textAlign
            )
        }
    }

    var textFieldVisualTransformation by remember {
        val transformation =
            if (keyboardOptions.keyboardType == KeyboardType.Password) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            }

        mutableStateOf(transformation)
    }

    val trailingIcon: (@Composable () -> Unit)? =
        if (keyboardOptions.keyboardType == KeyboardType.Password) {
            @Composable {
                val icon =
                    if (textFieldVisualTransformation is PasswordVisualTransformation) {
                        Icons.Outlined.VisibilityOff
                    } else {
                        Icons.Outlined.Visibility
                    }

                val contentDescriptionResource =
                    if (textFieldVisualTransformation is PasswordVisualTransformation) {
                        Res.string.show_password
                    } else {
                        Res.string.hide_password
                    }

                val contentDescription = stringResource(contentDescriptionResource)

                Icon(
                    imageVector = icon,
                    contentDescription = contentDescription,
                    modifier = Modifier.size(24.dp).numbClickable {
                        textFieldVisualTransformation =
                            if (textFieldVisualTransformation is PasswordVisualTransformation) {
                                VisualTransformation.None
                            } else {
                                PasswordVisualTransformation()
                            }
                    }
                )
            }
        } else null

    Column(modifier = modifier) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth()
                .border(1.dp, MaterialTheme.appColors.color30, CircleShape),
            readOnly = readOnly,
            textStyle = LocalTextStyle.current.copy(fontSize = 16.sp, textAlign = textAlign),
            placeholder = placeholderComposable,
            trailingIcon = trailingIcon,
            supportingText = supportingText,
            visualTransformation = textFieldVisualTransformation,
            keyboardOptions = keyboardOptions,
            singleLine = true,
            shape = CircleShape,
            colors = TextFieldDefaults.authTextFieldColors()
        )
    }
}

@Composable
private fun TextFieldDefaults.authTextFieldColors(): TextFieldColors {
    return colors(
        disabledContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,
        focusedContainerColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
        disabledPlaceholderColor = MaterialTheme.appColors.color50.copy(alpha = 0.38f),
        unfocusedPlaceholderColor = MaterialTheme.appColors.color50,
        focusedPlaceholderColor = MaterialTheme.appColors.color50,
        disabledTrailingIconColor = MaterialTheme.appColors.color50.copy(alpha = 0.38f),
        unfocusedTrailingIconColor = MaterialTheme.appColors.color50,
        focusedTrailingIconColor = MaterialTheme.appColors.color50,
        disabledTextColor = MaterialTheme.appColors.color100,
        unfocusedTextColor = MaterialTheme.appColors.color100,
        focusedTextColor = MaterialTheme.appColors.color100,
        disabledSupportingTextColor = MaterialTheme.colorScheme.error,
        unfocusedSupportingTextColor = MaterialTheme.colorScheme.error,
        focusedSupportingTextColor = MaterialTheme.colorScheme.error,
        errorSupportingTextColor = MaterialTheme.colorScheme.error
    )
}


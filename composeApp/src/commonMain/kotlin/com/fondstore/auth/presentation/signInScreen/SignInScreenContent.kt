package com.fondstore.auth.presentation.signInScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fondstore.auth.presentation.componentns.AuthTextField
import com.fondstore.core.presentation.LoadingButton
import com.fondstore.core.presentation.button
import com.fondstore.core.presentation.defaultClickable
import com.fondstore.core.presentation.screenBackground
import com.fondstore.core.presentation.screenPadding
import com.fondstore.resources.fontFamilyResource
import com.fondstore.ui.appColors
import fondstore.composeapp.generated.resources.DMSans_Medium
import fondstore.composeapp.generated.resources.DMSans_Regular
import fondstore.composeapp.generated.resources.Res
import fondstore.composeapp.generated.resources.email_hint
import fondstore.composeapp.generated.resources.forgot_password
import fondstore.composeapp.generated.resources.new_to_fondstore
import fondstore.composeapp.generated.resources.password_hint
import fondstore.composeapp.generated.resources.sign_in
import fondstore.composeapp.generated.resources.sign_up_for_free
import org.jetbrains.compose.resources.stringResource

@Composable
fun SignInScreenContent(state: SignInScreenState, onEvent: (SignInScreenEvent) -> Unit) {
    Column(modifier = Modifier.fillMaxSize().screenBackground().screenPadding()) {
        Text(
            text = stringResource(Res.string.sign_in),
            color = MaterialTheme.appColors.color100,
            fontFamily = fontFamilyResource(Res.font.DMSans_Medium),
            fontSize = 32.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        val newToFondstore = stringResource(Res.string.new_to_fondstore)
        val signUpForFree = stringResource(Res.string.sign_up_for_free)

        val annotatedString = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = MaterialTheme.appColors.color50,
                    fontFamily = fontFamilyResource(Res.font.DMSans_Medium),
                    fontSize = 14.sp
                )
            ) {
                append(newToFondstore)
            }

            append(" ")

            withStyle(
                style = SpanStyle(
                    color = MaterialTheme.colorScheme.primary,
                    fontFamily = fontFamilyResource(Res.font.DMSans_Medium),
                    fontSize = 14.sp
                )
            ) {
                pushStringAnnotation(tag = signUpForFree, annotation = signUpForFree)
                append(signUpForFree)
            }
        }

        ClickableText(
            text = annotatedString,
            onClick = { offset ->
                annotatedString.getStringAnnotations(offset, offset)
                    .firstOrNull()?.let {
                        onEvent(SignInScreenEvent.Navigate(SignInScreenDestination.SignUpScreen))
                    }
            }
        )

        Spacer(modifier = Modifier.height(40.dp))

        AuthTextField(
            value = state.email,
            onValueChange = { email ->
                onEvent(SignInScreenEvent.SetEmail(email))
            },
            modifier = Modifier.fillMaxWidth(),
            readOnly = state.isSigningIn,
            placeholder = stringResource(Res.string.email_hint),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.height(30.dp))

        AuthTextField(
            value = state.password,
            onValueChange = { password ->
                onEvent(SignInScreenEvent.SetPassword(password))
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = stringResource(Res.string.password_hint),
            readOnly = state.isSigningIn,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = Modifier.height(15.dp))

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
            Text(
                text = stringResource(Res.string.forgot_password),
                color = MaterialTheme.appColors.color50,
                fontFamily = fontFamilyResource(Res.font.DMSans_Regular),
                fontSize = 16.sp,
                modifier = Modifier.defaultClickable {
                    onEvent(SignInScreenEvent.Navigate(SignInScreenDestination.ForgotPasswordScreen))
                }
            )
        }

        Spacer(modifier = Modifier.height(50.dp))

        LoadingButton(
            onClick = {
                onEvent(SignInScreenEvent.SignIn)
            },
            isLoading = state.isSigningIn,
            modifier = Modifier.button(),
            enabled = !state.isSigningIn
        ) {
            Text(
                text = stringResource(Res.string.sign_in),
                fontFamily = fontFamilyResource(Res.font.DMSans_Medium),
                fontSize = 16.sp
            )
        }
    }
}

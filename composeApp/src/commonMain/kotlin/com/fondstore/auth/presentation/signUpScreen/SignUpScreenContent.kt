package com.fondstore.auth.presentation.signUpScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fondstore.auth.presentation.componentns.AuthTextField
import com.fondstore.resources.fontFamilyResource
import com.fondstore.ui.appColors
import fondstore.composeapp.generated.resources.confirm_password_hint
import fondstore.composeapp.generated.resources.email_hint
import fondstore.composeapp.generated.resources.password_hint
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.fondstore.core.presentation.LoadingButton
import com.fondstore.core.presentation.SuccessDialog
import com.fondstore.core.presentation.button
import com.fondstore.core.presentation.screenBackground
import com.fondstore.core.presentation.screenPadding
import fondstore.composeapp.generated.resources.DMSans_Medium
import fondstore.composeapp.generated.resources.Res
import fondstore.composeapp.generated.resources.already_have_an_account
import fondstore.composeapp.generated.resources.sign_in_for_free
import fondstore.composeapp.generated.resources.sign_up
import fondstore.composeapp.generated.resources.sign_up_success_action
import fondstore.composeapp.generated.resources.sign_up_success_message
import fondstore.composeapp.generated.resources.sign_up_success_title
import fondstore.composeapp.generated.resources.username_placeholder
import org.jetbrains.compose.resources.stringResource

@Composable
fun SignUpScreenContent(state: SignUpScreenState, onEvent: (SignUpScreenEvent) -> Unit) {
    Box(modifier = Modifier.fillMaxSize().screenBackground()) {
        Column(
            modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).screenPadding()
        ) {
            Text(
                text = stringResource(Res.string.sign_up),
                color = MaterialTheme.appColors.color100,
                fontFamily = fontFamilyResource(Res.font.DMSans_Medium),
                fontSize = 32.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            val alreadyHaveAnAccount = stringResource(Res.string.already_have_an_account)
            val signInForFree = stringResource(Res.string.sign_in_for_free)

            val annotatedString = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.appColors.color50,
                        fontFamily = fontFamilyResource(Res.font.DMSans_Medium),
                        fontSize = 14.sp
                    )
                ) {
                    append(alreadyHaveAnAccount)
                }

                append(" ")

                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.primary,
                        fontFamily = fontFamilyResource(Res.font.DMSans_Medium),
                        fontSize = 14.sp
                    )
                ) {
                    pushStringAnnotation(tag = signInForFree, annotation = signInForFree)
                    append(signInForFree)
                }
            }

            ClickableText(
                text = annotatedString,
                onClick = { offset ->
                    annotatedString.getStringAnnotations(offset, offset)
                        .firstOrNull()?.let {
                            onEvent(SignUpScreenEvent.Navigate(SignUpScreenDestination.SignInScreen))
                        }
                }
            )

            Spacer(modifier = Modifier.height(40.dp))

            AuthTextField(
                value = state.username,
                onValueChange = { username ->
                    onEvent(SignUpScreenEvent.SetUsername(username))
                },
                modifier = Modifier.fillMaxWidth(),
                readOnly = state.isSigningUp,
                placeholder = stringResource(Res.string.username_placeholder),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                error = state.result?.errorOrNull?.username
            )

            Spacer(modifier = Modifier.height(30.dp))

            AuthTextField(
                value = state.email,
                onValueChange = { email ->
                    onEvent(SignUpScreenEvent.SetEmail(email))
                },
                modifier = Modifier.fillMaxWidth(),
                readOnly = state.isSigningUp,
                placeholder = stringResource(Res.string.email_hint),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                error = state.result?.errorOrNull?.email
            )

            Spacer(modifier = Modifier.height(30.dp))

            AuthTextField(
                value = state.password,
                onValueChange = { password ->
                    onEvent(SignUpScreenEvent.SetPassword(password))
                },
                modifier = Modifier.fillMaxWidth(),
                readOnly = state.isSigningUp,
                placeholder = stringResource(Res.string.password_hint),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                error = state.result?.errorOrNull?.password
            )

            Spacer(modifier = Modifier.height(30.dp))

            AuthTextField(
                value = state.confirmPassword,
                onValueChange = { password ->
                    onEvent(SignUpScreenEvent.SetConfirmPassword(password))
                },
                modifier = Modifier.fillMaxWidth(),
                readOnly = state.isSigningUp,
                placeholder = stringResource(Res.string.confirm_password_hint),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                error = state.result?.errorOrNull?.confirmPassword
            )

            Spacer(modifier = Modifier.height(50.dp))

            val canSignIn = state.username.isNotBlank()
                    && state.email.isNotBlank()
                    && state.password.isNotBlank()
                    && state.confirmPassword.isNotBlank()
                    && !state.isSigningUp

            LoadingButton(
                onClick = {
                    onEvent(SignUpScreenEvent.SignUp)
                },
                isLoading = state.isSigningUp,
                modifier = Modifier.button(),
                enabled = !state.isSigningUp
            ) {
                Text(
                    text = stringResource(Res.string.sign_up),
                    fontFamily = fontFamilyResource(Res.font.DMSans_Medium),
                    fontSize = 16.sp
                )
            }
        }

        val info = state.result?.dataOrNull

        if (info != null) {
            SuccessDialog(
                title = stringResource(Res.string.sign_up_success_title, info.username),
                message = stringResource(Res.string.sign_up_success_message, info.email),
                actionMessage = stringResource(Res.string.sign_up_success_action)
            ) {
                onEvent(SignUpScreenEvent.Navigate(SignUpScreenDestination.SignInScreen))
            }
        }
    }
}

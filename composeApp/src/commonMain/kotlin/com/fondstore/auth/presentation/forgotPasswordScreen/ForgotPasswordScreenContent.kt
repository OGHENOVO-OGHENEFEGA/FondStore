package com.fondstore.auth.presentation.forgotPasswordScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fondstore.auth.presentation.componentns.AuthTextField
import com.fondstore.core.presentation.LoadingButton
import com.fondstore.core.presentation.screenBackground
import com.fondstore.core.presentation.screenPadding
import com.fondstore.resources.fontFamilyResource
import com.fondstore.ui.appColors
import fondstore.composeapp.generated.resources.DMSans_Medium
import fondstore.composeapp.generated.resources.Res
import fondstore.composeapp.generated.resources.email_hint
import fondstore.composeapp.generated.resources.password_reset_message
import fondstore.composeapp.generated.resources.reset
import org.jetbrains.compose.resources.stringResource

@Composable
fun ForgotPasswordScreenContent(
    state: ForgotPasswordScreenState,
    onEvent: (ForgotPasswordScreenEvent) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize().screenBackground().screenPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AuthTextField(
            value = state.email,
            onValueChange = { email ->
                onEvent(ForgotPasswordScreenEvent.SetEmail(email))
            },
            modifier = Modifier.fillMaxWidth(),
            readOnly = state.isResettingPassword,
            textAlign = TextAlign.Center,
            placeholder = stringResource(Res.string.email_hint),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(Res.string.password_reset_message),
            color = MaterialTheme.appColors.color50,
            fontFamily = fontFamilyResource(Res.font.DMSans_Medium),
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        LoadingButton(
            onClick = {
                onEvent(ForgotPasswordScreenEvent.ResetPassword)
            },
            isLoading = state.isResettingPassword,
            modifier = Modifier.fillMaxWidth(0.5f).height(50.dp),
            enabled = !state.isResettingPassword
        ) {
            Text(
                text = stringResource(Res.string.reset),
                fontFamily = fontFamilyResource(Res.font.DMSans_Medium),
                fontSize = 16.sp
            )
        }
    }
}


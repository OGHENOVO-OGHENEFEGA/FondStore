package com.fondstore.account

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import com.fondstore.helpCentre.HelpCentreScreen
import com.fondstore.profile.presentation.ProfileScreen
import com.fondstore.resources.fontFamilyResource
import fondstore.composeapp.generated.resources.DMSans_Medium
import fondstore.composeapp.generated.resources.Res
import fondstore.composeapp.generated.resources.help_centre
import fondstore.composeapp.generated.resources.my_profile
import org.jetbrains.compose.resources.stringResource

@Composable
fun AccountScreenContent() {
    Navigator(ProfileScreen()) { navigator ->
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(19.dp))

            val text = if (navigator.lastItemOrNull is ProfileScreen) {
                stringResource(Res.string.my_profile)
            } else {
                stringResource(Res.string.help_centre)
            }

            Row(modifier = Modifier.fillMaxWidth().padding(start = 10.dp)) {
                Button(
                    onClick = {
                        if (navigator.lastItemOrNull is ProfileScreen) {
                            navigator.push(HelpCentreScreen())
                        } else {
                            navigator.replaceAll(ProfileScreen())
                        }
                    },
                    contentPadding = PaddingValues(horizontal = 18.43.dp, vertical = 16.5.dp)
                ) {
                    Text(
                        text = text,
                        fontFamily = fontFamilyResource(Res.font.DMSans_Medium),
                        fontSize = 16.sp
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Icon(
                        imageVector = Icons.Filled.ChevronRight,
                        contentDescription = text,
                        modifier = Modifier.rotate(90f)
                    )
                }
            }

            CurrentScreen()
        }
    }
}
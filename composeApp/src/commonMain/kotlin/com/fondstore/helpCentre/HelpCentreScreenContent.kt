package com.fondstore.helpCentre

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDownCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fondstore.core.presentation.numbClickable
import com.fondstore.resources.fontFamilyResource
import com.fondstore.ui.appColors
import fondstore.composeapp.generated.resources.DMSans_Medium
import fondstore.composeapp.generated.resources.DMSans_Regular
import fondstore.composeapp.generated.resources.Res
import fondstore.composeapp.generated.resources.contact_us
import fondstore.composeapp.generated.resources.faqs
import fondstore.composeapp.generated.resources.our_crew_are_standing_by
import fondstore.composeapp.generated.resources.privacy_policy
import fondstore.composeapp.generated.resources.return_and_exchange_policy
import fondstore.composeapp.generated.resources.tell_us_how_we_can_help
import fondstore.composeapp.generated.resources.terms_and_condition
import org.jetbrains.compose.resources.stringResource

@Composable
fun HelpCentreScreenContent(onEvent: (HelpCentreScreenEvent) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(10.dp)
    ) {
        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = stringResource(Res.string.tell_us_how_we_can_help),
            color = MaterialTheme.appColors.color100,
            fontFamily = fontFamilyResource(Res.font.DMSans_Medium),
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = stringResource(Res.string.our_crew_are_standing_by),
            color = MaterialTheme.appColors.color70,
            fontFamily = fontFamilyResource(Res.font.DMSans_Regular),
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(48.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            border = BorderStroke(1.dp, MaterialTheme.appColors.color30),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary),
            shape = RoundedCornerShape(15.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(36.dp)
            ) {
                HelpCentreOptionBox(name = stringResource(Res.string.faqs)) {
                    onEvent(HelpCentreScreenEvent.Navigate(HelpCentreScreenDestination.FaqsScreen))
                }

                HelpCentreOptionBox(name = stringResource(Res.string.privacy_policy)) {
                    onEvent(
                        HelpCentreScreenEvent.Navigate(
                            HelpCentreScreenDestination.PrivacyPolicyScreen
                        )
                    )
                }

                HelpCentreOptionBox(name = stringResource(Res.string.terms_and_condition)) {
                    onEvent(
                        HelpCentreScreenEvent.Navigate(
                            HelpCentreScreenDestination.TermsAndConditionScreen
                        )
                    )
                }

                HelpCentreOptionBox(name = stringResource(Res.string.return_and_exchange_policy)) {
                    onEvent(
                        HelpCentreScreenEvent.Navigate(
                            HelpCentreScreenDestination.ReturnAndExchangePolicyScreen
                        )
                    )
                }

                HelpCentreOptionBox(name = stringResource(Res.string.contact_us)) {
                    onEvent(
                        HelpCentreScreenEvent.Navigate(
                            HelpCentreScreenDestination.ContactUsScreen
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun HelpCentreOptionBox(name: String, onAction: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().numbClickable(onClick = onAction),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name,
            color = MaterialTheme.appColors.color100,
            fontFamily = fontFamilyResource(Res.font.DMSans_Medium),
            fontSize = 18.sp
        )

        Icon(
            imageVector = Icons.Outlined.ArrowDropDownCircle,
            contentDescription = name,
            tint = MaterialTheme.appColors.color50,
            modifier = Modifier.rotate(-90f)
        )
    }
}
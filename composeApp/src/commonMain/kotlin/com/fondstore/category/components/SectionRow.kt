package com.fondstore.category.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fondstore.product.domain.models.Section

@Composable
fun SectionRow(
    isGettingSections: Boolean,
    sections: List<Section>,
    selectedSection: Section?,
    onSectionSelected: (Section) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.Center
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            if (isGettingSections || sections.isEmpty()) {
                repeat(5) { index ->
                    SectionButton(
                        section = null,
                        isLoading = isGettingSections,
                        isSelected = index == 0,
                        modifier = Modifier.width(120.dp).height(40.dp),
                        onClick = {

                        }
                    )
                }
            } else {
                sections.forEach { section ->
                    SectionButton(
                        section = section,
                        isLoading = isGettingSections,
                        isSelected = section.id == selectedSection?.id,
                        modifier = Modifier.widthIn(min = 100.dp).height(40.dp),
                        onClick = {
                            onSectionSelected(section)
                        }
                    )
                }
            }
        }
    }
}
package com.ah.studio.blueapp.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.ui.theme.SeaBlue400


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomDropDown(
    listItems: Array<String>,
    labelText: String = "",
    labelFontSize: TextUnit,
    textFontSize: TextUnit,
    modifier: Modifier = Modifier,
    trailingIcon: Int = R.drawable.ic_arrow_down,
    selectedText: (String) -> Unit
) {
    var selectedItem by remember {
        mutableStateOf(listItems[0])
    }

    var expanded by remember {
        mutableStateOf(false)
    }

    // the box
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        CustomTextField(
            label = labelText.ifEmpty { listItems[0] },
            placeholder = selectedItem,
            textInput = "",
            readOnly = true,
            labelFontSize = labelFontSize,
            textFontSize = textFontSize,
            trailingIcon = {
                Icon(
                    painter = painterResource(id = trailingIcon),
                    contentDescription = "",
                    modifier = Modifier
                        .width(24.dp)
                        .height(24.dp)
                        .clickable {
                        expanded = true
                    }
                )
            },
            trailingIconColor = SeaBlue400
        ) {
            selectedText(it)
        }

        // menu
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            listItems.forEach { selectedOption ->
                // menu item
                DropdownMenuItem(onClick = {
                    selectedItem = selectedOption
                    expanded = false
                }) {
                    Text(text = selectedOption)
                }
            }
        }
    }
}
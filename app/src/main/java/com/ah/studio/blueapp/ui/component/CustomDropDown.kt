package com.ah.studio.blueapp.ui.component

import android.widget.Toast
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.ui.theme.SeaBlue400


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomDropDown(
    listItems: Array<String>,
    labelFontSize: TextUnit,
    textFontSize: TextUnit,
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
            label = stringResource(id = R.string.boat_type),
            placeholder = selectedItem,
            textInput = "",
            readOnly = true,
            labelFontSize = labelFontSize,
            textFontSize = textFontSize,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
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
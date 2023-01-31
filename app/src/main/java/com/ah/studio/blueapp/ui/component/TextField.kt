package com.ah.studio.blueapp.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ah.studio.blueapp.ui.theme.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomTextField(
    label: String,
    placeholder: String,
    modifier: Modifier = Modifier,
    trailingIconColor :Color = SeaBlue400,
    textInput: String = "",
    readOnly: Boolean = false,
    labelFontSize: TextUnit = 16.sp,
    textFontSize: TextUnit = 20.sp,
    keyboardType: KeyboardType = KeyboardType.Text,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    value: (String) -> Unit
) {
    var text by remember {
        mutableStateOf(textInput)
    }

    Column(
        modifier = Modifier.padding(bottom = PaddingDouble),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = label,
            color = Color.Black,
            fontSize = labelFontSize,
            fontFamily = fontFamily,
            modifier = modifier.padding(
                horizontal = PaddingMedium,
                vertical = 0.dp
            )
        )
        TextField(
            value = text,
            readOnly = readOnly,
            onValueChange = { newText ->
                text = newText
                value(newText)
            },
            placeholder = {
                Text(
                    text = placeholder,
                    fontSize = textFontSize,
                    fontFamily = fontFamily
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = ImeAction.Next
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.Black,
                placeholderColor = Black25Percent,
                textColor = Color.Black,
                focusedIndicatorColor = Black25Percent,
                trailingIconColor = trailingIconColor
            ),
            textStyle = TextStyle(
                fontSize = textFontSize,
                fontFamily = fontFamily
            ),
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            modifier = Modifier
                .fillMaxWidth()
                .safeContentPadding()
        )
    }
}
package com.ah.studio.blueapp.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ah.studio.blueapp.ui.theme.Black25Percent
import com.ah.studio.blueapp.ui.theme.PaddingDouble
import com.ah.studio.blueapp.ui.theme.PaddingMedium

@Composable
fun CustomTextField(
    label: String,
    placeholder: String,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    value: (String) -> Unit
) {
    var text by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.padding(bottom = PaddingDouble),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = label,
            color = Color.Black,
            fontSize = 16.sp,
            modifier = modifier.padding(
                horizontal = PaddingMedium,
                vertical = 0.dp
            )
        )
        TextField(
            value = text,
            onValueChange = { newText ->
                text = newText
                value(newText)
            },
            placeholder = {
                Text(
                    text = placeholder,
                    fontSize = 20.sp
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
                textColor = Color.Black
            ),
            textStyle = TextStyle(fontSize = 20.sp),
            modifier = Modifier
                .fillMaxWidth()
                .safeContentPadding()
        )
    }
}
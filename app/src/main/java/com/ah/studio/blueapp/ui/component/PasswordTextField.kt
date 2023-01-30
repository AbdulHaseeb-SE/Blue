package com.ah.studio.blueapp.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.ui.theme.Black25Percent
import com.ah.studio.blueapp.ui.theme.PaddingDouble
import com.ah.studio.blueapp.ui.theme.PaddingMedium
import com.ah.studio.blueapp.ui.theme.fontFamily

@Composable
fun PasswordTextField(
    label: String,
    placeholder: String,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    value: (String) -> Unit
) {
    var text by remember {
        mutableStateOf("")
    }
    var passwordVisibility: Boolean by remember {
        mutableStateOf(false)
    }
    val painter = if (passwordVisibility)
        painterResource(R.drawable.ic_visibility)
    else painterResource(R.drawable.ic_visibility_off)

    // Please provide localized description for accessibility services
    val description = if (passwordVisibility) "Hide password" else "Show password"



    Column(
        modifier = Modifier.padding(bottom = PaddingDouble),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {

        Text(
            text = label,
            color = Color.Black,
            fontSize = 16.sp,
            fontFamily = fontFamily,
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
                    fontSize = 20.sp,
                    fontFamily = fontFamily
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = ImeAction.Next
            ),
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(
                    onClick = { passwordVisibility = !passwordVisibility }
                ) {
                    Icon(painter = painter, description)
                }
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.Black,
                placeholderColor = Black25Percent,
                textColor = Color.Black,
                focusedIndicatorColor = Black25Percent,
            ),
            textStyle = TextStyle(
                fontSize = 20.sp,
                fontFamily = fontFamily
            ),
            modifier = Modifier
                .fillMaxWidth()
                .safeContentPadding()
        )
    }
}
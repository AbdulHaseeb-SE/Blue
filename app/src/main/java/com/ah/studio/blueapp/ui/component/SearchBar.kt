package com.ah.studio.blueapp.ui.component

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.ui.theme.*

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    text: String,
    onTextChange: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent),
            shape = Shapes.medium
        ) {
            TextField(modifier = Modifier
                .fillMaxSize()
                .background(color = Grey200),
                value = text,
                onValueChange = { text ->
                    onTextChange(text)
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.search),
                        color = Black50Percent,
                        fontFamily = fontFamily,
                        fontSize = 17.sp
                    )
                },
                singleLine = true,
                leadingIcon = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = stringResource(R.string.search_icon),
                            tint = Black50Percent,
                            modifier = Modifier.padding(start = 12.dp, top = 12.dp, bottom = 12.dp)
                        )
                    }
                },
                trailingIcon = {},
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onSearchClicked(text)
                    }
                ),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Grey200,
                    cursorColor = Black50Percent,
                    placeholderColor = Black50Percent,
                    textColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                textStyle = TextStyle(
                    fontFamily = fontFamily,
                    fontSize = 17.sp
                )
            )

        }
    }
}
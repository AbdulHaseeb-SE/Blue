package com.ah.studio.blueapp.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.ui.theme.fontFamily

@Composable
fun ConfirmationDialog(
    showDialog: Boolean,
    title: String = stringResource(R.string.logout_confirmation),
    message: String = stringResource(R.string.logout_question),
    yesButtonColor: Color = Color.Black,
    onConfirm: () -> Unit,
    onCancel: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { /* Nothing to do here */ },
            title = {
                Text(
                    text = title,
                    color = Color.Black,
                    fontFamily = fontFamily,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = message,
                    color = Color.Black,
                    fontFamily = fontFamily,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            confirmButton = {
                TextButton(onClick = onConfirm) {
                    Text(
                        text = stringResource(R.string.yes),
                        color = yesButtonColor,
                        fontFamily = fontFamily
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    onCancel()
                }) {
                    Text(
                        text = stringResource(R.string.no),
                        color = Color.Black,
                        fontFamily = fontFamily
                    )
                }
            },
            properties = DialogProperties(
                dismissOnClickOutside = false
            )
        )
    }
}
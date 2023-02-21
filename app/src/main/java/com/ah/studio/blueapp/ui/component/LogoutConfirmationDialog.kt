package com.ah.studio.blueapp.ui.component

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.ah.studio.blueapp.ui.theme.fontFamily

@Composable
fun LogoutConfirmationDialog(
    showDialog: Boolean,
    onConfirm: () -> Unit,
    onCancel: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { /* Nothing to do here */ },
            title = {
                Text(
                    text = "Logout Confirmation",
                    color = Color.Black,
                    fontFamily = fontFamily,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = "Are you sure you want to log out?",
                    color = Color.Black,
                    fontFamily = fontFamily,
                    fontSize = 15.sp,
                )
            },
            confirmButton = {
                TextButton(onClick = onConfirm) {
                    Text(
                        text = "Yes",
                        color = Color.Black,
                        fontFamily = fontFamily
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    onCancel()
                }) {
                    Text(
                        text = "No",
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
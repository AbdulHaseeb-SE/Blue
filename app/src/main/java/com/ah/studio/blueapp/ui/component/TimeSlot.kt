package com.ah.studio.blueapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TimeSlots(slots: List<TimeSlot>) {
    Column {
        repeat(slots.size / 6) {
            Row {
                repeat(6) {
                    val slot = slots[it + 6]
                    TimeSlotView(slot)
                }
            }
        }
    }
}

@Composable
fun TimeSlotView(slot: TimeSlot) {
    Box(
        modifier = Modifier
            .size(48.dp, 72.dp)
            .background(
                color = if (slot.available) {
                    if (slot.start >= 2 && slot.end <= 14) {
                        Color(0xFF00FF00)
                    } else {
                        Color(0xFFFFFFFF)
                    }
                } else {
                    Color(0xFF000000)
                }
            )
            .clip(RoundedCornerShape(4.dp))
    ) {
        Text(slot.time, style = TextStyle(color = Color.White))
    }
}

data class TimeSlot(val time: String, val available: Boolean, val start: Int, val end: Int)


@Preview
@Composable
fun PreviewTimeSlot(){}
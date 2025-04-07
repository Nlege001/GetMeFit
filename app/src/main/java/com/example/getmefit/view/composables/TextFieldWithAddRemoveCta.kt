package com.example.getmefit.view.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TextFieldWithAddRemoveCta(
    modifier: Modifier = Modifier,
    label: String,
    count: Int,
    onAdd: (Int) -> Unit,
    onRemove: (Int) -> Unit,
    onValueChange: (String) -> Unit,
) {
    Column {
        Text(
            text = label
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                value = count.toString(),
                onValueChange = onValueChange,
                modifier = modifier
                    .border(1.dp, Color.Gray)
                    .padding(8.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            AddRemoveCtas(
                onAdd = { onAdd(count) },
                onRemove = { onRemove(count) },
                isRemoveEnabled = count != 0
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewTextFieldWithAddRemoveCta() {
    val count = remember { mutableStateOf(0) }
    TextFieldWithAddRemoveCta(
        count = count.value,
        onRemove = { count.value -= 1 },
        onAdd = { count.value += 1 },
        label = "Label",
        onValueChange = {}
    )
}
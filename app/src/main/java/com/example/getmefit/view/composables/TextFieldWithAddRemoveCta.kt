package com.example.getmefit.view.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
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
    Column(
        modifier = modifier.padding(vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = count.toString(),
                onValueChange = onValueChange,
                modifier = Modifier
                    .width(80.dp)
                    .padding(end = 12.dp),
                singleLine = true,
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                label = { Text(label) }
            )

            AddRemoveCtas(
                onAdd = { onAdd(count) },
                onRemove = { onRemove(count) },
                isRemoveEnabled = count > 0
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
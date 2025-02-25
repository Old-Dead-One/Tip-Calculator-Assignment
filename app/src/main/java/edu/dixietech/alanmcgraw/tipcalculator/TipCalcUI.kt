package edu.dixietech.alanmcgraw.tipcalculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.dixietech.alanmcgraw.tipcalculator.ui.theme.TipCalculatorTheme

@Composable
fun TipCalcUI(
    modifier: Modifier = Modifier
) {
    var initialBill by remember { mutableFloatStateOf(0f) }
    val formatedBill = "%.2f".format(initialBill)
    var tipPercentage by remember { mutableIntStateOf(0) }
    val tipAmount: Float = (initialBill * tipPercentage) / 100
    val formatedTipAmount = "%.2f".format(tipAmount)
    var numPeople by remember { mutableIntStateOf(1) }
    val totalBillPlusTip: Float = initialBill + tipAmount
    val shareOfBill = totalBillPlusTip / numPeople
    val formatedFinalBill = "%.2f".format(totalBillPlusTip)

    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Text(
            text = "Basic Tip Calculator",
            style = MaterialTheme.typography.titleLarge
        )

        OutlinedTextField(
            value = initialBill.toString(),
            singleLine = true,
            onValueChange = {
                initialBill = it.toFloat()
            },
            label = { Text("Enter Initial Bill Amount:") },
        )

        OutlinedTextField(
            value = tipPercentage.toString(),
            singleLine = true,
            onValueChange = {
                tipPercentage = it.toIntOrNull() ?: 0
            },
            label = { Text("Enter Tip Percentage:") }
        )

        OutlinedTextField(
            value = numPeople.toString(),
            singleLine = true,
            onValueChange = {
                numPeople = it.toIntOrNull() ?: 1
            },
            label = { Text("Split bill between $numPeople people") }
        )

        Text("Initial Bill $$formatedBill")

        Text("Tip Amount: $$formatedTipAmount")

        Text("Total Bill Including Tip: $$formatedFinalBill")

        Text("Each Persons Share is $$shareOfBill")

    }
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
private fun TipCalcUIPreview() {
    TipCalculatorTheme {
        TipCalcUI()
    }
}

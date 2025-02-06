package com.example.homework4.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.homework4.PersonInfo
import com.example.homework4.R

@Composable
fun SuccessScreen(
    personalInfo: PersonInfo,
    onFirstNameChange: (String) -> Unit,
    onLastNameChange: (String) -> Unit,
    onDateOfBirthChange: (String) -> Unit,
    onSexChange: (String) -> Unit,
    onTriggerError: () -> Unit
) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.title),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(text = stringResource(R.string.first_name),
                color = MaterialTheme.colorScheme.onBackground)
            OutlinedTextField(
                value = personalInfo.firstName,
                onValueChange = onFirstNameChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(stringResource(R.string.enter_first_name)) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Last name", color = MaterialTheme.colorScheme.onBackground)
            OutlinedTextField(
                value = personalInfo.lastName,
                onValueChange = onLastNameChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(stringResource(R.string.enter_last_name)) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = stringResource(R.string.date_of_birth), color = MaterialTheme.colorScheme.onBackground)
            OutlinedTextField(
                value = personalInfo.dateOfBirth,
                onValueChange = onDateOfBirthChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(stringResource(R.string.dd_mm_yyyy)) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Calendar"
                    )
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Sex", color = MaterialTheme.colorScheme.onBackground)
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                OutlinedButton(
                    onClick = { onSexChange("Male") },
                    colors = if (personalInfo.selectedSex == "Male")
                        ButtonDefaults.outlinedButtonColors(
                            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                        ) else ButtonDefaults.outlinedButtonColors()
                ) {
                    Text(stringResource(R.string.male), color = MaterialTheme.colorScheme.onBackground)
                }

                Spacer(modifier = Modifier.width(8.dp))

                OutlinedButton(
                    onClick = { onSexChange("Female") },
                    colors = if (personalInfo.selectedSex == "Female")
                        ButtonDefaults.outlinedButtonColors(
                            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                        ) else ButtonDefaults.outlinedButtonColors()
                ) {
                    Text(stringResource(R.string.female), color = MaterialTheme.colorScheme.onBackground)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    onTriggerError()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.call_error))
            }
        }
    }

}

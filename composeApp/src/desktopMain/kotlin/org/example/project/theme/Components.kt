package org.example.project.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.example.project.models.Views
import org.example.project.theme.Resources

@Composable
fun WaterLabCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(16.dp),
                clip = true
            ),
        elevation = 0.dp,
        shape = RoundedCornerShape(16.dp),
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colors.surface,
                            MaterialTheme.colors.surface.copy(alpha = 0.95f)
                        )
                    )
                )
                .border(
                    width = 1.dp,
                    color = LightAqua.copy(alpha = 0.3f),
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(20.dp)
        ) {
            content()
        }
    }
}

@Composable
fun WaterLabButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(48.dp)
            .fillMaxWidth(),
        enabled = enabled,
        shape = RoundedCornerShape(24.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary,
            disabledBackgroundColor = Gray.copy(alpha = 0.6f)
        ),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 2.dp,
            pressedElevation = 4.dp,
            disabledElevation = 0.dp
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            MaterialTheme.colors.primary,
                            MaterialTheme.colors.primaryVariant
                        )
                    ),
                    shape = RoundedCornerShape(24.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.button,
                color = MaterialTheme.colors.onPrimary
            )
        }
    }
}

@Composable
fun WaterLabTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String,
    enabled: Boolean = true
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        label = { Text(label) },
        enabled = enabled,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colors.primary,
            unfocusedBorderColor = Gray,
            focusedLabelColor = MaterialTheme.colors.primary,
            unfocusedLabelColor = Gray,
            cursorColor = MaterialTheme.colors.primary,
            backgroundColor = LightGray.copy(alpha = 0.5f)
        ),
        shape = RoundedCornerShape(12.dp)
    )
}

@Composable
fun WaterTable(){

}

@Composable
fun WaterQualityIndicator(
    quality: Float, // 0.0 to 1.0
    modifier: Modifier = Modifier
) {
    val color = when {
        quality >= 0.8f -> AccentGreen
        quality >= 0.6f -> WaterBlue
        quality >= 0.4f -> AquaBlue
        quality >= 0.2f -> Teal
        else -> Color(0xFFD32F2F) // Vermelho para qualidade baixa
    }
    
    Box(
        modifier = modifier
            .height(8.dp)
            .fillMaxWidth(quality)
            .clip(RoundedCornerShape(4.dp))
            .background(color)
    )
}

@Composable
fun SectionHeader(
    title: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(4.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(MaterialTheme.colors.primary)
        )
        
        Spacer(modifier = Modifier.width(8.dp))
        
        Text(
            text = title,
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.primary
        )
    }
}

@Composable
fun LabCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = 4.dp,
        shape = MaterialTheme.shapes.medium
    ) {
        Box(
            modifier = Modifier.padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}

@Composable
fun LabButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        enabled = enabled,
        shape = MaterialTheme.shapes.medium
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.button
        )
    }
}

@Composable
fun LabTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    errorMessage: String? = null
) {
    Column {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            modifier = modifier.fillMaxWidth(),
            isError = isError,
            shape = MaterialTheme.shapes.medium
        )
        if (isError && errorMessage != null) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
}

@Composable
fun LabDivider() {
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        color = MaterialTheme.colors.onBackground.copy(alpha = 0.12f)
    )
}

@Composable
fun LabMenuBar(
    currentScreen: Views,
    onScreenChange: (Views) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .shadow(4.dp),
        color = MaterialTheme.colors.surface,
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Logo e nome do laboratório
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Image(
                    painter = Resources.loadLogo(),
                    contentDescription = "Logo LabÁgua",
                    modifier = Modifier.size(50.dp)
                )
                Text(
                    text = "LabÁgua",
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primary
                )
            }

            // Botões de navegação
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                MenuButton(
                    text = "Home",
                    isSelected = currentScreen == Views.Home,
                    onClick = { onScreenChange(Views.Home) }
                )
                MenuButton(
                    text = "Análise de Água",
                    isSelected = currentScreen == Views.Tela1,
                    onClick = { onScreenChange(Views.Tela1) }
                )
                MenuButton(
                    text = "Orçamento",
                    isSelected = currentScreen == Views.Tela2,
                    onClick = { onScreenChange(Views.Tela2) }
                )
                MenuButton(
                    text = "Ficha Interna",
                    isSelected = currentScreen == Views.Tela3,
                    onClick = {onScreenChange(Views.Tela3)}
                )
                MenuButton(
                    text = "Registro de Analise",
                    isSelected = currentScreen == Views.Tela4,
                    onClick = {onScreenChange(Views.Tela4)}
                )
            }
        }
    }
}

@Composable
private fun MenuButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (isSelected) MaterialTheme.colors.primary else Color.Transparent,
            contentColor = if (isSelected) MaterialTheme.colors.onPrimary else MaterialTheme.colors.primary
        ),
        elevation = ButtonDefaults.elevation(
            defaultElevation = if (isSelected) 4.dp else 0.dp,
            pressedElevation = 8.dp
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.height(40.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.button,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
        )
    }
} 
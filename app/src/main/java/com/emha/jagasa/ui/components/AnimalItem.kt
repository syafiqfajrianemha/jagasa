package com.emha.jagasa.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emha.jagasa.R
import com.emha.jagasa.ui.Model.Animal

@Composable
fun AnimalItem(
    animal: Animal,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(10.dp)
            .wrapContentSize(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Image(
                painter = painterResource(animal.photo),
                contentDescription = "Photo ${animal.name}",
                contentScale = ContentScale.Crop,
                modifier = modifier.size(100.dp)
            )
            Column {
                Text(
                    text = animal.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = animal.description,
                    fontSize = 16.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnimalItemPreview() {
    MaterialTheme {
        AnimalItem(
            animal = Animal(
                1,
                "Komodo",
                "Komodo, sebagai hewan langka pertama di Indonesia juga menjadi satu-satunya hewan purba yang masih hidup hingga saat ini. Hewan langka ini memiliki nama lain Varatus Komodoensis atau Orah juga merupakan salah satu spesies reptil terbesar di dunia. Komodo memiliki gigitan yang sangat kuat serta bisa yang sangat mematikan, racun berbisa ini berasal dari ribuan kelenjar pada area gusinya. Habitat Komodo sendiri saat ini hanya dapat ditemukan di Pulau Komodo, Flores, Gili Matang, Gili Dasami dan Rinca Nusa Tenggara Timur Indonesia.",
                R.drawable.komodo,
                "Jumlah Komodo setiap tahun di masing-masing pulau bisa naik dan turun, bergantung jumlah Komodo yang hidup dan mati. Di Pulau Komodo, misalnya. Tahun 2016-2018, jumlah Komodo terus meningkat. Tahun 2019 jumlahnya menurun, naik lagi tahun 2020 hingga 2021. Pola yang sama juga terjadi di pulau lain. Hingga tahun 2021, jumlah Komodo di TN Komodo tercatat sebanyak 3.303 ekor. Untuk tahun 2022, jumlahnya masih dalam proses penghitungan."
            )
        )
    }
}
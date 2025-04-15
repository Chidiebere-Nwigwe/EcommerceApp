//package com.example.ecommerceapp.components
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Button
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import coil.compose.AsyncImage
//import com.google.firebase.Firebase
//import com.google.firebase.firestore.firestore
//
//@Composable
//fun BannerView(modifier: Modifier = Modifier) {
//    var bannerUrl by remember { mutableStateOf("") }
//
//    // Load one image from Firebase
//    LaunchedEffect(Unit) {
//        Firebase.firestore.collection("data")
//            .document("banners")
//            .get()
//            .addOnSuccessListener { doc ->
//                val urls = doc.get("urls") as? List<String>
//                if (!urls.isNullOrEmpty()) {
//                    bannerUrl = urls[0] // only display the first banner
//                }
//            }
//    }
//
//    Row(
//        modifier = modifier
//            .fillMaxWidth()
//            .height(180.dp)
//            .clip(RoundedCornerShape(16.dp))
//            .background(
//                Brush.horizontalGradient(
//                    listOf(Color(0xFFBF8B8B), Color(0xFFF3E3E3)) // gradient colors
//                )
//            )
//            .padding(16.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        // Left: Texts + Button
//        Column(
//            modifier = Modifier.weight(1f)
//        ) {
//            Text(
//                text = "Lorem Ipsum\nLorem Ipsum",
//                color = Color.White,
//                fontSize = 18.sp,
//                lineHeight = 22.sp
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(
//                text = "Lorem Ipsum is simply dummy text of the printing",
//                color = Color.White,
//                fontSize = 12.sp
//            )
//            Spacer(modifier = Modifier.height(5.dp))
//            Button(
//                onClick = { /* Handle CTA click */ },
//                shape = RoundedCornerShape(50),
//                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp)
//            ) {
//                Text("Lorem")
//            }
//        }
//
//        Spacer(modifier = Modifier.width(10.dp))
//
//        // Right: Banner image
//        AsyncImage(
//            model = bannerUrl,
//            contentDescription = "Banner image",
//            modifier = Modifier
//                .weight(1f)
//                .fillMaxHeight()
//                .clip(RoundedCornerShape(12.dp))
//        )
//    }
//}

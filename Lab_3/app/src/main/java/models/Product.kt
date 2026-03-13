package models

data class Product(
    val name: String,
    val category: String,
    val price: Double,
    val inStock: Boolean
)
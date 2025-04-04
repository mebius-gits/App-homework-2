data class SideBeverageItem(
    val imageResId: Int,
    val name: String,
    val largePrice: Int,
    val mediumPrice: Int,
    val smallPrice: Int,
    var selectedSize: String? = null,
    var quantity: Int = 0
)

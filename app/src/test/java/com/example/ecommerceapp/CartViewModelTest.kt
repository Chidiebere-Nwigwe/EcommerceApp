import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import com.example.ecommerceapp.viewmodel.CartViewModel
import com.example.ecommerceapp.model.Product
import com.example.ecommerceapp.model.Rating

class CartViewModelTest {

    private lateinit var cartViewModel: CartViewModel

    // Mock Product data for testing
    private val productA = Product(
        id = 1,
        title = "Product A",
        price = 10.0,
        description = "A great product",
        category = "Category A",
        image = "image_url_A",
        rating = Rating(rate = 4.5, count = 100)
    )

    private val productB = Product(
        id = 2,
        title = "Product B",
        price = 20.0,
        description = "Another great product",
        category = "Category B",
        image = "image_url_B",
        rating = Rating(rate = 4.0, count = 150)
    )

    private val productC = Product(
        id = 3,
        title = "Product C",
        price = 30.0,
        description = "Yet another product",
        category = "Category C",
        image = "image_url_C",
        rating = Rating(rate = 5.0, count = 200)
    )

    @Before
    fun setUp() {
        // Initialize the CartViewModel before each test
        cartViewModel = CartViewModel()
    }

    @Test
    fun testAddToCart_AddsNewItem() {
        // Act: Add a new product to the cart
        cartViewModel.addToCart(productA, 1)

        // Assert: The cart should contain 1 item with quantity 1
        val cartItems = cartViewModel.cartItems.value
        assertEquals(1, cartItems.size)
        assertEquals("Product A", cartItems[0].product.title)
        assertEquals(1, cartItems[0].quantity)
    }

    @Test
    fun testAddToCart_IncreasesQuantityForExistingItem() {
        // Arrange: Add product A with quantity 1
        cartViewModel.addToCart(productA, 1)

        // Act: Add the same product again with quantity 2
        cartViewModel.addToCart(productA, 2)

        // Assert: The cart should have 1 item with a total quantity of 3
        val cartItems = cartViewModel.cartItems.value
        assertEquals(1, cartItems.size)
        assertEquals(3, cartItems[0].quantity)
    }

    @Test
    fun testDecreaseQuantity_DecreasesQuantity() {
        // Arrange: Add product A with quantity 3
        cartViewModel.addToCart(productA, 3)

        // Act: Decrease the quantity by 1
        cartViewModel.decreaseQuantity(productA)

        // Assert: The quantity should now be 2
        val cartItems = cartViewModel.cartItems.value
        assertEquals(2, cartItems[0].quantity)
    }

    @Test
    fun testDecreaseQuantity_RemovesItemWhenQuantityIsOne() {
        // Arrange: Add product A with quantity 1
        cartViewModel.addToCart(productA, 1)

        // Act: Decrease the quantity (should remove the item)
        cartViewModel.decreaseQuantity(productA)

        // Assert: The cart should be empty
        val cartItems = cartViewModel.cartItems.value
        assertTrue(cartItems.isEmpty())
    }

    @Test
    fun testRemoveFromCart_RemovesItem() {
        // Arrange: Add product A and product B to the cart
        cartViewModel.addToCart(productA, 1)
        cartViewModel.addToCart(productB, 2)

        // Act: Remove product A from the cart
        cartViewModel.removeFromCart(productA)

        // Assert: The cart should only contain product B
        val cartItems = cartViewModel.cartItems.value
        assertEquals(1, cartItems.size)
        assertEquals("Product B", cartItems[0].product.title)
    }

    @Test
    fun testGetTotalPrice_CalculatesCorrectly() {
        // Arrange: Add products with different quantities
        cartViewModel.addToCart(productA, 2)  // 2 * 10.0 = 20.0
        cartViewModel.addToCart(productB, 1)  // 1 * 20.0 = 20.0
        cartViewModel.addToCart(productC, 3)  // 3 * 30.0 = 90.0

        // Act: Calculate the total price
        val totalPrice = cartViewModel.getTotalPrice()

        // Assert: The total price should be the sum of the prices
        assertEquals(130.0, totalPrice, 0.01)
    }
}
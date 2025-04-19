//package com.example.ecommerceapp.pages
//
//import androidx.compose.ui.test.*
//import androidx.compose.ui.test.junit4.createComposeRule
//import androidx.compose.material3.*
//import androidx.navigation.compose.rememberNavController
//import com.example.ecommerceapp.model.Product
//import com.example.ecommerceapp.viewmodel.CartItem
//import com.example.ecommerceapp.viewmodel.CartViewModel
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import kotlin.test.assertEquals
//
//class CheckoutPageTest {
//
//    @get:Rule
//    val composeTestRule = createComposeRule()
//
//    private lateinit var cartViewModel: CartViewModel
//    private lateinit var product1: Product
//    private lateinit var product2: Product
//
//    @Before
//    fun setUp() {
//        cartViewModel = CartViewModel()
//        product1 = Product(id = 1, name = "Product 1", price = 10.0)
//        product2 = Product(id = 2, name = "Product 2", price = 15.0)
//
//        // Add products to the cart
//        cartViewModel.addToCart(product1, 2)  // 2 of Product 1
//        cartViewModel.addToCart(product2, 1)  // 1 of Product 2
//    }
//
//    private fun launchCheckoutPage() {
//        composeTestRule.setContent {
//            CheckoutPage(navController = rememberNavController(), cartViewModel = cartViewModel)
//        }
//    }
//
//    // Test: Cart items are displayed correctly
//    @Test
//    fun testCartItemsDisplayedCorrectly() {
//        launchCheckoutPage()
//
//        // Verify the number of items and quantities in the cart
//        composeTestRule.onNodeWithText("Items: 2").assertIsDisplayed()  // 2 items in the cart
//        composeTestRule.onNodeWithText("3 x items").assertIsDisplayed()  // 3 total items (2 of Product 1, 1 of Product 2)
//    }
//
//    // Test: Price calculation (Subtotal, Shipping Fee, Final Price)
//    @Test
//    fun testPriceCalculation() {
//        launchCheckoutPage()
//
//        // Verify the subtotal, shipping fee, and final price
//        composeTestRule.onNodeWithText("Sub-total").assertIsDisplayed()
//        composeTestRule.onNodeWithText("$35.00").assertIsDisplayed()  // 2 * 10 + 1 * 15 = 35.00
//        composeTestRule.onNodeWithText("Shipping Fee").assertIsDisplayed()
//        composeTestRule.onNodeWithText("$5.00").assertIsDisplayed()  // Shipping fee is fixed at 5.00
//        composeTestRule.onNodeWithText("Final Price").assertIsDisplayed()
//        composeTestRule.onNodeWithText("$40.00").assertIsDisplayed()  // 35.00 + 5.00 = 40.00
//    }
//
//    // Test: User can input address and coupon
//    @Test
//    fun testAddressAndCouponInput() {
//        launchCheckoutPage()
//
//        // Simulate typing in the address field
//        composeTestRule.onNodeWithTag("addressField")  // Tag for address field
//            .performTextInput("1234 Main St")
//
//        // Simulate typing in the coupon field
//        composeTestRule.onNodeWithTag("couponField")  // Tag for coupon field
//            .performTextInput("DISCOUNT10")
//
//        // Verify that the values are entered correctly
//        composeTestRule.onNodeWithTag("addressField")
//            .assertTextEquals("1234 Main St")
//
//        composeTestRule.onNodeWithTag("couponField")
//            .assertTextEquals("DISCOUNT10")
//    }
//
//    // Test: "Continue to Checkout" button works and navigates to the payment page
//    @Test
//    fun testNavigateToPaymentWithCorrectData() {
//        launchCheckoutPage()
//
//        // Simulate typing in address and coupon fields
//        composeTestRule.onNodeWithTag("addressField")
//            .performTextInput("1234 Main St")
//
//        composeTestRule.onNodeWithTag("couponField")
//            .performTextInput("DISCOUNT10")
//
//        // Click the "Continue to Checkout" button
//        composeTestRule.onNodeWithText("Continue to Checkout").performClick()
//
//        // Verify that the next screen is the payment page
//        // Assuming your PaymentPage has a Text with id 'paymentPageTitle'
//        composeTestRule.onNodeWithText("Payment Page")  // Replace with actual title text or identifier
//            .assertIsDisplayed()
//    }
//}

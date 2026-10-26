package com.example.data

import androidx.annotation.DrawableRes
import com.example.R

enum class Category(val displayName: String) {
    ALL("All"),
    COFFEE("Coffee"),
    TEA("Tea"),
    BURGERS("Burgers"),
    DESSERTS("Desserts"),
    SNACKS("Snacks")
}

enum class BadgeType {
    BESTSELLER,
    AVAILABLE,
    AVAILABLE_TODAY,
    SOLD_OUT,
    NONE
}

data class MenuItem(
    val id: String,
    val name: String,
    val category: Category,
    val price: Double,
    val description: String,
    @DrawableRes val imageRes: Int,
    val badge: BadgeType = BadgeType.NONE,
    val dietaryTags: List<String> = emptyList(),
    val isFeatured: Boolean = false,
    val calories: String = "220 kcal",
    val prepTime: String = "3-5 mins",
    val ingredients: List<String> = emptyList()
)

data class Offer(
    val id: String,
    val title: String,
    val subtitle: String,
    val validity: String,
    val code: String,
    val discount: String,
    val iconType: String,
    val description: String
)

data class LoyaltyMilestone(
    val visitTarget: Int,
    val title: String,
    val subtitle: String,
    val rewardName: String,
    val rewardDescription: String,
    @DrawableRes val imageRes: Int,
    val isCompleted: Boolean
)

data class CustomerProfile(
    val name: String = "Guest Customer",
    val greeting: String = "HELLO THERE",
    val status: String = "Profile coming soon",
    val tagLine: String = "Great coffee brings people together",
    val memberSinceYear: String = "2026",
    val visitsCount: Int = 6,
    val rewardsEarnedCount: Int = 1,
    val favoriteItemIds: Set<String> = setOf("rose_latte", "caramel_cheesecake"),
    val dietaryPreferences: Set<String> = setOf("Oat Milk Friendly", "Low Sugar"),
    val notificationsEnabled: Boolean = true
)

object MenuRepository {
    val sampleMenuItems: List<MenuItem> = listOf(
        MenuItem(
            id = "rose_latte",
            name = "Rose Latte",
            category = Category.COFFEE,
            price = 240.0,
            description = "Silky espresso with rose syrup and steamed milk, topped with dried petals.",
            imageRes = R.drawable.hero_rose_latte_1792871045270,
            badge = BadgeType.BESTSELLER,
            dietaryTags = listOf("Dairy", "Contains oat option"),
            isFeatured = true,
            calories = "180 kcal",
            prepTime = "3 mins",
            ingredients = listOf("Double espresso shot", "Organic floral rose syrup", "Steamed whole or oat milk", "Edible dried Damascus rose petals")
        ),
        MenuItem(
            id = "caramel_cheesecake",
            name = "Caramel Cheesecake",
            category = Category.DESSERTS,
            price = 280.0,
            description = "Creamy cheesecake with rich caramel and a crunchy biscuit base.",
            imageRes = R.drawable.caramel_cheesecake_1792871066728,
            badge = BadgeType.BESTSELLER,
            dietaryTags = listOf("Vegetarian", "Dairy"),
            calories = "410 kcal",
            prepTime = "Ready to serve",
            ingredients = listOf("Philadelphia cream cheese", "Salted caramel drizzle", "Graham cracker butter crust", "Vanilla bean")
        ),
        MenuItem(
            id = "smash_burger",
            name = "Smash Burger",
            category = Category.BURGERS,
            price = 349.0,
            description = "Juicy smashed beef, cheddar, fresh lettuce and house sauce.",
            imageRes = R.drawable.smash_burger_1792871067107,
            badge = BadgeType.AVAILABLE,
            dietaryTags = listOf("Signature Beef", "Contains Gluten"),
            calories = "620 kcal",
            prepTime = "8-10 mins",
            ingredients = listOf("Two smashed 100% Angus beef patties", "Aged cheddar cheese", "Crisp butterhead lettuce", "House smoked paprika aioli", "Toasted brioche bun")
        ),
        MenuItem(
            id = "matcha_latte",
            name = "Matcha Latte",
            category = Category.TEA,
            price = 220.0,
            description = "Smooth matcha with milk, lightly sweetened.",
            imageRes = R.drawable.matcha_latte_1792871088033,
            badge = BadgeType.AVAILABLE,
            dietaryTags = listOf("Antioxidant rich", "Contains oat option"),
            calories = "140 kcal",
            prepTime = "3 mins",
            ingredients = listOf("Ceremonial grade Uji matcha", "Light organic cane sugar", "Fresh steamed or iced milk")
        ),
        MenuItem(
            id = "chocolate_brownie",
            name = "Chocolate Brownie",
            category = Category.DESSERTS,
            price = 180.0,
            description = "Rich, fudgy brownie with dark chocolate chips.",
            imageRes = R.drawable.chocolate_brownie_1792871087828,
            badge = BadgeType.SOLD_OUT,
            dietaryTags = listOf("Vegetarian", "Dark Chocolate 70%"),
            calories = "340 kcal",
            prepTime = "Ready to serve",
            ingredients = listOf("70% Belgian dark chocolate", "Dutch cocoa", "Brown sugar", "Semi-sweet chocolate chips")
        ),
        MenuItem(
            id = "vanilla_latte",
            name = "Vanilla Latte",
            category = Category.COFFEE,
            price = 210.0,
            description = "Velvety espresso with Madagascar vanilla syrup and silky steamed microfoam.",
            imageRes = R.drawable.vanilla_latte_1792871281719,
            badge = BadgeType.AVAILABLE,
            dietaryTags = listOf("Dairy", "Contains oat option"),
            calories = "190 kcal",
            prepTime = "3 mins",
            ingredients = listOf("Fresh roasted espresso", "Madagascar bourbon vanilla syrup", "Steamed milk")
        ),
        MenuItem(
            id = "strawberry_croissant",
            name = "Strawberry Croissant",
            category = Category.SNACKS,
            price = 195.0,
            description = "Flaky golden butter croissant filled with sliced fresh strawberries and sweet chantilly cream.",
            imageRes = R.drawable.strawberry_croissant_1792871108884,
            badge = BadgeType.AVAILABLE,
            dietaryTags = listOf("Fresh Fruit", "Vegetarian"),
            calories = "310 kcal",
            prepTime = "Ready to serve",
            ingredients = listOf("Hand-laminated butter croissant", "Fresh ripe strawberries", "Vanilla chantilly cream", "Powdered sugar")
        ),
        MenuItem(
            id = "pink_chai",
            name = "Pink Chai",
            category = Category.TEA,
            price = 180.0,
            description = "Traditional Kashmiri chai brewed with green tea, crushed pistachios and aromatic green cardamom.",
            imageRes = R.drawable.pink_chai_1792871281536,
            badge = BadgeType.AVAILABLE,
            dietaryTags = listOf("Contains Nuts", "Aromatic Spices"),
            calories = "160 kcal",
            prepTime = "4 mins",
            ingredients = listOf("Brewed Kashmiri tea leaves", "Crushed roasted pistachios", "Freshly ground cardamom", "Steamed warm milk")
        ),
        MenuItem(
            id = "free_cookie",
            name = "Chocolate Chip Cookie",
            category = Category.SNACKS,
            price = 140.0,
            description = "Thick artisanal golden cookie loaded with gooey melting Belgian chocolate chunks.",
            imageRes = R.drawable.chocolate_cookie_1792871109376,
            badge = BadgeType.AVAILABLE,
            dietaryTags = listOf("Baked Daily", "Vegetarian"),
            calories = "280 kcal",
            prepTime = "Ready to serve",
            ingredients = listOf("Brown butter", "Maldon sea salt", "Belgian dark chocolate chunks", "Organic flour")
        )
    )

    val sampleOffers: List<Offer> = listOf(
        Offer(
            id = "early_bird",
            title = "20% off coffees before 11am",
            subtitle = "Start your day brighter",
            validity = "Daily 7:00 AM - 11:00 AM",
            code = "STARTBRIGHT",
            discount = "20% OFF",
            iconType = "coffee",
            description = "Enjoy 20% off all handcrafted barista coffees, including lattes, cappuccinos, and pour-overs before 11:00 AM."
        ),
        Offer(
            id = "bogo_dessert",
            title = "Buy 1 Get 1 on desserts",
            subtitle = "Twice the sweetness",
            validity = "Monday to Thursday after 3:00 PM",
            code = "SWEETBOGO",
            discount = "BUY 1 GET 1",
            iconType = "cake",
            description = "Purchase any fresh artisan cheesecake or chocolate brownie and treat a friend to a second dessert of equal or lesser value!"
        ),
        Offer(
            id = "afternoon_tea",
            title = "Afternoon Tea & Pastry Combo",
            subtitle = "Midday pause made sweet",
            validity = "Daily 2:00 PM - 5:00 PM",
            code = "TEAPAUSE8",
            discount = "₹299 COMBO",
            iconType = "tea",
            description = "Pair any specialty hot or iced tea—including our signature Pink Chai or Matcha Latte—with a fresh Strawberry Croissant for only ₹299."
        ),
        Offer(
            id = "double_stamp",
            title = "Double Stamps Wednesday",
            subtitle = "Earn rewards twice as fast",
            validity = "Every Wednesday all day",
            code = "DOUBLESTAMP",
            discount = "2X STAMPS",
            iconType = "gift",
            description = "Get two digital loyalty stamps instead of one with every visit on Wednesdays. Reach your free coffee or cookie milestone twice as fast!"
        )
    )

    val sampleMilestones: List<LoyaltyMilestone> = listOf(
        LoyaltyMilestone(
            visitTarget = 5,
            title = "Visit 5: Free cookie",
            subtitle = "A little treat on us.",
            rewardName = "Free cookie",
            rewardDescription = "You've reached 5 visits! Enjoy a free cookie on us.",
            imageRes = R.drawable.chocolate_cookie_1792871109376,
            isCompleted = true
        ),
        LoyaltyMilestone(
            visitTarget = 10,
            title = "Visit 10: Free coffee",
            subtitle = "A bigger thank you for being here.",
            rewardName = "Free coffee",
            rewardDescription = "Just 4 more visits until your free coffee!",
            imageRes = R.drawable.hero_rose_latte_1792871045270,
            isCompleted = false
        )
    )
}

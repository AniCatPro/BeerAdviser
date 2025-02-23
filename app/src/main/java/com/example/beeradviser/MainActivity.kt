package com.example.beeradviser

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val findBeer = findViewById<Button>(R.id.find_beer)
        val beerColor = findViewById<Spinner>(R.id.beer_color)
        val beerBlock = findViewById<LinearLayout>(R.id.beer_block)
        val beerImage = findViewById<ImageView>(R.id.beer_image)
        val beerLink = findViewById<TextView>(R.id.beer_link)

        val beerImage2 = findViewById<ImageView>(R.id.beer_image_2)
        val beerName2 = findViewById<TextView>(R.id.beer_name_2)

        findBeer.setOnClickListener {
            val color = beerColor.selectedItem.toString()
            val beerList = getBeers(color)

            if (beerList.isNotEmpty()) {
                val firstBeer = beerList.first()

                val imageRes = beerImages[firstBeer]
                if (imageRes != null) {
                    beerImage.setImageResource(imageRes)
                    beerImage.visibility = ImageView.VISIBLE
                } else {
                    beerImage.visibility = ImageView.GONE
                }

                val link = beerLinks[firstBeer]
                val displayText = """
                    <b>$firstBeer</b><br/>
                    <a href='$link'>Visit the website</a>
                """
                beerLink.text = HtmlCompat.fromHtml(displayText, HtmlCompat.FROM_HTML_MODE_LEGACY)
                beerLink.movementMethod = LinkMovementMethod.getInstance() // Делает ссылку кликабельной
                beerLink.visibility = TextView.VISIBLE
                beerBlock.visibility = LinearLayout.VISIBLE

                if (beerList.size > 1) {
                    val secondBeer = beerList[1]

                    val imageRes2 = beerImages[secondBeer]
                    if (imageRes2 != null) {
                        beerImage2.setImageResource(imageRes2)
                        beerImage2.visibility = ImageView.VISIBLE
                    } else {
                        beerImage2.visibility = ImageView.GONE
                    }

                    beerName2.text = secondBeer
                    beerName2.visibility = TextView.VISIBLE
                } else {
                    beerImage2.visibility = ImageView.GONE
                    beerName2.visibility = TextView.GONE
                }
            }
        }
    }

    private fun getBeers(color: String): List<String> {
        return when (color) {
            "Light" -> listOf("Jail Pale Ale", "Lager Lite")
            "Amber" -> listOf("Jack Amber", "Red Moose")
            "Brown" -> listOf("Brown Bear Beer", "Bock Brownie")
            else -> listOf("Gout Stout", "Dark Daniel")
        }
    }

    private val beerImages = mapOf(
        "Jail Pale Ale" to R.drawable.jail_pale_ale,
        "Lager Lite" to R.drawable.lager_lite,
        "Jack Amber" to R.drawable.jack_amber,
        "Red Moose" to R.drawable.red_moose,
        "Brown Bear Beer" to R.drawable.brown_bear_beer,
        "Bock Brownie" to R.drawable.bock_brownie,
        "Gout Stout" to R.drawable.gout_stout,
        "Dark Daniel" to R.drawable.dark_daniel
    )

    private val beerLinks = mapOf(
        "Jail Pale Ale" to "https://www.dartmoorbrewery.co.uk/products/jail-ale-gift-pack",
        "Lager Lite" to "https://www.pillarsbrewery.com/products/lite-lager?srsltid=AfmBOor9TWXPJwDijfaMgec4kHNmLjoGmi_6hW8atHXn8TwJepKY1Dom",
        "Jack Amber" to "https://bourgognekruis.com/product/rusty-jack/",
        "Red Moose" to "https://winestyle.ru/products/Brew-Moose-Lager-in-can.html",
        "Brown Bear Beer" to "google.com/url?sa=i&url=https%3A%2F%2Fbjorneitalia.com%2Fprodotto%2Fbear-beer-brown-bear%2F&psig=AOvVaw1Ve7DOaEdDpBuaLk2kbFBX&ust=1740405129857000&source=images&cd=vfe&opi=89978449&ved=0CBQQjhxqFwoTCKi8hr742YsDFQAAAAAdAAAAABAE",
        "Bock Brownie" to "https://bierothek.nl/online-shop/10080002-chocolate+bock+-+maisel+++friends.html",
        "Gout Stout" to "https://bierbink.nl/holy-goat-brewing-double-chocolate-orange-stout",
        "Dark Daniel" to "https://webshop.rotodinamic.hr/en/bernard-dark-lager-beer"
    )
}

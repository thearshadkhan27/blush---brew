package com.example.data

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.example.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.util.concurrent.TimeUnit

object ReviewService {
    const val GOOGLE_REVIEW_URL = "https://share.google/ipfMjiqgpRSV4nlXr"

    fun openGoogleReview(context: Context) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(GOOGLE_REVIEW_URL)).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Could not open browser: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
        }
    }

    fun copyToClipboardAndOpenGoogle(context: Context, text: String) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Blush & Brew Review", text)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(context, "Review copied to clipboard! Opening Google...", Toast.LENGTH_SHORT).show()
        openGoogleReview(context)
    }

    suspend fun generateReviewWithAi(
        stars: Int,
        selectedTags: List<String>
    ): String = withContext(Dispatchers.IO) {
        val apiKey = try {
            BuildConfig.GEMINI_API_KEY
        } catch (e: Throwable) {
            ""
        }
        val tagsString = if (selectedTags.isNotEmpty()) selectedTags.joinToString(", ") else "cozy blush aesthetic, Rose Latte, friendly staff"

        if (!apiKey.isNullOrBlank() && apiKey != "MY_GEMINI_API_KEY") {
            try {
                val prompt = """
                    Write a genuine, concise, positive Google Maps review for 'Blush & Brew Cafe' (45-65 words).
                    Star rating: $stars out of 5 stars.
                    Mention these highlights naturally: $tagsString.
                    Mention the signature Rose Latte or desserts.
                    Keep it warm, polite, and sound like a real customer. Do not include quotes, hashtags, or bracketed text.
                """.trimIndent()

                val client = OkHttpClient.Builder()
                    .connectTimeout(12, TimeUnit.SECONDS)
                    .readTimeout(15, TimeUnit.SECONDS)
                    .build()

                val json = JSONObject().apply {
                    val contentsArray = JSONArray().apply {
                        val partObject = JSONObject().apply {
                            val partsArray = JSONArray().apply {
                                put(JSONObject().put("text", prompt))
                            }
                            put("parts", partsArray)
                        }
                        put(partObject)
                    }
                    put("contents", contentsArray)
                }

                val requestBody = json.toString().toRequestBody("application/json".toMediaType())
                val url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-3.5-flash:generateContent?key=$apiKey"
                val request = Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build()

                val response = client.newCall(request).execute()
                if (response.isSuccessful) {
                    val bodyString = response.body?.string()
                    if (!bodyString.isNullOrBlank()) {
                        val responseJson = JSONObject(bodyString)
                        val text = responseJson.optJSONArray("candidates")
                            ?.optJSONObject(0)
                            ?.optJSONObject("content")
                            ?.optJSONArray("parts")
                            ?.optJSONObject(0)
                            ?.optString("text")
                        if (!text.isNullOrBlank()) {
                            return@withContext text.trim().replace("\"", "")
                        }
                    }
                }
            } catch (e: Exception) {
                // Fallback to high quality contextual review
            }
        }

        return@withContext getContextualReview(stars, selectedTags)
    }

    fun getContextualReview(stars: Int, tags: List<String>): String {
        return when (stars) {
            5 -> {
                val list = listOf(
                    "Blush & Brew Cafe is an absolute dream! The signature Rose Latte was delicately spiced and topped with real rose petals. The Caramel Cheesecake was creamy perfection. Warm aesthetic, super friendly staff, and the cozy pink ambiance made our visit memorable. 10/10 recommend!",
                    "Loved my visit to Blush & Brew Cafe! The floral rose decor is so photogenic and relaxing. Everything from the Smash Burger to their fresh Strawberry Croissants is crafted to perfection. Great customer service and lovely vibes!",
                    "Such a charming cafe! The coffee quality at Blush & Brew is exceptional, especially the Rose Latte. Beautiful warm blush-pink interior and delightful desserts. Truly one of the best cafes in town!"
                )
                list.random()
            }
            4 -> {
                "Really enjoyed our time at Blush & Brew Cafe! The cozy pink aesthetic is wonderful to relax in. The coffee was aromatic and delicious, and the staff was very polite and helpful. Definitely coming back for another Rose Latte soon."
            }
            3 -> {
                "Nice experience at Blush & Brew Cafe. The cafe has a pretty blush theme and pleasant seating. The coffee and pastries were good, and the service was prompt."
            }
            else -> {
                "Visited Blush & Brew Cafe. Loved the cafe's aesthetic concept, but sharing feedback on Google to help improve the service and preparation times."
            }
        }
    }
}

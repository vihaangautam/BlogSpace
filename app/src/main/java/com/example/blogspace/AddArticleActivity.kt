package com.example.blogspace.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.blogspace.databinding.ActivityAddArticleBinding
import com.example.blogspace.models.Article
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class AddArticleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddArticleBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        db = FirebaseDatabase.getInstance()

        binding.postArticleButton.setOnClickListener {
            val title = binding.articleTitleInput.text.toString()
            val content = binding.articleContentInput.text.toString()

            if (title.isNotEmpty() && content.isNotEmpty()) {
                // Use a placeholder image URL directly
                val imageUrl = "https://placehold.co/600x400" // Replace with your desired placeholder image URL
                postArticle(title, content, imageUrl)
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun postArticle(title: String, content: String, imageUrl: String) {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            val articleId = db.reference.child("articles").push().key ?: ""
            val article = Article(
                id = articleId,
                title = title,
                content = content,
                imageUrl = imageUrl,
                authorId = userId,
                timestamp = System.currentTimeMillis()
            )

            db.reference.child("articles").child(articleId).setValue(article)
                .addOnSuccessListener {
                    Toast.makeText(this, "Article posted successfully!", Toast.LENGTH_SHORT).show()
                    finish()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error posting article: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        } else {
            Toast.makeText(this, "User not logged in.", Toast.LENGTH_SHORT).show()
        }
    }
}
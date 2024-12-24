package com.example.blogspace.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.blogspace.databinding.ActivityArticleDetailBinding
import com.example.blogspace.models.Article
import com.google.firebase.database.FirebaseDatabase

class ArticleDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArticleDetailBinding
    private lateinit var db: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FirebaseDatabase.getInstance()

        val articleId = intent.getStringExtra("articleId")
        if (articleId != null) {
            loadArticleDetails(articleId)
        } else {
            Toast.makeText(this, "Article ID not found.", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun loadArticleDetails(articleId: String) {
        db.reference.child("articles").child(articleId).get()
            .addOnSuccessListener { dataSnapshot ->
                if (dataSnapshot.exists()) {
                    val article = dataSnapshot.getValue(Article::class.java)
                    if (article != null) {
                        binding.articleDetailTitle.text = article.title
                        binding.articleDetailContent.text = article.content

                        // Load image using Glide
                        Glide.with(this)
                            .load(article.imageUrl)
                            .into(binding.articleDetailImage)
                    }
                } else {
                    Toast.makeText(this, "Article not found.", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error loading article: ${e.message}", Toast.LENGTH_SHORT).show()
                finish()
            }
    }
}
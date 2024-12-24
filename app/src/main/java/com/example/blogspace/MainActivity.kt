package com.example.blogspace.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blogspace.activities.LoginActivity
import com.example.blogspace.adapters.ArticleAdapter
import com.example.blogspace.databinding.ActivityMainBinding
import com.example.blogspace.models.Article
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var adapter: ArticleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        binding.articlesRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ArticleAdapter(mutableListOf()) { article ->
            val intent = Intent(this, ArticleDetailActivity::class.java)
            intent.putExtra("articleId", article.id)
            startActivity(intent)
        }
        binding.articlesRecyclerView.adapter = adapter

        binding.addArticleButton.setOnClickListener {
            if (auth.currentUser != null) {
                val intent = Intent(this, AddArticleActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }

        loadArticles()
    }

    private fun loadArticles() {
        db.collection("articles")
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshots, e ->
                if (e != null) {
                    // Handle error
                    return@addSnapshotListener
                }

                val articles = snapshots?.map { document ->
                    val article = document.toObject(Article::class.java)
                    article.copy(id = document.id)
                } ?: emptyList()
                adapter.updateArticles(articles)
            }
    }
}
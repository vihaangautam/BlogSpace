package com.example.blogspace.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.blogspace.databinding.ItemArticleBinding
import com.example.blogspace.models.Article

class ArticleAdapter(private var articles: List<Article>, private val onItemClick: (Article) -> Unit) :
    RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    class ArticleViewHolder(val binding: ItemArticleBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        holder.binding.articleTitle.text = article.title
        holder.binding.articlePreview.text = article.content.take(100) // Short preview

        // Load image from URL using Glide
        Glide.with(holder.itemView.context)
            .load(article.imageUrl)
            .into(holder.binding.articleImage)

        holder.itemView.setOnClickListener { onItemClick(article) }
    }

    override fun getItemCount() = articles.size

    fun updateArticles(newArticles: List<Article>) {
        articles = newArticles
        notifyDataSetChanged()
    }
}
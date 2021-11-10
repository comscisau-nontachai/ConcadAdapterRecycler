package com.example.nestedrecyclerview02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.nestedrecyclerview02.adapter.AnimalSectionAdapter
import java.security.AccessController.getContext

class MainActivity : AppCompatActivity() {

    private lateinit var concatAdapter: ConcatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        val section = DataSource.createSections(10,10)

        concatAdapter = ConcatAdapter()
        val sectionAdapter = AnimalSectionAdapter(section)

        concatAdapter.addAdapter(sectionAdapter)

        findViewById<RecyclerView>(R.id.recyclerView).run {
            layoutManager = LinearLayoutManager(this@MainActivity,RecyclerView.VERTICAL,false)
            adapter = sectionAdapter
        }
    }

    fun ImageView.loadImage(url: String) {
        try {
            Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true).placeholder(R.drawable.ic_launcher_background).into(this)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
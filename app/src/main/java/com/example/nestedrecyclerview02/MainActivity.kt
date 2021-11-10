package com.example.nestedrecyclerview02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nestedrecyclerview02.adapter.AnimalSectionAdapter

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
}
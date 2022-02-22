package br.com.weigner.netflix

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.weigner.netflix.adapter.MainAdapter
import br.com.weigner.netflix.model.CategoryModel
import br.com.weigner.netflix.util.CategoryJsonDownloadTask
import br.com.weigner.netflix.listeners.CategoryLoader

class MainActivity : AppCompatActivity(), CategoryLoader {

    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view_main)

        val categories = mutableListOf<CategoryModel>()

        mainAdapter = MainAdapter(categories, this)
        recyclerView.layoutManager = LinearLayoutManager(baseContext, RecyclerView.VERTICAL, false)
        recyclerView.adapter = mainAdapter

        val categoryJsonDownloadTask = CategoryJsonDownloadTask()
        categoryJsonDownloadTask.setCategoryLoader(this)
        categoryJsonDownloadTask.execute("https://tiagoaguiar.co/api/netflix/home")
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResult(categories: List<CategoryModel>) {
        mainAdapter.setCategories(categories)
        mainAdapter.notifyDataSetChanged()
    }
}








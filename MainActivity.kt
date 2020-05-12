package id.kshitiz.testkotlinapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.kshitiz.testkotlinapp.modal.NewsItem
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var viewModal: MainViewModal;
    private lateinit var myRecyclerview: RecyclerView
    lateinit var mainAdapter: MainAdapter
    private val itemList = mutableListOf<NewsItem>()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myRecyclerview = findViewById(R.id.recyclerView);
        myRecyclerview.layoutManager = LinearLayoutManager(this)
        mainAdapter = MainAdapter(arrayListOf(), this)
        myRecyclerview.addItemDecoration(
            DividerItemDecoration(
                myRecyclerview.context,
                (myRecyclerview.layoutManager as LinearLayoutManager).orientation
            )
        )
        myRecyclerview.adapter = mainAdapter
        setUpViewModal()
        setupObservers();

    }

    private fun setUpViewModal() {
        viewModal = ViewModelProviders.of(this@MainActivity).get(MainViewModal::class.java)
    }

    private fun setupObservers() {
        viewModal.getTopStories().observe(this, Observer {
            lifecycleScope.launch {
                viewModal.getStory(id = it.subList(0, 10)).collect { value ->
                    Log.d("KTZ:STORY", value.toString())
                    itemList.add(value)
                    mainAdapter.setItems(itemList)
                }
            }
        })
    }
}



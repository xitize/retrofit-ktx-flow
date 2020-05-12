package id.kshitiz.testkotlinapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import id.kshitiz.testkotlinapp.modal.NewsItem
import id.kshitiz.testkotlinapp.util.TimesAgo
import java.net.URI

class MainAdapter() : RecyclerView.Adapter<MainAdapter.MyViewHolder>() {
    lateinit var itemList: List<NewsItem>
    lateinit var context: FragmentActivity

    constructor(stories: List<NewsItem>, context: FragmentActivity) : this() {
        this.context = context
        this.itemList = stories
    }


    class MyViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var title: TextView = v.findViewById(R.id.tvItemTitle)
        var by: TextView = v.findViewById(R.id.tvBy)
        var time: TextView = v.findViewById(R.id.tvTime)
        var score: TextView = v.findViewById(R.id.tvScore)
        var tvShortSite: TextView = v.findViewById(R.id.tvShortSite)
        var tvType: TextView = v.findViewById(R.id.tvType)
    }

    fun setItems(items: List<NewsItem>) {
        itemList = items
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_story, parent, false);
        return MyViewHolder(view);
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var text = "";
        holder.title.text = itemList[position].title
        Log.d("KTZ", itemList[position].title)
        holder.by.text = itemList[position].by
        holder.score.text = itemList[position].score.toString()
        holder.time.text = TimesAgo.getTimeAgo(itemList[position].time.toLong())
        holder.tvType.text = itemList[position].type

        try {
            text = URI(itemList[position].url).host.replace("www.", "")
        } catch (e: Exception) {
            text = "news.ycombinator.com"
        } finally {
            holder.tvShortSite.text = text

        }
    }

}


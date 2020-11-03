package net.bedev.testlumut.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todos.view.*
import net.bedev.testlumut.DetailActivity
import net.bedev.testlumut.R
import net.bedev.testlumut.model.modelLumut


class LumutAdapter(private val listTodos: ArrayList<modelLumut.modelLumutItem>) :
    RecyclerView.Adapter<MvpHolder>() {
    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MvpHolder {
        context = parent.context
        return MvpHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_todos,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = listTodos.size

    override fun onBindViewHolder(holder: MvpHolder, position: Int) {
        val data = listTodos[position]
        val userId = holder.itemView.TvuserId
         val    id= holder.itemView.Tvid
        val title= holder.itemView.Tvtitle
        val completed= holder.itemView.Tvcompleted

        userId.text = data.userId.toString()
        id.text = data.id.toString()
        title.text = data.title
        completed.text = data.completed.toString()

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("id", data.id)
            context.startActivity(intent)
        }
    }


}
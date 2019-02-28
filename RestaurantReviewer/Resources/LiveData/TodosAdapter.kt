package com.example.leonardomadrigal.androidbasics.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.leonardomadrigal.androidbasics.R
import com.example.leonardomadrigal.androidbasics.model.Todo
import kotlinx.android.synthetic.main.item_todo.view.*

class TodosAdapter(private var items: List<Todo> = emptyList()) : RecyclerView.Adapter<TodosAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, index: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(viewHolder: ViewHolder, index: Int) {
        viewHolder.bind(items[index])
    }

    fun update(todos: List<Todo>) {
        items = todos
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(todo: Todo) {
            itemView.todoName.text = todo.title
            if (todo.completed) {
                itemView.completedIcon.visibility = View.VISIBLE
            } else {
                itemView.completedIcon.visibility = View.GONE
            }
        }
    }
}
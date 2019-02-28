package com.example.leonardomadrigal.androidbasics.view


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.leonardomadrigal.androidbasics.R
import com.example.leonardomadrigal.androidbasics.remote.BasicService
import kotlinx.android.synthetic.main.fragment_todos.*


class TodosFragment : Fragment() {

    private val adapter = TodosAdapter()
    private val mViewModel by lazy {
        ViewModelProviders.of(this, TodosViewModelFactory(BasicService.instance))
            .get(TodosViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todos, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // Recycler view data binding setup
        todosList.adapter = adapter
        todosList.layoutManager = GridLayoutManager(context, this.resources.getInteger(R.integer.grid_column_count))
        todosList.addItemDecoration(DividerItemDecoration(context, GridLayoutManager.VERTICAL))
    }

    override fun onStart() {
        super.onStart()

        mViewModel.todos.observe(this, Observer {
            it?.let { todos ->
                adapter.update(todos)
            }
        })

        mViewModel.errorMessage.observe(this, Observer {
            it?.let { error ->
                Toast.makeText(activity, error, Toast.LENGTH_LONG).show()
            }
        })

        mViewModel.isLoading.observe(this, Observer {
            it?.let { isLoading ->
                loading.visibility = if (isLoading) View.VISIBLE else View.GONE
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            TodosFragment().apply {
            }
    }
}

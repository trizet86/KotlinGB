package ru.nbdev.kotlingb.ui.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import ru.nbdev.kotlingb.R
import ru.nbdev.kotlingb.ui.model.MainViewModel
import ru.nbdev.kotlingb.ui.adapter.NotesRecyclerAdapter

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var adapter: NotesRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerInit()
        liveDataInit()
    }

    private fun recyclerInit() {
        recycler_main_notes.layoutManager = GridLayoutManager(this, 2)
        adapter = NotesRecyclerAdapter()
        recycler_main_notes.adapter = adapter
    }

    private fun liveDataInit() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.viewState().observe(this, Observer { viewState ->
            viewState?.let { adapter.notes = it.notes }
        })
    }
}

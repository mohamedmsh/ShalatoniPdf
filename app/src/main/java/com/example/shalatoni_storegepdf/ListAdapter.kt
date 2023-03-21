package com.example.shalatoni_storegepdf


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shalatoni_storegepdf.databinding.ListPdfBinding
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyView>() {
    val db = Firebase.storage
    var count = 0
    inner class MyView(private val binding: ListPdfBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(pos: Int) {

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        val view = ListPdfBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyView(view)
    }

    override fun onBindViewHolder(holder: MyView, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return count
    }

    fun addItem(name: String, phone: String, address: String) {
        this.count++
    }

    fun removeItem() {
        if(this.count > 0) this.count--

    }
}
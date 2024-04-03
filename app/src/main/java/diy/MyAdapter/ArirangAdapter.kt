package diy.MyAdapter

import android.content.Intent
import android.telecom.Call.Details
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import diy.NewsDetailActivity
import diy.arirangnewsapi.R
import diy.arirangnewsapi.arirang_models.Item
import diy.arirangnewsapi.databinding.ItemListBinding


import kotlinx.coroutines.withContext
import java.security.AccessController.getContext



class ArirangAdapter(): ListAdapter<Item, ArirangAdapter.MyViewHolder>(differ){


    interface OnItemClickListener{
        fun onItemClick(v:View,data:Item,pos:Int)
    }


    private var listener : OnItemClickListener?= null

    fun setOnItemClickListener(listener:OnItemClickListener){
        this.listener = listener
    }


    inner class MyViewHolder(val binding: ItemListBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(itemModel: Item){

            binding.titleTextView.text = itemModel.title
            binding.contentTextView.text = itemModel.content

            Glide
                .with(binding.coverImageView.context)
                .load(itemModel.thumUrl)
                .into(binding.coverImageView)

            val pos = adapterPosition
            Log.d("adapterPosition",pos.toString())

            if(pos!= RecyclerView.NO_POSITION){
                itemView.setOnClickListener {
                    Log.d("who first","adapter")
                    listener?.onItemClick(itemView,itemModel,pos)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArirangAdapter.MyViewHolder {

        return MyViewHolder(ItemListBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        Log.d("currentList onBindViewHolder",currentList.toString())
        holder.bind(currentList[position])

    }


    companion object{
        val differ = object : DiffUtil.ItemCallback<Item>(){
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                TODO("Not yet implemented")
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                TODO("Not yet implemented")
            }

        }
    }


}
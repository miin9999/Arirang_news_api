package diy.MyAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import diy.arirangnewsapi.arirang_models.Item
import diy.arirangnewsapi.databinding.ItemListBinding

class ArirangAdapter: ListAdapter<Item, ArirangAdapter.MyViewHolder>(differ){


    inner class MyViewHolder(val binding: ItemListBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(itemModel: Item){
            binding.titleTextView.text = itemModel.title

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return MyViewHolder(ItemListBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

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
package diy.MyAdapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import diy.NewsDetailActivity
import diy.arirangnewsapi.arirang_models.Item
import diy.arirangnewsapi.databinding.ItemListBinding

class ArirangAdapter(val itemClicked : ()->Unit): ListAdapter<Item, ArirangAdapter.MyViewHolder>(differ){


    inner class MyViewHolder(val binding: ItemListBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(itemModel: Item){
            binding.titleTextView.text = itemModel.title
            binding.contentTextView.text = itemModel.content

            Glide
                .with(binding.coverImageView.context)
                .load(itemModel.thumUrl)
                .into(binding.coverImageView)

            binding.root.setOnClickListener {
                itemClicked()
            }


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
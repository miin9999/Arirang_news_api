package diy.arirangnewsapi.widget.adapter.word

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import diy.arirangnewsapi.databinding.WordListBinding
import diy.arirangnewsapi.model.word.WordModel
import diy.arirangnewsapi.widget.adapter.listener.news.WordItemClickLIstener


class WordAdapter(
    private val listener : WordItemClickLIstener
): ListAdapter<WordModel, WordAdapter.WordViewHolder>(differ){



    inner class WordViewHolder(
        private val binding: WordListBinding
    ):RecyclerView.ViewHolder(binding.root){



        fun bind(wordModel: WordModel){

            Log.d("adapterBind","meeseses")
            binding.outSideTitleTextView.text = wordModel.originalWord

            binding.root.setOnClickListener{
                listener.onWordItemClick(wordModel)

            }


        }
    }


    override fun getItemCount(): Int = currentList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):WordViewHolder {


        Log.d("WhoFirst","onCreateViewHolder")

        return WordViewHolder(WordListBinding.inflate(LayoutInflater.from(parent.context),parent,false))


    }

    override fun onBindViewHolder(holder:WordViewHolder, position: Int) {

        Log.d("WhoFirst","onBindViewHolder")

        Log.d("currentListOfWord",currentList.toString())
        holder.bind(currentList[position])


    }


    companion object{
        val differ = object : DiffUtil.ItemCallback<WordModel>(){
            override fun areItemsTheSame(oldWordModel: WordModel, newWordModel: WordModel): Boolean {
                return oldWordModel.id == newWordModel.id
            }

            override fun areContentsTheSame(oldWordModel: WordModel, newWordModel: WordModel): Boolean {
                return oldWordModel == newWordModel
            }

        }
    }


}
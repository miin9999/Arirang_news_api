package diy.arirangnewsapi.widget.adapter.word

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import diy.arirangnewsapi.databinding.WordListBinding
import diy.arirangnewsapi.model.word.WordModel
import diy.arirangnewsapi.screen.main.scrab.SharedViewModel
import diy.arirangnewsapi.widget.adapter.listener.news.WordItemClickLIstener


class WordAdapter(
    private val listener : WordItemClickLIstener,
    private val viewModel: SharedViewModel
): ListAdapter<WordModel, WordAdapter.WordViewHolder>(differ){



    inner class WordViewHolder(
        private val binding: WordListBinding
    ):RecyclerView.ViewHolder(binding.root){



        fun bind(wordModel: WordModel){

            Log.d("adapterBind","meeseses")
            binding.outSideTitleTextView.text = wordModel.originalWord

            binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    // 체크박스를 체크한 경우
                    viewModel.toggleWordSelection(wordModel)
                } else {
                    // 체크박스를 체크 해제한 경우
                    viewModel.toggleWordSelection(wordModel)
                }
            }

            binding.root.setOnClickListener {
                val isCheckBoxVisible = viewModel.isCheckBoxVisibleOfMyWords.value ?: false
                if (isCheckBoxVisible) {
                    // 체크박스가 보이면 체크박스를 클릭하는 동작
                    binding.checkBox.performClick()
                } else {
                    // 원래의 아이템 클릭 동작
                    listener.onWordItemClick(wordModel)
                }
            }


            binding.root.setOnLongClickListener {
                // ViewModel의 상태를 변경하여 체크박스 표시
                viewModel.toggleCheckBoxVisibilityOfMyWord()
                listener.onWordItemLongClick(wordModel)
                true
            }

            val isCheckBoxVisible = viewModel.isCheckBoxVisibleOfMyWords.value ?: false
            binding.checkBox.visibility = if (isCheckBoxVisible) View.VISIBLE else View.GONE

            val isImageVisible = viewModel.isImageViewVisible.value ?: false
            binding.iconImageView.visibility = if (isImageVisible) View.VISIBLE else View.GONE




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
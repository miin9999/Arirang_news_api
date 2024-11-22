package diy.arirangnewsapi.screen.main.myword



import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import diy.arirangnewsapi.databinding.FragmentMywordBinding
import diy.arirangnewsapi.model.word.WordModel
import diy.arirangnewsapi.screen.base.BaseFragment
import diy.arirangnewsapi.screen.main.myword.detail.WordDetailActivity
import diy.arirangnewsapi.widget.adapter.listener.news.WordItemClickLIstener
import diy.arirangnewsapi.widget.adapter.word.WordAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyWordFragment:BaseFragment<MyWordViewModel,FragmentMywordBinding>() {



    override val viewModel by viewModel<MyWordViewModel>()


    private val recyclerAdapter by lazy{
        WordAdapter(object : WordItemClickLIstener{
            override fun onWordItemClick(wordModel: WordModel) {
                startActivity(
                    WordDetailActivity.newIntent(
                        requireContext(),wordModel
                    )
                )

            }

        })
    }


    override fun getViewBinding():FragmentMywordBinding = FragmentMywordBinding.inflate(layoutInflater)

    override fun observeData() = viewModel.wordsFromRoom.observe(viewLifecycleOwner){

        it.map{
            Log.d("wordList",it?.translatedWord.toString())
        }

        recyclerAdapter.submitList(WordModel.toModel(it))


    }


    override fun initViews()= with(binding){

        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


    }

    companion object{

        fun newInstance() = MyWordFragment()

        const val TAG = "MyWordFragment"
    }
}
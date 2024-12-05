package diy.arirangnewsapi.screen.main.myword



import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import diy.arirangnewsapi.databinding.FragmentMywordBinding
import diy.arirangnewsapi.model.word.WordModel
import diy.arirangnewsapi.screen.base.BaseFragment
import diy.arirangnewsapi.screen.main.MainActivity
import diy.arirangnewsapi.screen.main.myword.detail.WordDetailActivity
import diy.arirangnewsapi.screen.main.scrab.SharedViewModel
import diy.arirangnewsapi.widget.adapter.listener.news.WordItemClickLIstener
import diy.arirangnewsapi.widget.adapter.word.WordAdapter
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyWordFragment:BaseFragment<MyWordViewModel,FragmentMywordBinding>() {



    override val viewModel by viewModel<MyWordViewModel>()

    private val sharedViewModel by sharedViewModel<SharedViewModel>()


    private val recyclerViewAdapter by lazy{
        WordAdapter(object : WordItemClickLIstener{
            override fun onWordItemClick(wordModel: WordModel) {
                startActivity(
                    WordDetailActivity.newIntent(
                        requireContext(),wordModel
                    )
                )
            }

            override fun onWordItemLongClick(wordModel: WordModel) {
                (activity as? MainActivity)?.startActionMode()
            }
        },sharedViewModel)
    }


    override fun getViewBinding():FragmentMywordBinding = FragmentMywordBinding.inflate(layoutInflater)

    override fun observeData() = viewModel.wordsFromRoom.observe(viewLifecycleOwner){

        it.map{
            Log.d("wordList",it?.translatedWord.toString())
        }

        recyclerViewAdapter.submitList(WordModel.toModel(it))


    }

    fun checkBoxObserve()  = sharedViewModel.isCheckBoxVisibleOfMyWords.observe(viewLifecycleOwner){
        recyclerViewAdapter.notifyDataSetChanged()
        Log.d("isCheckBoxVisible",it.toString())
    }


    override fun initViews()= with(binding){

        recyclerView.adapter = recyclerViewAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        checkBoxObserve()


    }

    companion object{

        fun newInstance() = MyWordFragment()

        const val TAG = "MyWordFragment"
    }
}
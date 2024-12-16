package diy.arirangnewsapi.screen.main.myword



import android.annotation.SuppressLint
import android.util.Log

import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.view.ActionMode
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import diy.arirangnewsapi.databinding.FragmentMywordBinding
import diy.arirangnewsapi.model.word.WordModel
import diy.arirangnewsapi.screen.base.BaseFragment
import diy.arirangnewsapi.screen.main.MainActivity
import diy.arirangnewsapi.screen.main.myword.detail.WordDetailActivity
import diy.arirangnewsapi.screen.main.scrab.SharedViewModel
import diy.arirangnewsapi.widget.adapter.listener.news.WordItemClickLIstener
import diy.arirangnewsapi.widget.adapter.word.WordAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyWordFragment:BaseFragment<MyWordViewModel,FragmentMywordBinding>(), ActionMode.Callback {



    override val viewModel by viewModel<MyWordViewModel>()

    private val sharedViewModel by sharedViewModel<SharedViewModel>()

    private var actionMode: ActionMode? = null

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
                (activity as? MainActivity)?.startActionMode(this@MyWordFragment)
            }
        },sharedViewModel)
    }


    override fun getViewBinding():FragmentMywordBinding = FragmentMywordBinding.inflate(layoutInflater)

    override fun observeData() = viewModel.wordsFromRoom.observe(viewLifecycleOwner){

        it.map{
            Log.d("wordList",it?.originalWord.toString())
        }

        recyclerViewAdapter.submitList(WordModel.toModel(it))


    }







    @SuppressLint("NotifyDataSetChanged")
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

    override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        sharedViewModel.setActionModeActive(true)
        menu?.add(Menu.NONE, 1, Menu.NONE, "삭제")
        return true
    }

    override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {

        val selectedNewsCount = sharedViewModel.selectedWords.value?.size ?: 0
        val deleteItem = menu?.findItem(1)

        // 선택된 뉴스가 있을 때만 삭제 버튼을 보이도록
        deleteItem?.isVisible = selectedNewsCount > 0

        return true
    }

    override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
        when (item?.itemId) {
            1 -> {
                // 선택된 뉴스 삭제
                lifecycleScope.launch {
                    sharedViewModel.deleteSelectedWord()
                }
                mode?.finish() // 액션 모드 종료
                return true
            }
        }
        return false
    }

    override fun onDestroyActionMode(mode: ActionMode?) {
        actionMode = null
        sharedViewModel.setActionModeActive(false)
        sharedViewModel.selectedWords.value = mutableListOf()
        sharedViewModel.toggleCheckBoxVisibilityOfMyWord() // 체크박스 상태 초기화

    }
}
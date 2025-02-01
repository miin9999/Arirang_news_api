package diy.arirangnewsapi.screen.main.scrab

import android.util.Log

import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.view.ActionMode
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import diy.arirangnewsapi.databinding.FragmentScrabBinding
import diy.arirangnewsapi.model.news.NewsDetailModel
import diy.arirangnewsapi.screen.base.BaseFragment
import diy.arirangnewsapi.screen.main.scrab.detail.ScrapedNewsDetailActivity
import diy.arirangnewsapi.widget.adapter.news.NewsAdapterOfScrap
import diy.arirangnewsapi.widget.adapter.listener.news.NewsItemClickListener
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import diy.arirangnewsapi.screen.main.MainActivity
import kotlinx.coroutines.launch


class ScrabFragment: BaseFragment<ScrabViewModel,FragmentScrabBinding>(), ActionMode.Callback {


    override val viewModel by viewModel<ScrabViewModel>()

    private val sharedViewModel by sharedViewModel<SharedViewModel>()

    private var actionMode: ActionMode? = null

    private val recyclerViewAdapter by lazy{
        NewsAdapterOfScrap(object : NewsItemClickListener{
            override fun onItemClick(newsDetailModel: NewsDetailModel) {
                startActivity(
                    ScrapedNewsDetailActivity.newIntent(
                        requireContext(),newsDetailModel.toEntity()
                    )
                )
            }

            override fun onLongItemClick(newsDetailModel: NewsDetailModel) {
                (activity as? MainActivity)?.startActionMode(this@ScrabFragment)
            }

            override fun onRemoveItemClick(newsDetailModel: NewsDetailModel) {

            }


        },sharedViewModel)
    }






    override fun getViewBinding(): FragmentScrabBinding = FragmentScrabBinding.inflate(layoutInflater)

    override fun observeData() = viewModel.scrapedFragmentLiveData.observe(viewLifecycleOwner){

        // room 에서 받아온 데이터는 model로 변환

        Log.d("livedatasize",it.size.toString())

        recyclerViewAdapter.submitList(NewsDetailModel.toModel(it))


    }

    private fun checkBoxObserve()  = sharedViewModel.isCheckBoxVisibleOfScrapedNews.observe(viewLifecycleOwner){
        recyclerViewAdapter.notifyDataSetChanged()
        Log.d("isCheckBoxVisible",it.toString())
    }

    private fun scrapedNewsCountObserve() = viewModel.scrapedNewsCount.observe(viewLifecycleOwner){ count ->

        val tutorialImageView = binding.tutorialImageView
        val wordRecyclerView = binding.recyclerView
        if (count == 0) {
            tutorialImageView.visibility = View.VISIBLE
            wordRecyclerView.visibility = View.GONE
        } else {
            tutorialImageView.visibility = View.GONE
            wordRecyclerView.visibility = View.VISIBLE
        }
    }


    override fun initViews(){

        binding.recyclerView.adapter = recyclerViewAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        checkBoxObserve()
        scrapedNewsCountObserve()


    }

    companion object{

        fun newInstance() = ScrabFragment()

        const val TAG = "ScrabFragment"
    }

    override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {

        menu?.add(Menu.NONE, 1, Menu.NONE, "삭제")
        sharedViewModel.setActionModeActive(true)
        return true
    }

    override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {

        // selectedNews 값에 따라 삭제 버튼의 가시성 조정
        val selectedNewsCount = sharedViewModel.selectedNews.value?.size ?: 0
        val deleteItem = menu?.findItem(1)

        // 선택된 뉴스가 있을 때만 삭제 버튼을 보이도록
        deleteItem?.isVisible = selectedNewsCount > 0

        return true
    }

    override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
        when (item?.itemId) {
            1 -> {
                // 선택된 단어 삭제
                lifecycleScope.launch {
                    sharedViewModel.deleteSelectedNews()
                    mode?.finish()
                }
                 // 액션 모드 종료
                return true
            }
        }
        return false
    }

    override fun onDestroyActionMode(mode: ActionMode?) {
        actionMode = null
        sharedViewModel.setActionModeActive(false)
        sharedViewModel.selectedNews.value = mutableListOf()
        sharedViewModel.toggleCheckBoxVisibilityOfScrap()


    }


}
package diy.arirangnewsapi.screen.main.scrab

import android.util.Log
import diy.arirangnewsapi.databinding.FragmentScrabBinding
import diy.arirangnewsapi.model.news.NewsDetailModel
import diy.arirangnewsapi.screen.base.BaseFragment
import diy.arirangnewsapi.screen.main.scrab.detail.ScrapedNewsDetailActivity
import diy.arirangnewsapi.widget.adapter.news.NewsAdapterOfScrap
import diy.arirangnewsapi.widget.adapter.listener.news.NewsItemClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class ScrabFragment: BaseFragment<ScrabViewModel,FragmentScrabBinding>() {


    override val viewModel by viewModel<ScrabViewModel>()

    private val recyclerViewAdapter by lazy{
        NewsAdapterOfScrap(object : NewsItemClickListener{
            override fun onItemClick(newsDetailModel: NewsDetailModel) {
                startActivity(
                    ScrapedNewsDetailActivity.newIntent(
                        requireContext(),newsDetailModel.toEntity()
                    )
                )
            }
        })
    }


    override fun getViewBinding(): FragmentScrabBinding = FragmentScrabBinding.inflate(layoutInflater)

    override fun observeData() = viewModel.scrapedFragmentLiveData.observe(viewLifecycleOwner){

        // room 에서 받아온 데이터는 model로 변환

        Log.d("livedatasize",it.size.toString())

        recyclerViewAdapter.submitList(NewsDetailModel.toModel(it))



    }

    override fun initViews(){

        binding.recyclerView.adapter = recyclerViewAdapter

    }

    companion object{

        fun newInstance() = ScrabFragment()

        const val TAG = "ScrabFragment"
    }


}
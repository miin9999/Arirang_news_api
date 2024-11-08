package diy.arirangnewsapi.screen.main.home

import android.content.Intent
import android.util.Log
import diy.arirangnewsapi.databinding.FragmentHomeBinding
import diy.arirangnewsapi.model.news.NewsDetailModel
import diy.arirangnewsapi.screen.base.BaseFragment
import diy.arirangnewsapi.screen.main.home.detail.NewsDetailActivity
import diy.arirangnewsapi.widget.adapter.NewsAdapter
import diy.arirangnewsapi.widget.adapter.listener.news.NewsItemClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment: BaseFragment<HomeViewModel,FragmentHomeBinding>() {

    override val viewModel by viewModel<HomeViewModel>()

    override fun getViewBinding(): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)

    private val recyclerViewAdapter by lazy{
        NewsAdapter(object: NewsItemClickListener{
            override fun onItemClick(newsDetailModel: NewsDetailModel) {

                startActivity(
                    NewsDetailActivity.newIntent(
                        requireContext(),newsDetailModel.toEntity()
                    )
                )

            }

        })
    }



    override fun observeData() = viewModel.newsListLiveData.observe(viewLifecycleOwner){

        recyclerViewAdapter.submitList(it)
    }

    override fun initViews() = with(binding){

        Log.e("fevebbbbb","ddfdfefe")

        recyclerView.adapter = recyclerViewAdapter

    }



    companion object{

        fun newInstance() = HomeFragment()

        const val TAG = "HomeFragment"
    }

}
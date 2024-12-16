package diy.arirangnewsapi.screen.main.home

import android.util.Log
import diy.arirangnewsapi.databinding.FragmentHomeBinding
import diy.arirangnewsapi.model.news.NewsDetailModel
import diy.arirangnewsapi.screen.base.BaseFragment
import diy.arirangnewsapi.screen.main.home.detail.NewsDetailActivity
import diy.arirangnewsapi.widget.adapter.news.NewsAdapterOfHome
import diy.arirangnewsapi.widget.adapter.listener.news.NewsItemClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment: BaseFragment<HomeViewModel,FragmentHomeBinding>() {

    override val viewModel by viewModel<HomeViewModel>()

    override fun getViewBinding(): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)

    private val recyclerViewAdapter by lazy{
        NewsAdapterOfHome(object: NewsItemClickListener{
            override fun onItemClick(newsDetailModel: NewsDetailModel) {

                startActivity(
                    NewsDetailActivity.newIntent(
                        requireContext(),newsDetailModel.toEntity()
                    )
                )

            }

            override fun onRemoveItemClick(newsDetailModel: NewsDetailModel) {
            }

            override fun onLongItemClick(newsDetailModel: NewsDetailModel) {

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
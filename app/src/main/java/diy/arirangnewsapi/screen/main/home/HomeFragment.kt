package diy.arirangnewsapi.screen.main.home

import android.content.Intent
import android.util.Log
import diy.arirangnewsapi.databinding.FragmentHomeBinding
import diy.arirangnewsapi.model.arirang_models.NewsDetailItem
import diy.arirangnewsapi.screen.base.BaseFragment
import diy.arirangnewsapi.widget.adapter.NewsAdapter
import diy.arirangnewsapi.widget.adapter.listener.news.NewsItemClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment: BaseFragment<HomeViewModel,FragmentHomeBinding>() {

    override val viewModel by viewModel<HomeViewModel>()

    override fun getViewBinding(): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)

    private val recyclerViewAdapter by lazy{
        NewsAdapter(object: NewsItemClickListener{
            override fun onItemClick(newsDetailItem: NewsDetailItem) {

                val intent = Intent(requireContext(),NewsDetailActivity::class.java).apply{
                    putExtra("news_item",newsDetailItem)
                }

                startActivity(intent)


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
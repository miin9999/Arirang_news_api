package diy.arirangnewsapi.screen.main.home

import android.util.Log
import android.view.View
import diy.arirangnewsapi.databinding.FragmentHomeBinding
import diy.arirangnewsapi.model.news.NewsDetailModel
import diy.arirangnewsapi.screen.DataState
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


    override fun observeData() = viewModel.dataStateLiveData.observe(viewLifecycleOwner){

        when(it){
            is DataState.Uninitialized ->{
                Log.d("DataState","Uninitialized")

            }
            is DataState.Loading ->{
                Log.d("DataState","Loading")
                //프로그래스 바, 로딩 텍스트 표현 중인 상태


            }
            is DataState.Success ->{
                Log.d("DataState","Success")
                binding.progressBar.visibility = View.GONE
                recyclerViewAdapter.submitList(it.data)


            }
            is DataState.Error ->{
                Log.d("DataState","Error")
            }


        }



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
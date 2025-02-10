package diy.arirangnewsapi.screen.main.home

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
                binding.progressBar.visibility = View.VISIBLE
                isLoading = true


            }
            is DataState.Success ->{
                Log.d("DataState","Success")
                binding.progressBar.visibility = View.GONE
                isLoading = false
                recyclerViewAdapter.submitList(it.data)



            }
            is DataState.Error ->{
                Log.d("DataState","Error")
            }
        }
    }


    private var isLoading = false // 중복 호출 방지

    override fun initViews() = with(binding){

        Log.e("fevebbbbb","ddfdfefe")

        recyclerView.adapter = recyclerViewAdapter
        //recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!isLoading && (visibleItemCount + firstVisibleItemPosition) >= totalItemCount) {
                    viewModel.fetchData() // 추가 데이터 로드
                }
            }
        })
    }



    companion object{

        fun newInstance() = HomeFragment()

        const val TAG = "HomeFragment"
    }

}
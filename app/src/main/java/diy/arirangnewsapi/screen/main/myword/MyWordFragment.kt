package diy.arirangnewsapi.screen.main.myword

import diy.arirangnewsapi.databinding.FragmentMywordBinding
import diy.arirangnewsapi.screen.base.BaseFragment
import diy.arirangnewsapi.screen.main.home.HomeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyWordFragment:BaseFragment<MyWordViewModel,FragmentMywordBinding>() {


    override val viewModel by viewModel<MyWordViewModel>()

    override fun getViewBinding():FragmentMywordBinding = FragmentMywordBinding.inflate(layoutInflater)

    override fun observeData() {

    }

    override fun initViews() = with(binding){


    }

    companion object{

        fun newInstance() = MyWordFragment()

        const val TAG = "MyWordFragment"
    }
}
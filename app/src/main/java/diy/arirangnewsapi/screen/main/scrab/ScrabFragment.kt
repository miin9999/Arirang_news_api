package diy.arirangnewsapi.screen.main.scrab

import diy.arirangnewsapi.databinding.FragmentScrabBinding
import diy.arirangnewsapi.screen.base.BaseFragment
import diy.arirangnewsapi.screen.main.profile.ProfileFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ScrabFragment: BaseFragment<ScrabViewModel,FragmentScrabBinding>() {


    override val viewModel by viewModel<ScrabViewModel>()


    override fun getViewBinding(): FragmentScrabBinding = FragmentScrabBinding.inflate(layoutInflater)

    override fun observeData() {

    }

    override fun initViews() = with(binding){

    }

    companion object{

        fun newInstance() = ScrabFragment()

        const val TAG = "ScrabFragment"
    }


}
package diy.arirangnewsapi.screen.main.profile

import android.os.Bundle
import android.provider.ContactsContract.Profile
import android.view.View
import diy.arirangnewsapi.databinding.FragmentProfileBinding
import diy.arirangnewsapi.screen.base.BaseFragment
import diy.arirangnewsapi.screen.main.home.HomeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment:BaseFragment<ProfileViewModel,FragmentProfileBinding>() {


    override val viewModel by viewModel<ProfileViewModel>()


    override fun getViewBinding(): FragmentProfileBinding = FragmentProfileBinding.inflate(layoutInflater)

    override fun observeData() {

    }





    override fun initViews() = with(binding){

    }


    companion object{

        fun newInstance() = ProfileFragment()

        const val TAG = "ProfileFragment"
    }
}
package diy.arirangnewsapi.screen.main.setting

import android.content.Intent
import diy.arirangnewsapi.databinding.FragmentSettingBinding
import diy.arirangnewsapi.screen.base.BaseFragment
import diy.arirangnewsapi.screen.main.setting.api_reference.ApiReferenceActivity
import diy.arirangnewsapi.screen.main.setting.license.LicenseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingFragment: BaseFragment<SettingViewModel, FragmentSettingBinding>() {

    override val viewModel by viewModel<SettingViewModel>()

    override fun getViewBinding(): FragmentSettingBinding = FragmentSettingBinding.inflate(layoutInflater)

    override fun initViews() = with(binding) {

        binding.licenseButton.setOnClickListener{
            val intent = Intent(requireContext(),LicenseActivity::class.java)
            startActivity(intent)
        }

        binding.apiButton.setOnClickListener{
            val intent = Intent(requireContext(),ApiReferenceActivity::class.java)
            startActivity(intent)
        }



    }

    override fun observeData() {

    }

    companion object {

        fun newInstance() = SettingFragment()

        const val TAG = "SettingFragment"
    }
}
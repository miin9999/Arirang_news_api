package diy.arirangnewsapi.screen.main.profile


import android.content.Context
import android.util.Log
import diy.arirangnewsapi.databinding.FragmentProfileBinding
import diy.arirangnewsapi.screen.base.BaseFragment
import diy.arirangnewsapi.util.data_update_receiver.DataUpdateReceiver.Companion.ORIGINAL_KEY
import diy.arirangnewsapi.util.data_update_receiver.DataUpdateReceiver.Companion.TRANSLATED_KEY

import org.koin.androidx.viewmodel.ext.android.viewModel

class TodayWordFragment : BaseFragment<TodayWordViewModel, FragmentProfileBinding>() {


    override val viewModel by viewModel<TodayWordViewModel>()



    override fun getViewBinding(): FragmentProfileBinding =
        FragmentProfileBinding.inflate(layoutInflater)

    override fun observeData() {
        viewModel.originalWordLiveData.observe(viewLifecycleOwner) {
            Log.d("latestNewData",it.toString())
            binding.originalWordTextView.text = it
        }

        viewModel.translatedWordLiveData.observe(viewLifecycleOwner) {
            Log.d("translatedWord", it.toString())
            binding.translatedWordTextView.text = it
        }
    }

    override fun initViews(){

        val sharedPreferences = requireContext().getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val originalWord = sharedPreferences.getString(ORIGINAL_KEY, null)
        val translatedWord = sharedPreferences.getString(TRANSLATED_KEY, null)

        if (originalWord != null && translatedWord !=null) {
            Log.d("latestData",originalWord)
            viewModel.originalWordLiveData.value = originalWord
            viewModel.translatedWordLiveData.value = translatedWord

        } else{
            binding.originalWordTextView.text = "표시할 단어가 없음"
            binding.translatedWordTextView.text = " "

        }
    }




    companion object {

        fun newInstance() = TodayWordFragment()

        const val TAG = "ProfileFragment"
    }
}



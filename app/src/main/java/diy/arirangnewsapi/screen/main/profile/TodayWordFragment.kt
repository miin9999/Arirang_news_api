package diy.arirangnewsapi.screen.main.profile


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.workDataOf
import diy.arirangnewsapi.data.db.dao.WordDao
import diy.arirangnewsapi.databinding.FragmentProfileBinding
import diy.arirangnewsapi.screen.base.BaseFragment

import diy.arirangnewsapi.util.workmanager.FetchWordWorker
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.java.KoinJavaComponent.getKoin
import org.koin.java.KoinJavaComponent.inject
import java.util.concurrent.TimeUnit

class TodayWordFragment : BaseFragment<TodayWordViewModel, FragmentProfileBinding>() {


    override val viewModel by viewModel<TodayWordViewModel>()



    override fun getViewBinding(): FragmentProfileBinding =
        FragmentProfileBinding.inflate(layoutInflater)

    override fun observeData(){}

    override fun initViews() = with(binding) {
        val sharedPreferences = requireContext().getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val latestData = sharedPreferences.getString("latest_data", null)
        if (latestData != null) {
            Log.d("latestData",latestData)
            binding.textView.text = latestData
        }

        // LiveData를 사용하여 다른 업데이트도 처리
        viewModel.data.observe(viewLifecycleOwner) { newData ->
            Log.d("latestNewData",newData.toString())
            binding.textView.text = newData?.originalWord
        }
    }



    companion object {

        fun newInstance() = TodayWordFragment()

        const val TAG = "ProfileFragment"
    }
}



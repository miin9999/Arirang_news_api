package diy.arirangnewsapi.screen.main.setting.license

import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import diy.arirangnewsapi.databinding.ActivityLicenseBinding
import diy.arirangnewsapi.screen.base.BaseActivity
import diy.arirangnewsapi.widget.adapter.licenseAdapter.LicenseAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
class LicenseActivity : BaseActivity<LicenseViewModel, ActivityLicenseBinding>() {


    override val viewModel by viewModel<LicenseViewModel>()


    override fun getViewBinding(): ActivityLicenseBinding =
        ActivityLicenseBinding.inflate(layoutInflater)

    override fun initViews() = with(binding) {

        val licenses = listOf(
            Triple("Retrofit", "Apache License 2.0", "https://square.github.io/retrofit/"),
            Triple("OkHttp", "Apache License 2.0", "https://square.github.io/okhttp/"),
            Triple("Room", "Apache License 2.0", "https://developer.android.com/jetpack/androidx/releases/room"),
            Triple("Koin", "Apache License 2.0", "https://insert-koin.io/"),
            Triple("Glide", "BSD, MIT, Apache License 2.0", "https://github.com/bumptech/glide/blob/master/LICENSE"),
            Triple("Material Components", "Apache License 2.0", "https://github.com/material-components/material-components-android/blob/master/LICENSE"),
            Triple("Kotlinx Coroutines", "Apache License 2.0", "https://github.com/Kotlin/kotlinx.coroutines/blob/master/LICENSE.txt"),
            Triple("AndroidX Core", "Apache License 2.0", "https://developer.android.com/jetpack/androidx/releases/core"),
            Triple("AndroidX AppCompat", "Apache License 2.0", "https://developer.android.com/jetpack/androidx/releases/appcompat"),
            Triple("AndroidX ConstraintLayout", "Apache License 2.0", "https://developer.android.com/jetpack/androidx/releases/constraintlayout"),
            Triple("AndroidX Activity", "Apache License 2.0", "https://developer.android.com/jetpack/androidx/releases/activity"),
            Triple("Fragment KTX", "Apache License 2.0", "https://developer.android.com/jetpack/androidx/releases/fragment"),
            Triple("WorkManager", "Apache License 2.0", "https://developer.android.com/jetpack/androidx/releases/work"),
            Triple("Google Cloud Translation", "Apache License 2.0", "https://github.com/googleapis/java-translate/blob/main/LICENSE")
        )


        val adapter = LicenseAdapter(licenses)
        binding.licenseRecyclerView.adapter = adapter
        binding.licenseRecyclerView.layoutManager = LinearLayoutManager(this@LicenseActivity)


        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.title = ""

    }

    override fun observeData() {

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }


}
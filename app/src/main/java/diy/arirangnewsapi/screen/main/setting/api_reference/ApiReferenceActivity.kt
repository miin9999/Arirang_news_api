package diy.arirangnewsapi.screen.main.setting.api_reference

import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import diy.arirangnewsapi.databinding.ActivityApiReferenceBinding
import diy.arirangnewsapi.screen.base.BaseActivity
import diy.arirangnewsapi.widget.adapter.licenseAdapter.ApiReferenceAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
class ApiReferenceActivity: BaseActivity<ApiReferenceViewModel, ActivityApiReferenceBinding>() {
    override val viewModel by viewModel<ApiReferenceViewModel>()


    override fun getViewBinding(): ActivityApiReferenceBinding = ActivityApiReferenceBinding.inflate(layoutInflater)


    override fun initViews() =with(binding){

        val dataSource = listOf(
            ("데이터 출처\n본 앱은 공공데이터포털(https://www.data.go.kr)에서 제공하는 데이터를 활용하여 제작되었습니다.\n제공 기관: 국제방송교류재단\nAPI 출처: 뉴스기사API")
        )

        val adapter = ApiReferenceAdapter(dataSource)
        binding.licenseRecyclerView.adapter = adapter
        binding.licenseRecyclerView.layoutManager = LinearLayoutManager(this@ApiReferenceActivity)


        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.title = ""

    }

    override fun observeData() {

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home->{
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
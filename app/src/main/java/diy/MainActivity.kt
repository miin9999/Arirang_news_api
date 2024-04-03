package diy

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

import androidx.recyclerview.widget.LinearLayoutManager
import diy.MyAdapter.ArirangAdapter

import diy.arirangnewsapi.Repository
import diy.arirangnewsapi.arirang_models.Item
import diy.arirangnewsapi.databinding.ActivityMainBinding
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlin.reflect.KClass
import kotlin.reflect.full.functions


class MainActivity : AppCompatActivity() {


    private val scope = MainScope()
    private lateinit var binding : ActivityMainBinding
    private lateinit var adapter : ArirangAdapter

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        coroutineScope()
        adapterSetting()


    }

    private fun adapterSetting(){

        adapter = ArirangAdapter(
//            itemClicked = {
//                val intent = Intent(this,NewsDetailActivity::class.java)
//
//                startActivity(intent)
//            }
        ).apply{
            setOnItemClickListener(object: ArirangAdapter.OnItemClickListener{
            override fun onItemClick(v: View, data: Item, pos: Int) {
                Intent(this@MainActivity,NewsDetailActivity::class.java).apply{
                    putExtra("putContentData",data.content)
                    putExtra("putTitleData",data.title)
                    putExtra("putThumbUrl",data.thumUrl)
                    putExtra("putDate",data.broadcastDate)

                    Log.d("fdsfefef",data.content.toString())
                    //Log.d("who first","main")
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }.run{ startActivity(this) }

            }
        })

    }


        binding.recyclerViewId.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewId.adapter=adapter
    }

    private fun coroutineScope(){
        try{

            scope.launch{

                val dataSet = Repository.getNewsFromApiService(1, 15)
                Log.d("DATA SET",dataSet.toString())

                adapter.submitList(dataSet)

                //setText(dataSet!![0]!!.title.toString())
                //Log.d("현재 스레드 이름",Thread.currentThread().name.toString())
            }
        }catch(e:Exception){
            Log.d("ERRORERE","오류")

        }finally {

        }
    }

}
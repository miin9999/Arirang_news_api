package diy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import androidx.recyclerview.widget.LinearLayoutManager
import diy.MyAdapter.ArirangAdapter

import diy.arirangnewsapi.Repository
import diy.arirangnewsapi.databinding.ActivityMainBinding
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlin.reflect.KClass
import kotlin.reflect.full.functions


class MainActivity : AppCompatActivity() {


    private val scope = MainScope()
    private lateinit var binding : ActivityMainBinding
    private lateinit var adapter : ArirangAdapter

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        adapter = ArirangAdapter(
            itemClicked = {
              val intent = Intent(this,NewsDetailActivity::class.java)
                startActivity(intent)
            }
        )


        binding.recyclerViewId.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewId.adapter=adapter


        // reflection 실습
        val kClass: KClass<ArirangAdapter> = ArirangAdapter::class
        Log.d("reflection",kClass.functions.toString())


        try{

            scope.launch{

                val dataSet = Repository.getNewsFromApiService(1, 50)
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
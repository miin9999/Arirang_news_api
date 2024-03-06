package diy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import diy.MyAdapter.ArirangAdapter
import diy.arirangnewsapi.R
import diy.arirangnewsapi.Repository
import diy.arirangnewsapi.databinding.ActivityMainBinding
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {


    private val scope = MainScope()
    private lateinit var binding : ActivityMainBinding
    private lateinit var adapter : ArirangAdapter

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        adapter = ArirangAdapter()
        binding.recyclerViewId.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewId.adapter=adapter




        try{

            scope.launch{

                val dataSet = Repository.getNewsFromApiService(1, 1)
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
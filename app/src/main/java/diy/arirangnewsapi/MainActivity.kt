package diy.arirangnewsapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {


    private val scope = MainScope()



    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var titleTextView = findViewById<TextView>(R.id.titleTextView)

        try{

            scope.launch{


                val dataSet = Repository.getNewsFromApiService(1,1)
                Log.d("DATA SET",dataSet.toString())

                titleTextView.setText(dataSet!![0]!!.title.toString())

                Log.d("현재 스레드 이름",Thread.currentThread().name.toString())

            }


        }catch(e:Exception){
            Log.d("ERRORERE","오류")



        }finally {

        }



    }
}
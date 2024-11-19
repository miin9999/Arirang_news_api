package diy.arirangnewsapi.screen.main.myword



import android.util.Log
import diy.arirangnewsapi.databinding.FragmentMywordBinding
import diy.arirangnewsapi.screen.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyWordFragment:BaseFragment<MyWordViewModel,FragmentMywordBinding>() {



    override val viewModel by viewModel<MyWordViewModel>()


    override fun getViewBinding():FragmentMywordBinding = FragmentMywordBinding.inflate(layoutInflater)

    override fun observeData() = viewModel.wordsFromRoom.observe(viewLifecycleOwner){
        Log.d("wordFromRoom",it.first()?.wordString.toString())
        binding.textView.text = it.first()?.wordString
    }


    override fun initViews():Unit = with(binding){



    }

    companion object{

        fun newInstance() = MyWordFragment()

        const val TAG = "MyWordFragment"
    }
}
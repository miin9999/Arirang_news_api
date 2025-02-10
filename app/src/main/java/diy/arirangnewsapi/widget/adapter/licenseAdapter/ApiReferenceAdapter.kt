package diy.arirangnewsapi.widget.adapter.licenseAdapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import diy.arirangnewsapi.R

class ApiReferenceAdapter(
    private val apis: List<String> // (라이브러리명, 라이센스 유형, URL)
) : RecyclerView.Adapter<ApiReferenceAdapter.ApiReferenceViewHolder>() {

    inner class ApiReferenceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ReferenceDescription_TextView: TextView = view.findViewById(R.id.ReferenceDescription)

        fun bind(referenceDescription: String) {
            ReferenceDescription_TextView.text = referenceDescription

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApiReferenceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_api_reference, parent, false)
        return ApiReferenceViewHolder(view)
    }

    override fun onBindViewHolder(holder: ApiReferenceViewHolder, position: Int) {
        holder.bind(apis[position])
    }

    override fun getItemCount(): Int = apis.size
}


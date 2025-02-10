package diy.arirangnewsapi.widget.adapter.licenseAdapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import diy.arirangnewsapi.R

class LicenseAdapter(
    private val licenses: List<Triple<String, String, String>> // (라이브러리명, 라이센스 유형, URL)
) : RecyclerView.Adapter<LicenseAdapter.LicenseViewHolder>() {

    inner class LicenseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val libraryNameTextView: TextView = view.findViewById(R.id.LibraryName)
        val licenseTypeTextView: TextView = view.findViewById(R.id.LicenseType)

        fun bind(libraryName: String, licenseType: String, licenseUrl: String) {
            libraryNameTextView.text = libraryName
            licenseTypeTextView.text = licenseType

            itemView.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(licenseUrl))
                itemView.context.startActivity(intent) // 클릭하면 웹 브라우저로 이동
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LicenseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_license, parent, false)
        return LicenseViewHolder(view)
    }

    override fun onBindViewHolder(holder: LicenseViewHolder, position: Int) {
        val (libraryName, licenseType, licenseUrl) = licenses[position]
        holder.bind(libraryName, licenseType, licenseUrl)
    }

    override fun getItemCount(): Int = licenses.size
}


package diy.arirangnewsapi.di

import android.content.Context
import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.translate.Translate
import com.google.cloud.translate.TranslateOptions
import java.io.InputStream

// 번역 서비스 초기화
fun provideTranslateService(context: Context): Translate {
    val credentialsPath = "arirangnewsappKeyfile.json"
    val credentials = context.assets.open(credentialsPath).use { inputStream ->
        GoogleCredentials.fromStream(inputStream)
    }
    return TranslateOptions.newBuilder()
        .setCredentials(credentials)
        .build()
        .service
}
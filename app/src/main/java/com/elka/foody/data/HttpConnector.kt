package com.elka.foody.data

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.net.HttpURLConnection
import java.net.URL

object HttpConnector {
  fun createRequest(url: String): Observable<String> {
    return Observable.create<String> {
      val urlConnection = URL(url).openConnection() as HttpURLConnection
      try {
        urlConnection.connect()

        if (urlConnection.responseCode != HttpURLConnection.HTTP_OK) {
          it.onError(RuntimeException(urlConnection.responseMessage))
        } else {
          val result = urlConnection.inputStream.bufferedReader().readText()
          it.onNext(result)
        }
      } finally {
        urlConnection.disconnect()
      }
    }
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
  }
}
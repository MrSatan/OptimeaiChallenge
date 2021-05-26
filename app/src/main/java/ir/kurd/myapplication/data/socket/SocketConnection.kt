package ir.kurd.myapplication.data.socket

import android.util.Log
import com.microsoft.signalr.HubConnection
import com.microsoft.signalr.HubConnectionBuilder
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.ReplaySubject
import ir.kurd.myapplication.data.model.TestApiModel

object SocketConnection {
    const val SOCKET_URL ="http://94.182.191.41:5000/test_channel"
    val testApiProvider:ReplaySubject<TestApiModel> = ReplaySubject.create()
    private lateinit var hubConnection: HubConnection

    init {
        initialSocketConnection()
        connectToSocket()
    }

    private fun connectToSocket() {
        hubConnection.start().doOnComplete {
            Log.e("Hub Success", "oncomplete")
            listenToEvents()
            }
            .doOnError {
                Log.e("Hub Error", it.localizedMessage?:"")
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    private fun initialSocketConnection() {
        hubConnection = HubConnectionBuilder.create(SOCKET_URL).build()


    }

    private fun listenToEvents(){
        hubConnection.on("OnGetTestApi",{data->
            testApiProvider.onNext(data)

        }, TestApiModel::class.java)
    }



}
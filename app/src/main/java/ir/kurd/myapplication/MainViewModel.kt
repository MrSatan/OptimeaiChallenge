package ir.kurd.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ir.kurd.myapplication.data.model.TestApiModel
import ir.kurd.myapplication.data.socket.SocketConnection

class MainViewModel: ViewModel() {
    val disposable = CompositeDisposable()
    val testApiLiveData = MutableLiveData<TestApiModel>()
    init {
        subscribeToSocketEvents()
    }

    private fun subscribeToSocketEvents() {
        disposable.add(
        SocketConnection.testApiProvider.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                testApiLiveData.postValue(it)
            }
    )
    }
}
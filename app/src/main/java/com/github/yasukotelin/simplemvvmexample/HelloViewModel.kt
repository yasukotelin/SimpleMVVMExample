package com.github.yasukotelin.simplemvvmexample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HelloViewModel : ViewModel() {

    // StateはViewModelに書きます。
    // LiveDataを使うことでDataBindingに変更通知が可能になります。
    // LiveDataを使わないと変更が検知されないので最初の1回の描画だけです。
    private var _message = MutableLiveData("Welcome to SimpleMVVMExample!")
    val message: LiveData<String>
        get() = _message

    // イベント通知もLiveDataでOKです！
    // 注意点としては、画面遷移などでLiveDataがActiveになった時に再度値が流れてしまうので、
    // GoogleのEvent.ktなどで1回だけイベントが飛ぶようにしてあげるといいと思います。
    private var _showToastAction = MutableLiveData<Event<String>>()
    val showToastAction
        get() = _showToastAction

    private var _navigateNextAction = MutableLiveData<Event<Unit>>()
    val navigateNextAction
        get() = _navigateNextAction

    // onClickの関数もViewModelに書きます。
    // Viewから直接ViewModelの関数を参照して呼び出します。
    fun reverseMessage() {
        message.value?.let {
            _message.postValue(it.reversed())
            _showToastAction.postValue(Event("リバースしたよ！"))
        }
    }

    fun onClickNextButton() {
        _navigateNextAction.postValue(Event(Unit))
    }
}
package com.unknowncompany.pandatechtechtest.utils

import android.util.Patterns
import android.widget.EditText
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

object ObservableUtils {

    fun EditText.getTextIsBlankStream(): Observable<Boolean> =
        RxTextView.textChanges(this)
            .skipInitialValue()
            .debounce(500, TimeUnit.MILLISECONDS)
            .map { text ->
                text.isBlank()
            }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun EditText.getEmailIsInvalidStream(): Observable<Boolean> =
        RxTextView.textChanges(this)
            .skipInitialValue()
            .debounce(500, TimeUnit.MILLISECONDS)
            .map { email ->
                !Patterns.EMAIL_ADDRESS.matcher(email).matches()
            }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

}
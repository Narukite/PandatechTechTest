package com.unknowncompany.pandatechtechtest.ui

import androidx.lifecycle.ViewModel
import com.unknowncompany.pandatechtechtest.domain.model.User

class MainViewModel : ViewModel() {

    var users = listOf<User>()

}
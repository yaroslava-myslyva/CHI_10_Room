package com.example.chi_10_room.app

import android.app.Application
import com.example.chi_10_room.db.MyDataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MyApplication :Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    val dataBase by lazy { MyDataBase.getDataBase(this, applicationScope)}
}
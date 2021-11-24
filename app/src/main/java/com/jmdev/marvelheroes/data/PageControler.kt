package com.jmdev.marvelheroes.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PageControler @Inject constructor() {
    var limit:Int=20
    var offset:Int=0
    var query:String=""
}
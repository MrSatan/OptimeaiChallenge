package ir.kurd.myapplication

import java.text.SimpleDateFormat
import java.util.*

fun String.parseDate():String{
    val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
   return formatter.parse(this)?.toString()?:""
}
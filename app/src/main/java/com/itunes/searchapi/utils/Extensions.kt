package com.itunes.searchapi.utils

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.text.SimpleDateFormat

fun View.visible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun ImageView.loadImage(url: String) {
    Glide.with(this)
        .load(url)
        .into(this)
}

fun ImageView.loadImageWithResize(url: String) {
    Glide.with(this)
        .load(url)
        .apply( RequestOptions().override(350, 800))
        .into(this)
}

fun isEmpty(text: String): Boolean {
   return  text.isEmpty()
}

fun enableButtons(buttonList : List<View>){
    for(item in buttonList){
        item.isEnabled = true
    }
}

fun dateFormat(releaseDate: String): String {
    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    val formattedDate = simpleDateFormat.parse(releaseDate)
    val format = SimpleDateFormat("MMM dd,yyyy hh:mm")
    return format.format(formattedDate)
}




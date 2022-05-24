package com.example.akharinkhabar.ui

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.core.view.marginTop
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.akharinkhabar.R
import com.makeramen.roundedimageview.RoundedImageView
import de.hdodenhof.circleimageview.CircleImageView

/**
 * Created by Moha on 2022-05-23.
 */

@BindingAdapter("setImage")
fun RoundedImageView.setImage(image: String?) {
    image?.let {
        Glide.with(context)
            .load("http://app.akharinkhabar.ir/images/2021/04/20/Tumb_8f40815a-56e4-4693-85ff-76d5925c2ce0.jpeg")
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.svg_comment)
                    .error(R.drawable.svg_comment)
            ).into(this)
    }
}

@BindingAdapter("typeOfSimple")
fun TextView.typeOfSimple(type: String?) {
    type?.let {
        if (type.isNotEmpty())
            text = type
        else
            isVisible = false
    } ?: kotlin.run {
        isVisible = false
    }
}

@BindingAdapter("publishDateFa", "categoryName")
fun CircleImageView.visibleView(publishDateFa: String?, categoryName: String?) {
    isVisible = !(publishDateFa.isNullOrBlank() || categoryName.isNullOrBlank())
}

@BindingAdapter("publish", "category")
fun TextView.visibleTextView(publishDateFa: String?, categoryName: String?) {
    isVisible = !(publishDateFa.isNullOrBlank() || categoryName.isNullOrBlank())
}

@BindingAdapter("visibleDependOnTitle", "marginTopDependOnDec")
fun ImageView.visibleMarkView(title: String?, dis: String?) {
    isVisible = !(title.isNullOrBlank())
    if (dis.isNullOrBlank()) {
        val param = layoutParams as ViewGroup.MarginLayoutParams
        param.setMargins(0, 50, 0, 0)
        layoutParams = param
    }
}

@BindingAdapter("timeOfSimple")
fun TextView.timeOfSimple(time: String?) {
    time?.let {
        if (time.isNotEmpty())
            text = time
        else
            isVisible = false
    } ?: kotlin.run {
        isVisible = false
    }
}

@BindingAdapter("setNumber")
fun TextView.setNumber(number: Long?) {
    number?.let {
        text = if (number >= 1000) {
            context.getString(R.string.text_k, (number / 1000).toString())
        } else {
            number.toString()
        }
    } ?: kotlin.run {
        isVisible = false
    }
}

@BindingAdapter("setTextOfItems")
fun TextView.setTextOfItems(textOfItem: String?) {
    textOfItem?.let {
        if (textOfItem.isNotBlank()) {
            text = textOfItem
        } else {
            isVisible = false
        }
    } ?: kotlin.run {
        isVisible = false
    }
}


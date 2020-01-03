package com.kucingselfie.kadesubmission.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kucingselfie.kadesubmission.BuildConfig.BASE_URL
import com.kucingselfie.kadesubmission.R
import javax.inject.Inject

class FragmentBindingAdapters @Inject constructor(val fragment: Fragment) {
    @BindingAdapter("imageUrl")
    fun bindImage(imgView: ImageView, imgUrl: String?) {
        if (imgUrl == null) {
            Glide.with(imgView.context)
                .load(R.drawable.trophy)
                .into(imgView)
        } else {
            Glide.with(imgView.context)
                .load(imgUrl).placeholder(R.drawable.trophy)
                .into(imgView)
        }
    }
}
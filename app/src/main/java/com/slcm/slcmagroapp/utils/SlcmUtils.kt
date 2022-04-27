package com.slcm.slcmagroapp.utils

import android.app.Dialog
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import com.bumptech.glide.Glide
import com.slcm.slcmagroapp.R

object SlcmUtils {

    const val API_KEY: String = "AIzaSyCqOIVFM4CWSKKCCSSUXiDdGMsLEICV2rc"
    private const val ANDROID_ASSET: String = "file:///android_asset/"
    val TAG: String = SlcmUtils::class.java.simpleName

    const val LOADING: String = "${ANDROID_ASSET}ajaxnew.html"
    const val GEAR_BOX_MOTOR: String = "https://source.android.com/devices/automotive"
    const val DRUM: String = "https://news.google.com/topstories?tab=in&hl=en-IN&gl=IN&ceid=IN:en"
    const val MIXING: String = "https://github.com/"
    const val CABIN: String = "https://www.advity.in/dev/krishe-dash/userdash/data.php"

    fun popUpImg(
        context: Context?,
        uri: Uri?,
        ImgCredit: String?,
        encodedImgORUrl: String?,
        bitmap: Bitmap?,
        Type: String
    ): Dialog {
        val dialog = Dialog(context!!, R.style.Base_ThemeOverlay_AppCompat_Light)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.photo_popup)
        val photoId = dialog.findViewById<View>(R.id.photoId) as RelativeLayout

        //        RelativeLayout signId = (RelativeLayout) dialog.findViewById(R.id.signId);
        //        signId.setVisibility(View.GONE);
        photoId.visibility = View.VISIBLE
        val closeImgPopUp = dialog.findViewById<View>(R.id.closeImgPopUp) as ImageView
        val imgPopup = dialog.findViewById<View>(R.id.imgPopup) as ImageView
        val headingText = dialog.findViewById<View>(R.id.headingText) as TextView
        headingText.text = ImgCredit
        closeImgPopUp.setOnClickListener { dialog.dismiss() }
        dialog.setCancelable(true)
        if (Type == "URI") {
            imgPopup.setImageURI(uri)
        } else if (Type == "NOT_URL") {
            val bytes = Base64.decode(encodedImgORUrl, Base64.DEFAULT)
            val bitmap1 = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            imgPopup.setImageBitmap(bitmap1)
        } else if (Type == "bitMap") {
            imgPopup.setImageBitmap(bitmap)
        } else { //if (!Type.equals("URL")) {
            try {
                // Loads given image
                //  int size = (int) Math.ceil(Math.sqrt(800 * 600));
                /*Picasso.get()
                    .load(encodedImgORUrl) // .transform(new BitmapTransform(800, 600))
                    // .resize(size, size)
                    // .centerInside()
                    // .noPlaceholder()
                    .placeholder(R.drawable.loader)
                    .error(R.drawable.load_failed)
                    .into(imgPopup)*/
                Glide.with(context)
                    .load(encodedImgORUrl)
                    //.centerCrop()
                    .placeholder(R.drawable.loader)
                    .error(R.drawable.blankimge)
                    .into(imgPopup)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        dialog.show()
        return dialog
    }

    fun ImageView.setTint(@ColorRes colorRes: Int) {
        ImageViewCompat.setImageTintList(
            this,
            ColorStateList.valueOf(ContextCompat.getColor(context, colorRes))
        )
    }

    fun toastIt(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }

    fun logIt(msg: String) {
        Log.e(TAG, "logIt: $msg")
    }
}
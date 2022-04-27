package  com.slcm.slcmagroapp.views.cs

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.slcm.slcmagroapp.R
import com.slcm.slcmagroapp.databinding.FabItemLayoutBinding
import com.slcm.slcmagroapp.databinding.ImgMiddleLayoutBinding
import com.slcm.slcmagroapp.views.fab.OnFabItemClickListener

/**
 * Created by Nataraj Bingi on Feb 20, 2022
 */

@RequiresApi(Build.VERSION_CODES.M)
class MidImgAdapter(private val mListener: OnFabItemClickListener) :
    ListAdapter<Int, MidImgAdapter.ImgMiddleViewHolder>(FabItemsDiffCallback()) {
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImgMiddleViewHolder {
        return ImgMiddleViewHolder.itemsViewHolder(this, parent)
    }

    override fun onBindViewHolder(holder: ImgMiddleViewHolder, position: Int) {
        val item = getItem(position)//data[position]
        holder.bindReport(item, mListener,position)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    class ImgMiddleViewHolder private constructor(private val itemBinding: ImgMiddleLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bindReport(item: Int, listener: OnFabItemClickListener, position: Int) {

            Log.e("TAG", "bindReport: ImgMid $item", )
            itemBinding.mainImage.background = itemView.context.getDrawable(item)

            /*itemView.setOnClickListener {
                listener.onItemClick(item, position)
                selectedPosition = position
//                Log.e("TAG", "bindReport:setOnClickListener:selectedPosition $selectedPosition" )
            }
            itemView.setOnLongClickListener {
                listener.onItemLongClick(item, position)
                true
            }*/
        }

        companion object {
            fun itemsViewHolder(
                itemsStatusAdapter: MidImgAdapter,
                parent: ViewGroup
            ): ImgMiddleViewHolder {
                itemsStatusAdapter.context = parent.context
                val binding =
                    ImgMiddleLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ImgMiddleViewHolder(binding)
            }

        }
    }

    // override fun getItemCount(): Int = data.size
    class FabItemsDiffCallback : DiffUtil.ItemCallback<Int>() {
        override fun areItemsTheSame(
            oldItem: Int,
            newItem: Int
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Int,
            newItem: Int
        ): Boolean {
            return oldItem == newItem
        }
    }
}

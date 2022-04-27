package  com.slcm.slcmagroapp.views.fab

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

/**
 * Created by Nataraj Bingi on Feb 20, 2022
 */

@RequiresApi(Build.VERSION_CODES.M)
class FabMenuAdapter(private val mListener: OnFabItemClickListener) :
    ListAdapter<ItemsModel, FabMenuAdapter.FabViewHolder>(FabItemsDiffCallback()) {
    lateinit var context: Context

    companion object {
        var selectedPosition = -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FabViewHolder {
        return FabViewHolder.itemsViewHolder(this, parent)
    }

    override fun onBindViewHolder(holder: FabViewHolder, position: Int) {
        val item = getItem(position)//data[position]
        holder.bindReport(item, mListener,position)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    class FabViewHolder private constructor(private val itemBinding: FabItemLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bindReport(item: ItemsModel, listener: OnFabItemClickListener, position: Int) {
            if (selectedPosition == position) {
                itemBinding.layoutBg.setBackgroundColor(itemView.context.getColor(R.color.menu_bg_selected))
                itemBinding.title.setTextColor(itemView.context.getColor(R.color.menu_text_selected))

            } else {
                itemBinding.layoutBg.setBackgroundColor(itemView.context.getColor(android.R.color.transparent))
                itemBinding.title.setTextColor(itemView.context.getColor(R.color.menu_text_inactive))
            }
//            Log.e("TAG", "bindReport:selectedPosition $selectedPosition" )

            itemBinding.title.text = item.title
            itemBinding.imageLogo.background = itemView.context.getDrawable(item.image)

            itemView.setOnClickListener {
                listener.onItemClick(item, position)
                selectedPosition = position
//                Log.e("TAG", "bindReport:setOnClickListener:selectedPosition $selectedPosition" )
            }
            itemView.setOnLongClickListener {
                listener.onItemLongClick(item, position)
                true
            }
        }

        companion object {
            fun itemsViewHolder(
                itemsStatusAdapter: FabMenuAdapter,
                parent: ViewGroup
            ): FabViewHolder {
                itemsStatusAdapter.context = parent.context
                val binding =
                    FabItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return FabViewHolder(binding)
            }

        }
    }

    // override fun getItemCount(): Int = data.size
    class FabItemsDiffCallback : DiffUtil.ItemCallback<ItemsModel>() {
        override fun areItemsTheSame(
            oldItem: ItemsModel,
            newItem: ItemsModel
        ): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(
            oldItem: ItemsModel,
            newItem: ItemsModel
        ): Boolean {
            return oldItem == newItem
        }
    }
}

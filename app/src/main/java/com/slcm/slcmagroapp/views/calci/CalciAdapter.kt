package  com.slcm.slcmagroapp.views.calci

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.slcm.slcmagroapp.databinding.CalciItemLayoutBinding
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.pow
import kotlin.math.roundToInt

/**
 * Created by Nataraj Bingi on Feb 22, 2022
 */
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class CalciAdapter(private val mListener: OnCalciItemClickListener) :
    ListAdapter<CalciItemsModel, CalciAdapter.CalciViewHolder>(ItemsDiffCallback()) {
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalciViewHolder {
        return CalciViewHolder.itemsViewHolder(this, parent)
    }

    override fun onBindViewHolder(holder: CalciViewHolder, position: Int) {
        val item = getItem(position)//data[position]
        holder.bindReport(item, mListener)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    class CalciViewHolder private constructor(private val itemBinding: CalciItemLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bindReport(item: CalciItemsModel, listener: OnCalciItemClickListener) {
            itemBinding.title.text = item.title
            itemBinding.titleOne.text = item.roundUpHrs
            itemBinding.titleTwo.text = roundOffDecimal(item.Hrs.toDouble()).toString()

            itemView.setOnClickListener {
                listener.onItemClick(item, adapterPosition)
            }
            itemView.setOnLongClickListener {
                listener.onItemLongClick(item, adapterPosition)
                true
            }
        }

        companion object {
            fun itemsViewHolder(
                itemsStatusAdapter: CalciAdapter,
                parent: ViewGroup
            ): CalciViewHolder {
                itemsStatusAdapter.context = parent.context
                val binding =
                    CalciItemLayoutBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                return CalciViewHolder(binding)
            }
        }
        private fun roundOffDecimal(number: Double): Double? {
            val df = DecimalFormat("#.##")
            df.roundingMode = RoundingMode.FLOOR
            return df.format(number).toDouble()
        }

        fun Double.roundTo(numFractionDigits: Int): Double {
            val factor = 10.0.pow(numFractionDigits.toDouble())
            return (this * factor).roundToInt() / factor
        }
    }

    // override fun getItemCount(): Int = data.size
    class ItemsDiffCallback : DiffUtil.ItemCallback<CalciItemsModel>() {
        override fun areItemsTheSame(
            oldItem: CalciItemsModel,
            newItem: CalciItemsModel
        ): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(
            oldItem: CalciItemsModel,
            newItem: CalciItemsModel
        ): Boolean {
            return oldItem == newItem
        }
    }
}

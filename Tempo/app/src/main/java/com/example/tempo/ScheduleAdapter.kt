package com.example.tempo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView

class ScheduleAdapter(private val rows: List<ScheduleRow>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_SECTION = 0
        private const val TYPE_ITEM = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (rows[position]) {
            is ScheduleRow.Section -> TYPE_SECTION
            is ScheduleRow.Item -> TYPE_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_SECTION) {
            val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_layout_header, parent, false)
            SectionViewHolder(v)
        } else {
            val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_schedule, parent, false)
            ItemViewHolder(v)
        }
    }

    override fun getItemCount(): Int = rows.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val row = rows[position]) {
            is ScheduleRow.Item -> {
                (holder as ItemViewHolder).bind(row)
                holder.itemView.setOnClickListener {
                    val ctx = holder.itemView.context
                    // Use the real item if available, otherwise fallback to a sample
                    val title = row.activity.ifEmpty { "Sample Study" }
                    val category = row.status.ifEmpty { "Study" }
                    val start = row.time.ifEmpty { "09:00" }
                    val finish = "10:00" // arbitrary finish time
                    val details = row.details.ifEmpty { "No extra details." }

                    val sheet = StudyBlockBottomSheet.newInstance(
                        title = title,
                        type = category,
                        start = start,
                        finish = finish,
                        details = details
                    )
                    if (ctx is FragmentActivity) {
                        sheet.show(ctx.supportFragmentManager, "study_block")
                    }
                }
            }
            is ScheduleRow.Section -> {
                (holder as SectionViewHolder).bind(row)
            }
        }
    }

    class SectionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvSection: TextView = view.findViewById(R.id.tvSection)
        fun bind(section: ScheduleRow.Section) {
            tvSection.text = section.title
        }
    }

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvTime: TextView = view.findViewById(R.id.tvTime)
        private val tvActivity: TextView = view.findViewById(R.id.tvActivity)
        private val tvDetails: TextView = view.findViewById(R.id.tvDetails)
        private val tvStatus: TextView = view.findViewById(R.id.tvStatus)

        fun bind(item: ScheduleRow.Item) {
            tvTime.text = item.time
            tvActivity.text = item.activity
            tvDetails.text = item.details
            tvStatus.text = item.status
        }
    }
}

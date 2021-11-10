package com.example.nestedrecyclerview02.adapter

import android.os.Parcelable
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nestedrecyclerview02.AnimalSection
import com.example.nestedrecyclerview02.R
import com.example.nestedrecyclerview02.adapter.base.BaseAdapter
import com.example.nestedrecyclerview02.adapter.base.BaseViewHolder
import org.w3c.dom.Text

class AnimalSectionAdapter (
    items : List<AnimalSection> = emptyList()
) : BaseAdapter<AnimalSection>(R.layout.item_rv_animal_section,items){

    private val scrollState : MutableMap<String,Parcelable?> = mutableMapOf()
    private val viewPool = RecyclerView.RecycledViewPool()

    override fun bind(itemView: View, item: AnimalSection, position: Int, viewHolder: BaseViewHolderImp) {
        itemView.findViewById<TextView>(R.id.sectionTitleTextView).text = item.title

        //set scroll rv
        val layoutManager = LinearLayoutManager(itemView.context,LinearLayoutManager.HORIZONTAL,false)
        layoutManager.initialPrefetchItemCount = 4

        val sectionRv = itemView.findViewById<RecyclerView>(R.id.titledSectionRecycler)
        sectionRv.apply {
            setRecycledViewPool(viewPool)
            this.layoutManager = layoutManager
            this.adapter = AnimalAdapter(item.animals)
        }

        //restore horizontal scroll state
        val key = getSectionId(viewHolder.adapterPosition)
        val state = scrollState[key]
        state?.let {
            sectionRv.layoutManager?.onRestoreInstanceState(state)
        }?:run {
            sectionRv.layoutManager?.scrollToPosition(0)
        }
    }

    private fun getSectionId(position: Int) : String{
        return items[position].id
    }

    override fun onViewRecycled(holder: BaseViewHolder<AnimalSection>) {
        super.onViewRecycled(holder)

        //save horizontal scroll state
        val key = getSectionId(holder.layoutPosition)
        scrollState[key] =
            holder.itemView.findViewById<RecyclerView>(R.id.titledSectionRecycler).layoutManager?.onSaveInstanceState()
    }


}
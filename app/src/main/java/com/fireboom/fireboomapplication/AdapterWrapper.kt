package com.fireboom.fireboomapplication

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

/**
 * 能够添加header和footer的adapter装饰类
 * Created by WangYu on 2018/4/11.
 */
class AdapterWrapper(private val mOriginAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val headers: SparseArrayCompat<View> = SparseArrayCompat()
    private val footers: SparseArrayCompat<View> = SparseArrayCompat()

    private val VALUE_INT_HEAD_TYPE = 10000
    private val VALUE_INT_FOOT_TYPE = 20000

    override fun getItemCount(): Int = headers.size() + mOriginAdapter.itemCount + footers.size()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when {
            headers[viewType] != null -> HeadViewHolder(headers[viewType])
            footers[viewType] != null -> HeadViewHolder(footers[viewType])
            else -> mOriginAdapter.onCreateViewHolder(parent, viewType)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when{
            isHeaderPos(position) ->return
            isFooterPos(position) ->return
            else -> mOriginAdapter.onBindViewHolder(holder,position-headers.size() )
        }
    }


    override fun getItemViewType(position: Int): Int {
        return when {
            isHeaderPos(position) -> headers.keyAt(position)
            isFooterPos(position) -> footers.keyAt(position - headers.size() - mOriginAdapter.itemCount)
            else -> mOriginAdapter.getItemViewType(position-headers.size())
        }
    }

    private fun isHeaderPos(pos: Int) = pos < headers.size()
    private fun isFooterPos(pos: Int) = pos >= headers.size() + mOriginAdapter.itemCount
    fun addHeader(view: View) = headers.put(VALUE_INT_HEAD_TYPE + headers.size(), view)
    fun addFooter(view: View) = footers.put(VALUE_INT_FOOT_TYPE + footers.size(), view)

    fun getHeaderSize()=headers.size()
    fun getFooterSize()=footers.size()
}

class HeadViewHolder(view: View) : RecyclerView.ViewHolder(view)
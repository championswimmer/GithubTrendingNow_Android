package tech.arnav.githubtrending.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import tech.arnav.lib.trendinggithub.models.BaseModel

/**
 * @author championswimmer
 *
 * A base class to create list adapters from.
 *
 * @param layoutId The row item layout for this adapter
 * @param bindView (optional) layout <-> data binding function. if not provided
 *          will fall-back to using the [baseBindView] function
 */
abstract class BaseListAdapter<T : BaseModel>(
    @LayoutRes val layoutId: Int,
    val bindView: ((itemView: View, item: T) -> Unit)?
) :
    ListAdapter<T, BaseListAdapter.BaseViewHolder>(
        object : DiffUtil.ItemCallback<T>() {
            override fun areItemsTheSame(oldItem: T, newItem: T) =
                oldItem.equalsId(newItem)

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: T, newItem: T) =
                oldItem == newItem
        }
    ) {

    abstract fun baseBindView(itemView: View, item: T)

    class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val rootView = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return BaseViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        bindView?.let {
            it(holder.itemView, getItem(position))
        } ?: baseBindView(holder.itemView, getItem(position))

    }
}
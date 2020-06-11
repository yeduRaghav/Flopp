package com.yrgv.flopp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.yrgv.flopp.R
import com.yrgv.flopp.ui.main.ListingsAdapter.ListingViewHolder
import com.yrgv.flopp.util.ListingClickListener

/**
 * Adapter to render the listings in a list
 */
class ListingsAdapter(private val clickListener: ListingClickListener) :
    ListAdapter<ListingListItem, ListingViewHolder>(ListAdapterDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListingViewHolder {
        return ListingViewHolder.get(parent)
    }

    override fun onBindViewHolder(holder: ListingViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    private class ListAdapterDiffCallback : DiffUtil.ItemCallback<ListingListItem>() {
        override fun areItemsTheSame(oldItem: ListingListItem, newItem: ListingListItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ListingListItem,
            newItem: ListingListItem
        ): Boolean {
            return (oldItem.title == newItem.title
                    && oldItem.location == newItem.location
                    && oldItem.price == newItem.price
                    && oldItem.image == newItem.image)
        }
    }

    class ListingViewHolder private constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        companion object {
            fun get(parent: ViewGroup): ListingViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_listing_list_item, parent, false)
                return ListingViewHolder(view)
            }
        }

        private val imageView = itemView.findViewById<ImageView>(R.id.listing_item_image)
        private val locationView = itemView.findViewById<TextView>(R.id.listing_item_location)
        private val priceView = itemView.findViewById<TextView>(R.id.listing_item_price)
        private val titleView = itemView.findViewById<TextView>(R.id.listing_item_title)

        fun bind(listItem: ListingListItem, listingClickListener: ListingClickListener) {
            locationView.text = listItem.location
            priceView.text = listItem.price
            titleView.text = listItem.title
            Picasso.get()
                .load(listItem.image)
                .placeholder(R.drawable.image_placeholder)
                .into(imageView)
            itemView.setOnClickListener {
                listingClickListener(listItem)
            }
        }
    }

}
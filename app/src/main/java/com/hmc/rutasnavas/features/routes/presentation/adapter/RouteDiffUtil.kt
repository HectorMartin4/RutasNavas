package com.hmc.rutasnavas.features.routes.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hmc.rutasnavas.features.routes.domain.Route

class RouteDiffUtil : DiffUtil.ItemCallback<Route>() {
    override fun areItemsTheSame(oldItem: Route, newItem: Route): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Route, newItem: Route): Boolean {
        return oldItem == newItem
    }
}
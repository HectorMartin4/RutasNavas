package com.hmc.rutasnavas.features.routes.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.hmc.rutasnavas.R
import com.hmc.rutasnavas.features.routes.domain.Route

class RouteAdapter : ListAdapter<Route, RouteViewHolder>(RouteDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RouteViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_route_item, parent, false)
        return RouteViewHolder(view)
    }

    override fun onBindViewHolder(holder: RouteViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = currentList.size
}
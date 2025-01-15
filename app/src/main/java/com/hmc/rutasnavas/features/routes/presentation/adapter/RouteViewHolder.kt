package com.hmc.rutasnavas.features.routes.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.hmc.rutasnavas.databinding.ViewRouteItemBinding
import com.hmc.rutasnavas.features.routes.domain.Route

class RouteViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    val binding = ViewRouteItemBinding.bind(view)

    fun render(
        route: Route,
        onClickDetail: ((String) -> Unit)?,
        onClickDelete: ((String) -> Unit)?
    ) {
        binding.apply {
            routeTitle.text = route.title
            view.setOnClickListener {
                onClickDetail!!.invoke(route.id)
            }
            iconDelete.setOnClickListener {
                onClickDelete!!.invoke(route.id)
            }
        }
    }

}
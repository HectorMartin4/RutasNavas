package com.hmc.rutasnavas.features.routes.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolylineOptions
import com.hmc.rutasnavas.R
import com.hmc.rutasnavas.databinding.FragmentRouteDetailBinding
import com.hmc.rutasnavas.features.routes.domain.RouteResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RouteDetailFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentRouteDetailBinding? = null
    private val binding get() = _binding!!
    private val argument: RouteDetailFragmentArgs by navArgs()

    private lateinit var map: GoogleMap

    val viewModel by viewModels<RouteDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRouteDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = SupportMapFragment.newInstance()
        childFragmentManager.beginTransaction()
            .replace(R.id.map, mapFragment)
            .commit()
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        this.map = map
    }

    private fun setupObservers() {
        val observer = Observer<RouteDetailViewModel.RouteUiState> {
            createRoute(it.route!!.start, it.route.end)
        }
        viewModel.routeUiState.observe(viewLifecycleOwner, observer)
    }

    private fun createRoute(start: String, end: String) {
        CoroutineScope(Dispatchers.IO).launch {
            drawRoute(viewModel.createRoute(start, end))
        }
    }

    private fun drawRoute(route: RouteResponse?) {
        val polyLineOptions = PolylineOptions()
        route?.features?.first()?.geometry?.coordinates?.forEach {
            polyLineOptions.add(LatLng(it[0], it[1]))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
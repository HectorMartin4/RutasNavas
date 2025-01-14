package com.hmc.rutasnavas.features.map.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Polyline
import com.google.android.gms.maps.model.PolylineOptions
import com.hmc.rutasnavas.R
import com.hmc.rutasnavas.databinding.FragmentMapBinding
import com.hmc.rutasnavas.features.routes.domain.RouteResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MapFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!

    private lateinit var mapFragment: GoogleMap
    private lateinit var btnCalculateRoute: Button

    private lateinit var start: String
    private lateinit var end: String

    private var poly: Polyline? = null

    private val viewModel: MapViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        setupView()
        return binding.root
    }

    private fun setupView() {
        btnCalculateRoute = binding.btnRoute
        btnCalculateRoute.setOnClickListener {
            start = ""
            end = ""
            poly?.remove()
            poly = null
            Toast.makeText(
                requireContext(),
                "Selecciona un punto de inicio y uno de final",
                Toast.LENGTH_SHORT
            ).show()
            if (::mapFragment.isInitialized) {
                mapFragment.setOnMapClickListener {
                    if (start.isEmpty()) {
                        start = "${it.longitude},${it.latitude}"
                    } else if (end.isEmpty()) {
                        end = "${it.longitude},${it.latitude}"
                        createRoute(start, end)
                    }
                }
            }
        }
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
        this.mapFragment = map
    }

    private fun createRoute(start: String, end: String) {
        CoroutineScope(Dispatchers.IO).launch {
            drawRoute(viewModel.createRoute(start, end))
        }
    }

    private fun drawRoute(route: RouteResponse?) {
        val polyLineOptions = PolylineOptions()
        route?.features?.first()?.geometry?.coordinates?.forEach {
            polyLineOptions.add(LatLng(it[1], it[0]))
        }
        CoroutineScope(Dispatchers.Main).launch {
            poly = mapFragment.addPolyline(polyLineOptions)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
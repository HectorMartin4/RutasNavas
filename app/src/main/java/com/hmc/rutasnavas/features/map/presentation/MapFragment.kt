package com.hmc.rutasnavas.features.map.presentation

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Polyline
import com.google.android.gms.maps.model.PolylineOptions
import com.google.android.material.textfield.TextInputEditText
import com.hmc.rutasnavas.R
import com.hmc.rutasnavas.databinding.FragmentMapBinding
import com.hmc.rutasnavas.features.routes.domain.Route
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
    private lateinit var btnAddRoute: Button

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
        btnAddRoute = binding.btnAdd
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

                        btnAddRoute.setOnClickListener {
                            showInputDialog(requireContext(), start, end)
                        }
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
        enableLocation()
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

    private fun isLocationPermissionGranted() = ContextCompat.checkSelfPermission(
        requireContext(),
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

    @SuppressLint("MissingPermission")
    private fun enableLocation() {
        if (!::mapFragment.isInitialized) return
        if (isLocationPermissionGranted()) {
            mapFragment.isMyLocationEnabled = true
        } else {
            requestLocationPermission()
        }
    }

    private fun requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        ) {
            Toast.makeText(requireContext(), "Acepta los permisos en ajustes", Toast.LENGTH_SHORT)
                .show()
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE_LOCATION
            )
        }
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE_LOCATION -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mapFragment.isMyLocationEnabled = true
            } else {
                Toast.makeText(
                    requireContext(),
                    "Acepta los permisos en ajustes",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }
    }

    companion object {
        const val REQUEST_CODE_LOCATION = 0
    }

    @SuppressLint("MissingPermission")
    override fun onResume() {
        super.onResume()
        if (!isLocationPermissionGranted()) {
            mapFragment.isMyLocationEnabled = false
            Toast.makeText(requireContext(), "Acepta los permisos en ajustes", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun showInputDialog(context: Context, start: String, end: String) {

        val inputField = TextInputEditText(context).apply {
            hint = "Titulo de la ruta"
        }

        val dialog = AlertDialog.Builder(context)
            .setTitle("Añadir Título")
            .setMessage("Introduce un título para la ruta:")
            .setView(inputField)
            .setPositiveButton("Guardar") { _, _ ->
                val title = inputField.text.toString().trim()

                if (title.isNotEmpty()) {
                    val save = Route(
                        title = title,
                        start = start,
                        end = end
                    )
                    viewModel.saveRoute(save)
                    Toast.makeText(context, "Título guardado: $title", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "El título no puede estar vacío", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            .setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        dialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
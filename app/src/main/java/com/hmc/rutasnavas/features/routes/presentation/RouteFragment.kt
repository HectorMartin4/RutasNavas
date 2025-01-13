package com.hmc.rutasnavas.features.routes.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.hmc.rutasnavas.R
import com.hmc.rutasnavas.databinding.FragmentRoutesBinding
import com.hmc.rutasnavas.features.routes.presentation.adapter.RouteAdapter

class RouteFragment : Fragment() {

    private var _binding: FragmentRoutesBinding? = null
    private val binding get() = _binding!!
    private val routeAdapter = RouteAdapter()

    val viewModel by viewModels<RouteViewModel>()

    private val skeleton: Skeleton by lazy {
        binding.listRoute.applySkeleton(R.layout.view_route_item, 6)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRoutesBinding.inflate(inflater, container, false)
        setupView()
        return binding.root
    }

    private fun setupView() {
        binding.apply {
            listRoute.apply {
                layoutManager = LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )
                adapter = routeAdapter
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    private fun setupObservers() {
        val observer = Observer<RouteViewModel.UiState> {
            if (it.isLoading) {
                skeleton.showSkeleton()
            } else {
                skeleton.showOriginal()
                routeAdapter.submitList(it.routes)
            }
        }
        viewModel.uiState.observe(viewLifecycleOwner, observer)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.ah.studio.blueapp.ui.screens.main.viewModel

import com.ah.studio.blueapp.ui.screens.main.domain.dto.BottomNavItemResponse
import kotlinx.coroutines.flow.Flow

interface IBottomNavViewModel {
    fun bottomNavItems() : Flow<MutableList<BottomNavItemResponse>>
    abstract fun handleError(throwable: Throwable)

    val visiblePermissionDialogQueue : MutableList<String>

    fun dismissDialog()
    fun onPermissionResult(permission: String, isGranted: Boolean)
}
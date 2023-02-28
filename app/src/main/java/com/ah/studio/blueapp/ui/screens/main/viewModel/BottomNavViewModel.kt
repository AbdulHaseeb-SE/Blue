package com.ah.studio.blueapp.ui.screens.main.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ah.studio.blueapp.ui.screens.main.domain.dto.BottomNavItemResponse
import com.ah.studio.blueapp.ui.screens.main.domain.repository.IBottomNavItemRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

class BottomNavViewModel(
    private val repository: IBottomNavItemRepository
) : ViewModel(), IBottomNavViewModel {

    private val bottomNavItems =
        MutableStateFlow<MutableList<BottomNavItemResponse>>(mutableListOf())

    private val baseViewModelScope = viewModelScope + CoroutineExceptionHandler { _, throwable ->
        handleError(throwable)
    }

    init {
        baseViewModelScope.launch {
            val items = repository.getBottomNavItems()
            bottomNavItems.value = items
        }
    }

    override fun bottomNavItems(): Flow<MutableList<BottomNavItemResponse>> = bottomNavItems

    override fun handleError(throwable: Throwable) {}


    override val visiblePermissionDialogQueue = mutableStateListOf<String>()

    override fun dismissDialog() {
        visiblePermissionDialogQueue.removeFirst()
    }

    override fun onPermissionResult(
        permission: String,
        isGranted: Boolean
    ) {
        if (!isGranted && !visiblePermissionDialogQueue.contains(permission)) {
            visiblePermissionDialogQueue.add(permission)
        }
    }
}
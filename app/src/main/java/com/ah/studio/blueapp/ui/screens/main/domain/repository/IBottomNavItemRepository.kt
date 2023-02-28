package com.ah.studio.blueapp.ui.screens.main.domain.repository

import com.ah.studio.blueapp.ui.screens.main.domain.dto.BottomNavItemResponse

interface IBottomNavItemRepository {
    fun getBottomNavItems() :  MutableList<BottomNavItemResponse>
}
package com.slcm.slcmagroapp.views.fab

/**
 * Created by Nataraj Bingi on Feb 16, 2022
 */
interface OnFabItemClickListener {
    fun onItemClick(item: ItemsModel, position: Int)

    fun onItemLongClick(item: ItemsModel, position: Int)
}
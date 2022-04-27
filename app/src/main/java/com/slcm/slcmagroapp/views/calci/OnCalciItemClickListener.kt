package com.slcm.slcmagroapp.views.calci

/**
 * Created by Nataraj Bingi on Feb 16, 2022
 */
interface OnCalciItemClickListener {
    fun onItemClick(item: CalciItemsModel, position: Int)

    fun onItemLongClick(item: CalciItemsModel, position: Int)
}
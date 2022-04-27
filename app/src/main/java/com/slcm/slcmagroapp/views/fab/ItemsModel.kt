package com.slcm.slcmagroapp.views.fab

/**
 * Created by Nataraj Bingi on Feb 16, 2022
 */
data class ItemsModel(val image: Int, var title: String, var list: List<Int> = emptyList()) {
    var urlString: String = ""
}

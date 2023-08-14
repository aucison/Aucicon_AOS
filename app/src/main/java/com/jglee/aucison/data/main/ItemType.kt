package com.jglee.aucison.data.main

import androidx.annotation.StringRes
import com.jglee.aucison.R

enum class ItemType(@StringRes val resource: Int) {
    NORMAL(R.string.item_type_normal),
    HANDMADE(R.string.item_type_handmade),
}

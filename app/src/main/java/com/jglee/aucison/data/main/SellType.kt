package com.jglee.aucison.data.main

import androidx.annotation.StringRes
import com.jglee.aucison.R

enum class SellType(@StringRes val resource: Int) {
    NORMAL(R.string.item_type_normal),
    HANDMADE(R.string.item_type_handmade),
    AUCTION(R.string.item_type_auction),
    NON_AUCTION(R.string.item_type_non_auction)
}

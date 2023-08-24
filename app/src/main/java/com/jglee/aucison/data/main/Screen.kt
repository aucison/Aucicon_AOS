package com.jglee.aucison.data.main

import androidx.annotation.StringRes
import com.jglee.aucison.R

enum class Screen(@StringRes val resource: Int) {
    MAIN(R.string.screen_name_home),
    MY_PAGE(R.string.screen_name_my_page),
    SELL(R.string.screen_name_sell),
    MARKET(R.string.screen_name_market),
    SEARCH(R.string.screen_name_search)
}
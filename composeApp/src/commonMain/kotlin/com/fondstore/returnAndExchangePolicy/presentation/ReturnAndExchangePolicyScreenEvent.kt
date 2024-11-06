package com.fondstore.returnAndExchangePolicy.presentation

sealed class ReturnAndExchangePolicyScreenEvent {
    data object RefreshScreen : ReturnAndExchangePolicyScreenEvent()
    data object CloseScreen : ReturnAndExchangePolicyScreenEvent()
}
package com.mytestprogram.rickmortyapplication.utils


sealed class UIEvent {
    data class ShowToast(val message: String) : UIEvent()
}

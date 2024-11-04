package diy.arirangnewsapi.util.event

import diy.arirangnewsapi.screen.main.MainTabMenu
import kotlinx.coroutines.flow.MutableSharedFlow

class MenuChangeEventBus {

    val mainTabMenuFlow = MutableSharedFlow<MainTabMenu>()

    suspend fun changeMenu(menu: MainTabMenu){
        mainTabMenuFlow.emit(menu)
    }
}
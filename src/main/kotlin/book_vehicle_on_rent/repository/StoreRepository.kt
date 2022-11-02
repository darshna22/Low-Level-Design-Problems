package book_vehicle_on_rent.repository

import book_vehicle_on_rent.model.store.Store

class StoreRepository(mStoreList: MutableList<Store>) {
    private var storeList = mutableListOf<Store>()

    init {
        this.storeList = mStoreList
    }

    fun addStore(store: Store) {
        storeList.add(store)
    }

    fun removeStore(store: Store) {
        storeList.remove(store)
    }

    fun getStoreList() = storeList
}
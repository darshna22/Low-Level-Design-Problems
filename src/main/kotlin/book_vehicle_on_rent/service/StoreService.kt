package book_vehicle_on_rent.service

import book_vehicle_on_rent.model.store.Store
import book_vehicle_on_rent.repository.StoreRepository

class StoreService(mStoreList: MutableList<Store>) {
    private var storeRepository: StoreRepository = StoreRepository(mStoreList)


    fun addStore(store: Store) {
        storeRepository.addStore(store)
    }

    fun removeStore(store: Store) {
        storeRepository.removeStore(store)
    }

    fun getStoreList() = storeRepository.getStoreList()
}
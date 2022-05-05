package services;

import models.Store;
import models.User;
import repositories.StoreRepository;
import repositories.UserRepository;

public class StoreService {
    private final StoreRepository storeRepository;
    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public void saveStore(Store store){
        if(storeRepository.findByName(store.getStoreName()) == null) {
            this.storeRepository.SaveStore(store);
        }
        else{
            System.out.println("store already exists");
        }
    }
}

package controllers;

import models.Store;
import models.User;
import services.StoreService;
import services.UserService;

import java.util.Scanner;

public class StoreController {
    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    public void saveStore(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter the store name you want to save");
        String name = scanner.nextLine();
        System.out.println("please enter the ID of the user");
        int id = scanner.nextInt();
        Store store = new Store();
        store.setStoreName(name);
        store.setCreatedBy(id);


        storeService.saveStore(store);
    }
}

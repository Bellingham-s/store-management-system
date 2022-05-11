import controllers.ProductController;
import controllers.StoreController;
import controllers.UserController;
import enumerations.MenuType;
import models.Product;
import models.Store;
import models.User;
import org.apache.log4j.Logger;
import repositories.ProductRepository;
import repositories.StoreRepository;
import repositories.UserRepository;
import services.ProductService;
import services.StoreService;
import services.UserService;

import java.util.Properties;
import java.util.Scanner;

public class App {
    private static final String LOG_FILE = "log4j properties";
    private static final Logger log = Logger.getLogger(App.class);
    Properties properties = new Properties();

    private UserRepository userRepository;
    private UserService userService;
    private UserController userController;
    private MenuType menuType;

    public App() {
        //autowiring
        this.userRepository = new UserRepository();
        this.userService = new UserService(userRepository);
        this.userController = new UserController(userService);
        this.menuType = MenuType.LOGIN_MENU;
    }

    public static void main(String[] args) {


//        StoreRepository storeRepository = new StoreRepository();
//        StoreService storeService = new StoreService(storeRepository);
//        StoreController storeController = new StoreController(storeService);
//        storeController.saveStore();
//
//        ProductRepository productRepository = new ProductRepository();
//        ProductService productService = new ProductService(productRepository);
//        ProductController productController = new ProductController(productService);
//        productController.saveProduct();

        App app = new App();
        app.RenderMenu(MenuType.LOGIN_MENU);
    }

    public void storeManagementMenu() {
        System.out.print("Welcome to SMS. \nPlease enter your username to login.");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        User user = this.userController.findByUserName(name);

        if (user != null) {
            this.menuType = MenuType.MAIN_MENU;
            System.out.print("Welcome back \nPlease select an option to proceed \na. Users \nb. Stores \nc. Products \nd. Go Back.");
            String menu = scanner.nextLine();
            if (menu != null) {
                switch (menu) {
                    case "a":
                        this.menuType = MenuType.USER_MENU;
                        System.out.println("Please select one of this options");
                        System.out.println("1. find a User by name \n2. find all users");
                        String userMenu = scanner.nextLine();
                        switch (userMenu) {
                            case "1":
                                System.out.println("enter the name of the user you want to find");
                                String userNameToFind = scanner.nextLine();
                                User user1 = this.userController.findByUserName(userNameToFind);

                                if (user1 == null) {
                                    System.out.println("user doesn't exist");
                                }
                                break;
                            case "2":
                                System.out.println("these are all the users in the database " + userController.getUsers());
                                break;
                        }
                        break;
                    case "b":
                        this.menuType = MenuType.STORES_MENU;
                        System.out.println("Please select one of this options");
                        System.out.println("1. find a Store by name \n2. find all Stores");
                        break;
                    case "c":
                        this.menuType = MenuType.PRODUCT_MENU;
                        System.out.println("Please select one of this Options");
                        System.out.println("1. find a Product by name \n2. find all Products");

                        break;
                    case "d":

                        break;
                }
            }
        } else {
            System.out.print("user not found please try again.");
        }


    }

    public void RenderMenu(MenuType menuType){
     String menu = menuType.name();
     String loginmenu = MenuType.LOGIN_MENU.toString();
        switch (menu) {
            case "LOGIN_MENU":
                loginMenu();
                break;
            case "STORES_MENU":
               storesMenu();
               break;
            case "PRODUCT_MENU":
              productsMenu();
              break;
            case "USER_MENU":
                usersMenu();
                break;
            case "MAIN_MENU":
                mainMenu();
                break;
            default : loginMenu();
        }
    }
    public void loginMenu() {
        System.out.print("Welcome to SMS. \nPlease enter your username to login.");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        User user = this.userController.findByUserName(name);
        if (user != null) {
            RenderMenu(MenuType.MAIN_MENU);
        }
        RenderMenu(MenuType.LOGIN_MENU);
    }

    public void mainMenu() {
        System.out.print("Welcome back \nPlease select an option to proceed \na. Users \nb. Stores \nc. Products \nd. Go Back.");
        Scanner scanner = new Scanner(System.in);
        String menu = scanner.nextLine();

        switch (menu) {
            case "a":
                RenderMenu(MenuType.USER_MENU);
            case "b":
                RenderMenu(MenuType.STORES_MENU);
            case "c":
                RenderMenu(MenuType.PRODUCT_MENU);
            case "d":
                RenderMenu(MenuType.LOGIN_MENU);
            default : RenderMenu(MenuType.MAIN_MENU);
        }
    }

    public void usersMenu() {
        System.out.println("Please select one of this options");
        System.out.println("1. find a User by name \n2. find all users");
        Scanner scanner = new Scanner(System.in);
        String userMenu = scanner.nextLine();
        switch (userMenu) {
            case "1":
                System.out.println("enter the name of the user you want to find");
                String userNameToFind = scanner.nextLine();
                User user1 = this.userController.findByUserName(userNameToFind);

                if (user1 == null) {
                    System.out.println("user doesn't exist");
                }
                break;
            case "2":
                System.out.println("these are all the users in the database " + userController.getUsers());
                break;
        }
    }

    public void storesMenu() {
        System.out.println("Please select one of this options");
        System.out.println("1. find a Store by name \n2. find all Stores");
        Scanner scanner = new Scanner(System.in);
    }
    public void productsMenu() {
        System.out.println("Please select one of this Options");
        System.out.println("1. find a Product by name \n2. find all Products");
    }


}

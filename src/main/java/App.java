import controllers.ProductController;
import controllers.StoreController;
import controllers.UserController;
import models.Product;
import models.Store;
import org.apache.log4j.Logger;
import repositories.ProductRepository;
import repositories.StoreRepository;
import repositories.UserRepository;
import services.ProductService;
import services.StoreService;
import services.UserService;

import java.util.Properties;

public class App {
    private static final String LOG_FILE = "log4j properties";
    private static final Logger log = Logger.getLogger(App.class);
    Properties properties = new Properties();

    public App() {

    }

    public static void main(String[] args) {
        log.info("about to save a user");
        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService(userRepository);
        UserController userController = new UserController(userService);
        userController.saveUser();

        log.info("able to save a new user");



        StoreRepository storeRepository = new StoreRepository();
        StoreService storeService = new StoreService(storeRepository);
        StoreController storeController = new StoreController(storeService);
        storeController.saveStore();

        ProductRepository productRepository = new ProductRepository();
        ProductService productService = new ProductService(productRepository);
        ProductController productController = new ProductController(productService);
        productController.saveProduct();


        log.info("All users in the db: " + userController.getUsers());



    }

}

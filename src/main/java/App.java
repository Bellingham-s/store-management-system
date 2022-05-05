import controllers.ProductController;
import controllers.StoreController;
import controllers.UserController;
import models.Product;
import models.Store;
import repositories.ProductRepository;
import repositories.StoreRepository;
import repositories.UserRepository;
import services.ProductService;
import services.StoreService;
import services.UserService;

public class App {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService(userRepository);
        UserController userController = new UserController(userService);
        userController.saveUser();


        StoreRepository storeRepository = new StoreRepository();
        StoreService storeService = new StoreService(storeRepository);
        StoreController storeController = new StoreController(storeService);
        storeController.saveStore();

        ProductRepository productRepository = new ProductRepository();
        ProductService productService = new ProductService(productRepository);
        ProductController productController = new ProductController(productService);
        productController.saveProduct();
    }
}

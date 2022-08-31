
package fr.m2i.javaspringmvc.service;


import fr.m2i.javaspringmvc.exception.InsufficientBalanceException;
import fr.m2i.javaspringmvc.exception.NotEnoughStockException;
import fr.m2i.javaspringmvc.exception.NotFoundException;
import fr.m2i.javaspringmvc.model.Product;
import fr.m2i.javaspringmvc.repository.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository repo;
    private final UserService userService;

    @Autowired
    public ProductService(ProductRepository repo, UserService userService) {
        this.repo = repo;
        this.userService = userService;
    }

    public List<Product> findAll() {
        return repo.findAll();
    }

    public Product findBydId(Long id) throws NotFoundException {
        return repo.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Product with id: " + id + " was not found");
        });
    }

    public void save(Product product) {
        repo.save(product);
    }

    public void buyProduct(Long id) throws Exception {
        // Si le produit avec l'id passé en paramètre n'existe pas une NotFoundException sera levée
        Product product = findBydId(id);

        // On vérifie le stock du produit trouvé
        // On lève une NotEnoughStockException si besoin
        if (product.getQuantity() < 1) {
            throw new NotEnoughStockException("Product with id: " + product.getId() + " has no more stock");
        }

        // Via le userService on vérifie que l'utilisateur a assez de crédit
        // On lève une InsufficientBalanceException si besoin
        if (userService.getBalance() < product.getPrice()) {
            throw new InsufficientBalanceException("User do not have enough balance for the product with id: " + product.getId());
        }
        
        // On met à jour la quantité du produit que l'utilisateur vient d'acheter
        product.setQuantity(product.getQuantity() - 1);

        // On met à jour le crédit de l'utilisateur
        userService.decreaseBalance(product.getPrice());
        
        // On persist les nouvelles données du produit
        save(product);
    }
}















/*
import fr.m2i.javaspringmvc.model.Product;
import fr.m2i.javaspringmvc.model.User;
import fr.m2i.javaspringmvc.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository repo;
    private final UserService userService;

    @Autowired
    public ProductService(ProductRepository repo, UserService userService) {
        this.repo = repo;
        this.userService = userService;
    }
    
    // lister les produits
    
    public List<Product> findAll() throws Exception {
        
        List<Product> productList= repo.findAll();
//        if (null == productList){
//            throw new Exception("product not found");
//        }
        return productList;
    }
    
    // find by id
     public Product getProductById(Long id){
        Optional<Product> product= repo.findById(id);
        return product.orElse(null);
    }
    
    // save
    
    // acheter un produit
    // -> Est ce que j'ai assez de crédit ?
    // -> Est ce que le produit existe ? il reste du stock ?
     @Transactional
    public Boolean buyProduct(Long idProduct, Integer qte) throws Exception{
        Double credit = userService.getBalance(); // toujours User 1
        Product product = getProductById(idProduct);
        if(product == null){
            return false;
        }
        
        if((qte>product.getQuantity()) || (product.getPrice()*qte)> credit ){
            return false;
        }
        
        product.setQuantity(product.getQuantity()-qte);
        userService.decreaseBalance(product.getPrice()*qte);
        return true;
        
        
    }
}
*/

package fr.m2i.javaspringmvc.service;

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

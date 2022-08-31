
package fr.m2i.javaspringmvc.controller;


import fr.m2i.javaspringmvc.exception.InsufficientBalanceException;
import fr.m2i.javaspringmvc.exception.NotEnoughStockException;
import fr.m2i.javaspringmvc.exception.NotFoundException;
import fr.m2i.javaspringmvc.form.BuyProductForm;
import fr.m2i.javaspringmvc.form.UserBalanceForm;
import fr.m2i.javaspringmvc.model.Product;
import fr.m2i.javaspringmvc.service.ProductService;
import fr.m2i.javaspringmvc.service.UserService;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DistributeurController {

    private final UserService userService;
    private final ProductService productService;

    @Autowired
    public DistributeurController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("/distributeur")
    public String showDistributeurPage() {
        return "distributeur";
    }

    @PostMapping("/addBalance")
    public String addBalance(@ModelAttribute("userBalanceForm") @Valid UserBalanceForm userBalanceForm,
            BindingResult result) {

        if (result.hasErrors()) {
            return "distributeur";
        }

        try {
            userService.addBalance(userBalanceForm.getBalance());
            return "redirect:distributeur";
        } catch (NotFoundException e) {
            result.rejectValue("balance", null, "Une erreur est survenue lors de l'ajout de crédit");
            return "distributeur";
        }
    }

    @PostMapping("/buyProduct")
    public String buyProduct(@ModelAttribute("buyProductForm") @Valid BuyProductForm buyProductForm,
            BindingResult result) {
        
        if (result.hasErrors()) {
            return "distributeur";
        }
        
        String errorMessage;

        try {
            productService.buyProduct(buyProductForm.getId());
            return "redirect:distributeur";
        } catch (NotFoundException nfe) {
            errorMessage = "Le produit demandé n'existe pas";
        } catch (NotEnoughStockException nese) {
            errorMessage = "Le produit demandé n'est plus en stock";
        } catch (InsufficientBalanceException ibe) {
            errorMessage = "Vous manquez de crédit pour le produit demandé";
        } catch (Exception e) {
            errorMessage = "Une erreur est survenue lors de l'achat";
        }

        result.rejectValue("id", null, errorMessage);
        return "distributeur";
    }
    
    @ModelAttribute("balance")
    public Double addBalanceBean() {
        try {
            return userService.getBalance();
        } catch (NotFoundException e) {
            // log user not found
            return 0.0;
        }
    }

    @ModelAttribute("products")
    public List<Product> addProductsBean() {
        try {
            return productService.findAll();
        } catch (Exception e) {
            // log no products in database
            return new ArrayList();
        }
    }

    @ModelAttribute("userBalanceForm")
    public UserBalanceForm addUserBalanceFormBean() {
        return new UserBalanceForm();
    }

    @ModelAttribute("buyProductForm")
    public BuyProductForm addBuyProductFormBean() {
        return new BuyProductForm();
    }
}




/*
import fr.m2i.javaspringmvc.form.UserBalanceForm;
import fr.m2i.javaspringmvc.form.BuyForm;
import fr.m2i.javaspringmvc.model.Product;
import fr.m2i.javaspringmvc.service.ProductService;
import fr.m2i.javaspringmvc.service.UserService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DistributeurController {

    private final UserService userService;
    private final ProductService productService;

    @Autowired
    public DistributeurController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("/distributeur")
    public String showDistributeurPage() {
        return "distributeur";
    }

    @ModelAttribute("balance")
    public Double addBalanceBean() throws Exception {
        return userService.getBalance();
    }

    @ModelAttribute("products")
    public List<Product> addProductsBean() throws Exception {
        return productService.findAll();
    }
    
    
    //-------------------------------
    // pour l'affichage des erreurs
    // dans le premier paramètre il faut bien préciser le nom du modelAttribute s'il est different de la classe
    // exemple: modelAttribut balanceForm et classe UserBalanceForm
    @PostMapping("/addBalance")
    public String addBalance(@ModelAttribute("balanceForm") @Valid UserBalanceForm balanceF,BindingResult result, ModelMap model) throws Exception{
        
        if (result.hasErrors()) {
            return "distributeur"; // redirect ecrase les message d'erreur: sans redirect l'uri ne change pas
        }
        userService.addBalance(balanceF.getBalance());
        return "redirect:distributeur";
    }
    
    @ModelAttribute("balanceForm") // nom de modeleAttribute utilisé dans le formulaire
    public UserBalanceForm addBalanceFormxxx() {
        return new UserBalanceForm();
    }
    //----------------------------
    
    //-----------------------------
    @PostMapping("/buyProduct")
    public String byProduct(@ModelAttribute @Valid BuyForm product,BindingResult result, ModelMap model) throws Exception{
        
        if (result.hasErrors()) {
            return "distributeur";
        }
        Boolean status = productService.buyProduct(product.getIdProduct(), product.getQuantity());
        if(status == false){
            result.addError(new ObjectError("buyError","l'operation d'achat non possible"));
            // jstl
            //model.addAttribute("errorMessage", "l'operation d'achat non possible, veuillez vérifier votre commande");
            //result.rejectValue("idProduct", null, "l'operation d'achat non possible"); // le message d'erreur est lié au champ
            return "distributeur";
        }
        return "redirect:distributeur";
    }
    
    @ModelAttribute("buyForm") // nom de modeleAttribute utilisé dans le formulaire
    public BuyForm addBuyProductForm() {
        return new BuyForm();
    }
    
    
    
    
    
    
//    @ModelAttribute("amount")
//    public List<Product> addAmountBean() throws Exception {
//        return userService.addBalance("credit");
//    }
}
*/
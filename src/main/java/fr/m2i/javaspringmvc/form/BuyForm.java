
package fr.m2i.javaspringmvc.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class BuyForm {
    
    @Min(value = 1, message = "Enter a valid id")
    @NotNull(message = "The field ID is mandatory")
    private Long idProduct;
    
    @Min(value = 1, message = "Enter a valid quantity")
    @NotNull(message = "The field ID is mandatory")
    private Integer quantity;

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    
    
    
    
}

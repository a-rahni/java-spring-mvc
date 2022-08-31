
package fr.m2i.javaspringmvc.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class UserBalanceForm {

    @Min(value = 1, message = "Le montant que vous souhaitez ajouter doit être positif")
    @NotNull(message = "Le champs 'montant' est obligatoire")
    private Double balance;

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}




/*
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


public class UserBalanceForm {
    
    @Min(value = 0, message = "Enter a valid balance")
    @NotNull(message = "The field balance is mandatory")
    private Double balance;

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
    
    
    
}
*/
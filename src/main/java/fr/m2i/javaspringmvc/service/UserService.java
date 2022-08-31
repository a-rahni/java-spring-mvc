
package fr.m2i.javaspringmvc.service;


import fr.m2i.javaspringmvc.exception.NotFoundException;
import fr.m2i.javaspringmvc.model.User;
import fr.m2i.javaspringmvc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repo;

    @Autowired
    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public Double getBalance() throws NotFoundException {
        User user = repo.findById(1L).orElseThrow(() -> {
            throw new NotFoundException("User with id: 1 was not found");
        });
        return user.getBalance();
    }

    public void addBalance(Double balance) throws NotFoundException {
        User user = repo.findById(1L).orElseThrow(() -> {
            throw new NotFoundException("User with id: 1 was not found");
        });
        user.setBalance(user.getBalance() + balance);

        repo.save(user);
    }

    public void decreaseBalance(Double balance) throws NotFoundException {
        User user = repo.findById(1L).orElseThrow(() -> {
            throw new NotFoundException("User with id: 1 was not found");
        });
        user.setBalance(user.getBalance() - balance);

        repo.save(user);
    }
}







/*
import fr.m2i.javaspringmvc.model.User;
import fr.m2i.javaspringmvc.repository.UserRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    private final UserRepository repo;
    
    @Autowired
    public UserService(UserRepository repo){
        this.repo = repo;
    }
    
    public User getUserById(Long id){
        Optional<User> user= repo.findById(id);
        return user.orElse(null);
    }
    
    public Double getBalance() throws Exception {
        // Code avec lambda
         User user = repo.findById(1L).orElseThrow(() -> new Exception()); // Todo throw a custom exception called NotFoundException
         return user.getBalance();

//        // Code sans lambda
//        Optional<User> userOptional = repo.findById(1L);
//        User userFounded;
//
//        if (!userOptional.isPresent()) {
//            throw new Exception();
//        }
//
//        userFounded = userOptional.get();
//        
//        return userFounded.getBalance();
    }
    
    @Transactional
     public void addBalance(Double balance) throws Exception {
        // Code avec lambda
         User user = repo.findById(1L).orElseThrow(() -> new Exception()); // Todo throw a custom exception called NotFoundException
        
         user.setBalance((user.getBalance()+balance));
     }
     
     public void decreaseBalance(Double balance) throws Exception {
        User user = repo.findById(1L).orElseThrow(() -> new Exception()); // Todo throw a custom exception called NotFoundException
        user.setBalance(user.getBalance() - balance);

        repo.save(user);
    }
    
}
*/
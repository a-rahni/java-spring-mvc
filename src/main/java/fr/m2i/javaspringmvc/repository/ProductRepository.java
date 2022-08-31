
package fr.m2i.javaspringmvc.repository;



import fr.m2i.javaspringmvc.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    
}

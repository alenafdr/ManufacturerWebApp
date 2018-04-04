package manufacturerwebapp.dao;

import manufacturerwebapp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.UUID;

public interface ProductDao extends JpaRepository<Product, UUID> {
    @Modifying
    @Query("update Product p set p.name = ?1, p.price = ?2, p.manufacturer = ?3 where p.id = ?4")
    void update(String name, BigDecimal price, UUID manufacturerID, UUID idProduct);
}

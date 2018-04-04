package manufacturerwebapp.service;

import manufacturerwebapp.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    void save(Product product);
    List<Product> list();
    void update(Product product);
    Product getById(UUID id);
    void remove(UUID id);
}

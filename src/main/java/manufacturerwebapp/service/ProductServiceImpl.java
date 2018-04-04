package manufacturerwebapp.service;

import manufacturerwebapp.dao.ProductDao;
import manufacturerwebapp.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;

    @Override
    @Transactional
    public void save(Product product) {
        product.setId(UUID.randomUUID());
        productDao.save(product);
    }

    @Override
    @Transactional
    public List<Product> list() {
        return productDao.findAll();
    }

    @Override
    @Transactional
    public void update(Product product) {
        productDao.update(product.getName(), product.getPrice(), product.getManufacturer().getId(),product.getId());
    }

    @Override
    @Transactional
    public Product getById(UUID id) {
        return productDao.findOne(id);
    }

    @Override
    @Transactional
    public void remove(UUID id) {
        productDao.delete(id);
    }
}

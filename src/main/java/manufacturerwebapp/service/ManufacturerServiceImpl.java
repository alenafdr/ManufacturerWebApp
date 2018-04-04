package manufacturerwebapp.service;

import manufacturerwebapp.dao.ManufacturerDao;
import manufacturerwebapp.model.Manufacturer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    @Autowired
    ManufacturerDao manufacturerDao;

    @Override
    @Transactional
    public void save(Manufacturer manufacturer) {
        manufacturer.setId(UUID.randomUUID());
        manufacturerDao.save(manufacturer);
    }

    @Override
    @Transactional
    public List<Manufacturer> list() {
        return manufacturerDao.findAll();
    }

    @Override
    @Transactional
    public void update(Manufacturer manufacturer) {
        manufacturerDao.update(manufacturer.getName(), manufacturer.getId());
    }

    @Override
    @Transactional
    public Manufacturer getById(UUID id) {
        return manufacturerDao.findOne(id);
    }

    @Override
    @Transactional
    public void remove(UUID id) {
        manufacturerDao.delete(id);
    }
}

package manufacturerwebapp.service;

import manufacturerwebapp.model.Manufacturer;

import java.util.List;
import java.util.UUID;

public interface ManufacturerService {
    void save(Manufacturer manufacturer);
    List<Manufacturer> list();
    void update(Manufacturer manufacturer);
    Manufacturer getById(UUID id);
    void remove(UUID id);
}

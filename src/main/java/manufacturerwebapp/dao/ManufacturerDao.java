package manufacturerwebapp.dao;

import manufacturerwebapp.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface ManufacturerDao extends JpaRepository<Manufacturer, UUID> {
    @Modifying
    @Query("update Manufacturer m set m.name = ?1 where m.id = ?2")
    void update(String name, UUID idManufacturer);
}

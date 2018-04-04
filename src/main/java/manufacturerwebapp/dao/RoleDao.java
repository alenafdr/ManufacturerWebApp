package manufacturerwebapp.dao;

import manufacturerwebapp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleDao extends JpaRepository<Role, UUID> {
    public Role getByName(String name);
}

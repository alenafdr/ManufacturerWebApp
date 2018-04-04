package manufacturerwebapp.dao;

import manufacturerwebapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface UserDao extends JpaRepository<User, UUID> {
    User findByEmail(String email);

    @Modifying
    @Query("update User u set u.email = ?1, u.password = ?2, u.firstName = ?3, u.lastName = ?4 where u.id = ?5")
    void update(String email, String password, String firstName, String lastName, UUID id);
}

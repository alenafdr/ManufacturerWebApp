package manufacturerwebapp.service;

import manufacturerwebapp.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<User> list();
    void save(User user);
    void update(User user);
    void remove(UUID id);
    User getById(UUID id);
    User findByEmail(String email);
}

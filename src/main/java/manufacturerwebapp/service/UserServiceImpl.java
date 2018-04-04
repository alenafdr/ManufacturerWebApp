package manufacturerwebapp.service;

import manufacturerwebapp.dao.RoleDao;
import manufacturerwebapp.dao.UserDao;
import manufacturerwebapp.model.Role;
import manufacturerwebapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public void save(User user) {

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getByName("ROLE_USER"));
        user.setRoles(roles);
        user.setId(UUID.randomUUID());
        userDao.save(user);
    }

    @Override
    @Transactional
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    @Transactional
    public List<User> list() {
        return userDao.findAll();
    }

    @Override
    @Transactional
    public void update(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getByName("ROLE_USER"));
        user.setRoles(roles);
        userDao.save(user);
        //userDao.update(user.getEmail(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getId());
    }

    @Override
    @Transactional
    public void remove(UUID id) {
        userDao.delete(id);
    }

    @Override
    @Transactional
    public User getById(UUID id) {
        return userDao.findOne(id);
    }
}

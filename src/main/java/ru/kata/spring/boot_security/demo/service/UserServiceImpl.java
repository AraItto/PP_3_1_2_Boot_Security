package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService, UserService{

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User myUser = userDao.findByUsername(username);
        if (myUser == null) {
            throw new UsernameNotFoundException("Unknown user: " + username);
        }
        return new User(myUser.getId(), myUser.getName()
                , myUser.getSurname(), myUser.getUsername(), myUser.getPassword(), myUser.getAge()
                , myUser.getEmail(), (myUser.getRoles()));
    }

    @Transactional
    @Override
    public void addUser(ru.kata.spring.boot_security.demo.entities.User user) {
        userDao.addUser(user);
    }

    @Override
    public List<User> getListUsers() {
        return userDao.getListUsers();
    }

    @Override
    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    @Transactional
    @Override
    public void deleteUserById(long id) {
        userDao.deleteUserById(id);
    }

}

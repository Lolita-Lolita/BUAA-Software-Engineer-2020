package com.example.user.service;

import com.example.user.entity.UserCrud;
import com.example.user.repository.UserCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserCrudService {

    @Autowired
    private UserCrudRepository userCrudRepository;

    private PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    @Qualifier("user")
    private MessageChannel user;

    public Optional<UserCrud> findById(Integer id) {
        return userCrudRepository.findById(id);
    }

    public List<UserCrud> findByUserNameLike(String name) {
        return userCrudRepository.findByUserNameLike(name);
    }

    public List<UserCrud> findByRoleLike(String role) {
        return userCrudRepository.findByRoleLike(role);
    }

    public void delete(Integer id) {
        MessageHeaders headers = new MessageHeaders(new HashMap<String, Object>(){{
            put("operate", "delete");
        }});
        user.send(MessageBuilder.createMessage(id, headers));
        userCrudRepository.deleteById(id);
    }

    public UserCrud save(UserCrud userCrud) {
        userCrud.setPassWord(encoder.encode(userCrud.getPassWord()));
        userCrudRepository.save(userCrud);
        MessageHeaders headers = new MessageHeaders(new HashMap<String, Object>(){{
            put("operate", "save");
        }});
        user.send(MessageBuilder.createMessage(userCrud, headers));
        return userCrud;
    }
}

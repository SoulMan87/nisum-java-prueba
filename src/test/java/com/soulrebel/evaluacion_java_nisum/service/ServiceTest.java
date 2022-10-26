package com.soulrebel.evaluacion_java_nisum.service;


import com.soulrebel.evaluacion_java_nisum.entity.Phone;
import com.soulrebel.evaluacion_java_nisum.entity.User;
import com.soulrebel.evaluacion_java_nisum.model.UserResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static com.soulrebel.evaluacion_java_nisum.utils.Constants.EMAIL;
import static com.soulrebel.evaluacion_java_nisum.utils.Constants.EMAIL_MESSAGE;
import static com.soulrebel.evaluacion_java_nisum.utils.Constants.MESSAGE;
import static com.soulrebel.evaluacion_java_nisum.utils.Constants.NAME;
import static com.soulrebel.evaluacion_java_nisum.utils.Constants.PASSWORD;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {

    @Autowired
    private UserService service;


    @Test
    public void testSave() {
        var data = new Phone();
        var listPhones = List.of(data);
        var newUser = new User(NAME, EMAIL, PASSWORD, listPhones);
        var userResponse = service.saveUser(newUser);
        Assert.assertNotNull(MESSAGE, newUser);
    }

    @Test
    public void testFindEmail() {
        Assert.assertTrue(EMAIL_MESSAGE, true);
    }

    @Test
    public void testPassWord() {
        var user = new User("teste@gmail.com", "12456");
        var userService = service.findByEmail(user.getEmail());
        Assert.assertNotNull(MESSAGE, userService != null && userService.getEmail().equals(user.getEmail()));
    }

    @Test
    public void checkEmail() {
        var status = User.checkUserEmail(EMAIL);
        Assert.assertNotNull(EMAIL_MESSAGE, status);
    }


}

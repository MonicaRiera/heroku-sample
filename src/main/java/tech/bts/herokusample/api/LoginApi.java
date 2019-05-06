package tech.bts.herokusample.api;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import tech.bts.herokusample.model.User;

import java.util.*;

@RestController
@RequestMapping(path = "/api/login")
public class LoginApi {


    @PostMapping("/register")
    public String register(@RequestBody User user) {

        return "email: " + user.getEmail() + " password: " + user.getPassword();
    }
}

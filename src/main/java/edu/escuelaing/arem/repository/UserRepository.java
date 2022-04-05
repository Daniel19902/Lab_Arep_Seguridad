package edu.escuelaing.arem.repository;


import edu.escuelaing.arem.Encriptar.Encripto;
import edu.escuelaing.arem.model.User;
import java.util.HashMap;


public class UserRepository {

    private final HashMap<String, User> userHashMap = new HashMap<>();

    public UserRepository() {
        Encripto encripto = new Encripto();
        userHashMap.put("Daniel", new User("Daniel", encripto.encriptar("123456")));
        System.out.println(userHashMap.get("Daniel").getPassword());
    }

    public Boolean getUser(String userName){
        if(userHashMap.containsKey(userName)){
            return true;
        }
        return false;
    }
}

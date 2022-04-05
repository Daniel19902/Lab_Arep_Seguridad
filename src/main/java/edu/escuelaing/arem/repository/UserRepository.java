package edu.escuelaing.arem.repository;


import edu.escuelaing.arem.Encriptar.Encripto;
import edu.escuelaing.arem.model.User;
import java.util.HashMap;
import java.util.Objects;


public class UserRepository {

    private final HashMap<String, User> userHashMap = new HashMap<>();
    private final Encripto encriptar = new Encripto();

    public UserRepository() {

        userHashMap.put("Daniel", new User("Daniel", encriptar.encriptar("123456")));
        System.out.println(userHashMap.get("Daniel").getPassword());
    }

    public String getUser(String userName,String password){
        if(userHashMap.containsKey(userName)){
            if(Objects.equals(encriptar.encriptar(password), userHashMap.get(userName).getPassword())){
                userHashMap.get(userName).setToken(encriptar.generateToken(userHashMap.get(userName)));
                return userHashMap.get(userName).getToken();
            }
        }
        return "";
    }

    public Boolean verificarToken(String token){
        System.out.println(userHashMap.get(encriptar.getInfoToken(token)).getToken()+"token user");
        return userHashMap.get(encriptar.getInfoToken(token)).getToken().equals(token);
    }

    public String getInfoLoby(String token){
        return userHashMap.get(encriptar.getInfoToken(token)).getFrace();
    }
}

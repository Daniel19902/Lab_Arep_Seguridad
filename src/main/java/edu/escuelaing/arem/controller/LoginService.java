package edu.escuelaing.arem.controller;
import com.google.gson.Gson;
import edu.escuelaing.arem.connect.Servicios;
import edu.escuelaing.arem.model.User;
import edu.escuelaing.arem.repository.UserRepository;


import java.util.Objects;

import static spark.Spark.*;
public class LoginService
{
    private static Gson gson = new Gson();
    private static UserRepository userRepository = new UserRepository();

    public static void main( String[] args )
    {
        secure("keystrokes/keystore.p12","123456",  "keystrokes/myTrustStoreApi", "123456");
        port(getPort());
        staticFiles.location("public");
        //get("/hello/:user/:password", (req, res) -> gson.toJson(req.params(":user")+req.params(":password")));
        post("/login", (req, res) -> verificar(req.body()));
        get("/loby", (req, res) -> infoLoby(req.headers("Authorization")));
        get("/sumar/:uno/:dos", (req, res) -> sumar(req.headers("Authorization"), req.params(":uno"), req.params(":dos")));
    }

    public static String verificar(String user){
        System.out.println(user);
        User s = gson.fromJson(user, User.class);
        String auth = userRepository.getUser(s.getUserName(), s.getPassword());
        if(!Objects.equals(auth, "")){
            return gson.toJson(auth);
        }
        return gson.toJson("error de autenticacion");
    }

    public static String infoLoby(String user){
        System.out.println(user + "  token");
        return gson.toJson(userRepository.getInfoLoby(user));
    }

    public static String sumar(String token, String uno, String dos){
        System.out.println(token + uno + dos+"sumar dos numeros");
        if(!Objects.equals(token, "null")){
            System.out.println("emtre");
            if(userRepository.verificarToken(token)){
                System.out.println("entre a sumar");
                return gson.toJson(new Servicios().sumarServicio(uno,dos));
            }
        }
        return gson.toJson("No tiene permisos sobre este servicio");
    }

    public static int getPort(){
        if(System.getenv("PORT") != null){
            return Integer.parseInt(System.getenv("PORT"));
        }

        return 45647;

    }
}

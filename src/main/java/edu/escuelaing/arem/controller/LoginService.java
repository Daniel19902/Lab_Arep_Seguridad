package edu.escuelaing.arem.controller;
import com.google.gson.Gson;
import edu.escuelaing.arem.model.User;
import edu.escuelaing.arem.repository.UserRepository;
import org.json.JSONObject;
import spark.Response;

import java.util.Objects;

import static spark.Spark.*;
public class LoginService
{
    private static Gson gson = new Gson();
    private static UserRepository userRepository = new UserRepository();

    public static void main( String[] args )
    {
        secure("keystrokes/keystore.p12","123456",  null, null);
        port(getPort());
        staticFiles.location("public");
        //get("/hello/:user/:password", (req, res) -> gson.toJson(req.params(":user")+req.params(":password")));
        post("/login", (req, res) -> verificar(req.body()));
        get("/loby", (req, res) -> infoLoby(req.headers("Authorization")));
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

    public static int getPort(){
        if(System.getenv("PORT") != null){
            return Integer.parseInt(System.getenv("PORT"));
        }

        return 4567;

    }
}

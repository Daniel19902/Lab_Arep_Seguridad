package edu.escuelaing.arem.connect;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class Servicios {

    private Gson gson = new Gson();

    public String sumarServicio(String uno, String dos){
        System.setProperty("javax.net.ssl.trustStore", "keystrokes/myTrustStore");
        System.setProperty("javax.net.ssl.trustStorePassword", "123456");
        String url = "https://ec2-44-201-210-16.compute-1.amazonaws.com:4568/sumardos/"+uno+"/"+dos;

        try {
            System.out.println(url);
            URL url1 = new URL(url);
            BufferedReader in = new BufferedReader(new InputStreamReader(url1.openStream()));
            String input;
            String outPut = "";

            while ((input = in.readLine()) != null){
                outPut += input;
            }
            System.out.println(outPut + "output");
            return gson.toJson(outPut);

        }catch (Exception e){
            System.out.println(e.toString());
            return "error en el get";
        }

    }


}

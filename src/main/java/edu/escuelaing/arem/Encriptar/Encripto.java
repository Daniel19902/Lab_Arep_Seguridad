package edu.escuelaing.arem.Encriptar;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Encripto {

    private final String keyPrivate = "arep202123456789";

    public Encripto() {
    }

    public String encriptar (String password) {
        String temp = "";
        try {
            Key aesKey = new SecretKeySpec(keyPrivate.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encrypted = cipher.doFinal(password.getBytes());
            temp = Base64.getEncoder().encodeToString(encrypted);
        }catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e){
            e.printStackTrace();
        }
        return temp;
    }


}

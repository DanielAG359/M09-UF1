package iticbcn.xifratge;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;

public class ClauPublica {
    public KeyPair generaParellClausRSA() throws Exception{
        KeyPairGenerator key = KeyPairGenerator.getInstance("RSA");
        key.initialize(2048);
        return key.generateKeyPair();
    }
    public byte[] xifraRSA(String msg, PublicKey clauPublica)throws Exception{
        try {
            Cipher chiper = Cipher.getInstance("RSA");
            chiper.init(Cipher.ENCRYPT_MODE, clauPublica);
            return chiper.doFinal(msg.getBytes("UTF-8"));
        }catch(Exception e){
            System.out.print("[ERROR] -- Nose miratelo.");
        }
        return null;
    }
    public String desxifraRSA(byte[] msgXifrat, PrivateKey ClauPrivada){
        try {
            Cipher chiper = Cipher.getInstance("RSA");
            chiper.init(Cipher.DECRYPT_MODE, ClauPrivada);
            return new String(chiper.doFinal(msgXifrat), "UTF-8");
        }catch(Exception e){
            System.out.print("[ERROR] -- Nose miratelo.");
        }
        return null;
    }
}

package iticbcn.xifratge;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;

public class XifradorAES implements Xifrador{

    public final String ALGORISME_XIFRAT = "AES";
    public final String ALGORISME_HASH = "SHA-256";
    public final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private final int MIDA_IV = 16;
    private byte[] iv = new byte[MIDA_IV];
    // private final String CLAU = "";
    public byte[] xifraAES(String msg, String clau) throws Exception {
        new SecureRandom().nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        
        // Genera la clau
        SecretKeySpec secretKey = new SecretKeySpec(getSHA(clau), ALGORISME_XIFRAT);
        
        // Xifra el missatge
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
        byte[] msgXifrat = cipher.doFinal(msg.getBytes());

        // Combina IV i missatge xifrat
        byte[] result = new byte[MIDA_IV + msgXifrat.length];
        System.arraycopy(iv, 0, result, 0, MIDA_IV);
        System.arraycopy(msgXifrat, 0, result, MIDA_IV, msgXifrat.length);

        return result;
    }

    public String desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception {
        // Extreu IV
        byte[] iv = new byte[MIDA_IV];
        System.arraycopy(bIvIMsgXifrat, 0, iv, 0, MIDA_IV);

        // Extreu la part xifrada
        byte[] msgXifrat = new byte[bIvIMsgXifrat.length - MIDA_IV];
        System.arraycopy(bIvIMsgXifrat, MIDA_IV, msgXifrat, 0, msgXifrat.length);

        // Genera la clau
        SecretKeySpec secretKey = new SecretKeySpec(getSHA(clau), ALGORISME_XIFRAT);

        // Desxifra el missatge
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));
        byte[] desxifrat = cipher.doFinal(msgXifrat);

        return new String(desxifrat);
    }

    private byte[] getSHA(String clau) throws Exception {
        MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
        return digest.digest(clau.getBytes("UTF-8"));
    }

}

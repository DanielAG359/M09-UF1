package iticbcn.xifratge;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class XifradorAES implements Xifrador{

    public final String ALGORISME_XIFRAT = "AES";
    public final String ALGORISME_HASH = "SHA-256";
    public final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private final int MIDA_IV = 16;
    private byte[] iv = new byte[MIDA_IV];
    IvParameterSpec ivSpec = new IvParameterSpec(iv);
    
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(clau.getBytes(), ALGORISME_XIFRAT);
            Cipher cipher = Cipher.getInstance(FORMAT_AES);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
            byte[] msgXifrat = cipher.doFinal(msg.getBytes());
            return new TextXifrat(msgXifrat);
        } catch (Exception e) {
            throw new ClauNoSuportada("Error al cifrar con AES: " + e.getMessage());
        }
    }

    @Override

    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(clau.getBytes(), ALGORISME_XIFRAT);

            Cipher cipher = Cipher.getInstance(FORMAT_AES);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));
            byte[] desxifrat = cipher.doFinal(xifrat.getBytes());

            return new String(desxifrat);
        } catch (Exception e) {
            throw new ClauNoSuportada("Error al descifrar con AES: " + e.getMessage());
        }

    }

}

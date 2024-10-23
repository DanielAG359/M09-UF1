import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;

public class AES {

    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "LaClauSecretaQueVulguis";

    public static byte[] xifraAES(String msg, String clau) throws Exception {
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

    public static String desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception {
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

    private static byte[] getSHA(String clau) throws Exception {
        MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
        return digest.digest(clau.getBytes("UTF-8"));
    }

    public static void main(String[] args) {
        String msgs[] = {"Lorem ipsum dicet",
        "Hola Andrés cómo está tu cuñado",
        "Àgora ïlla Ôtto"};
        for (int i = 0; i < msgs.length; i++) {
            String msg = msgs[i];
            byte[] bXifrats = null;
            String desxifrat = "";
            try {
                bXifrats = xifraAES(msg, CLAU);
                desxifrat = desxifraAES (bXifrats, CLAU);
            } catch (Exception e) {
                System.err.println("Error de xifrat: "
                + e.getLocalizedMessage ());
            }
            System.out.println("--------------------" );
            System.out.println("Msg: " + msg);
            System.out.println("Enc: " + new String(bXifrats));
            System.out.println("DEC: " + desxifrat);
        }
    }
}

import java.util.HexFormat;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.ArrayList;
import java.util.HexFormat;

public class Hashes {
    public char[] forcaBruta = {'a','b','c','d','e','f','A','B','C','D','E','F','1','2','3','4','5','6','7','8','9','0','!'};
    public int npass = 0; 
    public List<String> combinations;
    public String getSHA512AmbSalt(String pw, String salt){
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(pw.getBytes(StandardCharsets.UTF_8));
            HexFormat hex = HexFormat.of();
            generatedPassword = hex.formatHex(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    public String getPBKDF2AmbSalt(String pw, String salt){
        int iterations = 10000;
        int keyLength = 256;
        byte[] saltBytes = salt.getBytes(StandardCharsets.UTF_8);
        try {
            PBEKeySpec spec = new PBEKeySpec(pw.toCharArray(), saltBytes, iterations, keyLength);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            HexFormat hex = HexFormat.of();
            return hex.formatHex(hash);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String forcaBruta(String alg, String hash, String salt) {
        for (String pwd : combinations) {
            String candidateHash = null;
            if (alg.equals("SHA-512")) {
                candidateHash = getSHA512AmbSalt(pwd, salt);
            } else if (alg.equals("PBKDF2")) {
                candidateHash = getPBKDF2AmbSalt(pwd, salt);
            }

            if (candidateHash != null && candidateHash.equals(hash)) {
                return pwd;
            }else{
                npass ++;
            }
        }
        return null;
    }

    private void generateCombinations() {
        combinations = new ArrayList<>();
        for (char c1 : forcaBruta) {
            combinations.add(String.valueOf(c1));
            for (char c2 : forcaBruta) {
                combinations.add("" + c1 + c2);
                for (char c3 : forcaBruta) {
                    combinations.add("" + c1 + c2 + c3);
                    for (char c4 : forcaBruta) {
                        combinations.add("" + c1 + c2 + c3 + c4);
                        for (char c5 : forcaBruta) {
                            combinations.add("" + c1 + c2 + c3 + c4 + c5);
                            for (char c6 : forcaBruta) {
                                combinations.add("" + c1 + c2 + c3 + c4 + c5 + c6);
                            }
                        }
                    }
                }
            }
        }
    }

    public String getInterval(long t1, long t2) {
        long duration = t2 - t1;
        return ("" + duration);
    }
    public Hashes(){
        generateCombinations();
    }
}

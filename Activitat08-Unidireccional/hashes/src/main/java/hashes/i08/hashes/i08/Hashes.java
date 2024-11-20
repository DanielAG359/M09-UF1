package hashes.i08;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.util.HexFormat;

public class Hashes {
    public char[] forcaBruta = {'a','b','c','d','e','f','A','B','C','D','E','F','1','2','3','4','5','6','7','8','9','0','!'};
    public int npass = 0; 
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
        String passwrd;
        for (char c1 : forcaBruta) {
            passwrd = generateCombinations(alg, hash, salt, String.valueOf(c1));
            if (passwrd != null){return passwrd;}
            for (char c2 : forcaBruta) {
                passwrd = generateCombinations(alg, hash, salt, ("" + c1 + c2));
                if (passwrd != null){return passwrd;}
                for (char c3 : forcaBruta) {
                    passwrd = generateCombinations(alg, hash, salt, ("" + c1 + c2 + c3));
                    if (passwrd != null){return passwrd;}
                    for (char c4 : forcaBruta) {
                        passwrd = generateCombinations(alg, hash, salt, ("" + c1 + c2 + c3 + c4));
                        if (passwrd != null){return passwrd;}
                        for (char c5 : forcaBruta) {
                            passwrd = generateCombinations(alg, hash, salt, ("" + c1 + c2 + c3 + c4 + c5));
                            if (passwrd != null){return passwrd;}
                            for (char c6 : forcaBruta) {
                                passwrd = generateCombinations(alg, hash, salt, ("" + c1 + c2 + c3 + c4 + c5 + c6));
                                if (passwrd != null){return passwrd;}
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    private String generateCombinations(String alg, String hash, String salt, String pwd) {
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
        return null;
    }

    public String getInterval(long t1, long t2) {
        long millis = t2 - t1;
        long seg = millis/1000;
        long min = seg/60;
        long hor = min/60;
        long day = hor/24;

        return ("dies " + (day) + " | hores " + (hor %= 24) + " | minuts " + (min %= 60) + " | segons " + (seg %= 60) + " | millisegons " + (millis %= 1000));
    }
}
    
//=====================================[SEGONA FORMA MES LENTA]========================================================================
// public List<String> combinations;
// import java.util.List;
// import java.util.ArrayList;
// public class Hashes {
//     public char[] forcaBruta = {'a','b','c','d','e','f','A','B','C','D','E','F','1','2','3','4','5','6','7','8','9','0','!'};
//     public int npass = 0; 
//     public List<String> combinations;
//     public String getSHA512AmbSalt(String pw, String salt){
//         String generatedPassword = null;
//         try {
//             MessageDigest md = MessageDigest.getInstance("SHA-512");
//             md.update(salt.getBytes(StandardCharsets.UTF_8));
//             byte[] bytes = md.digest(pw.getBytes(StandardCharsets.UTF_8));
//             HexFormat hex = HexFormat.of();
//             generatedPassword = hex.formatHex(bytes);
//         } catch (NoSuchAlgorithmException e) {
//             e.printStackTrace();
//         }
//         return generatedPassword;
//     }

//     public String getPBKDF2AmbSalt(String pw, String salt){
//         int iterations = 10000;
//         int keyLength = 256;
//         byte[] saltBytes = salt.getBytes(StandardCharsets.UTF_8);
//         try {
//             PBEKeySpec spec = new PBEKeySpec(pw.toCharArray(), saltBytes, iterations, keyLength);
//             SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
//             byte[] hash = factory.generateSecret(spec).getEncoded();
//             HexFormat hex = HexFormat.of();
//             return hex.formatHex(hash);
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//         return null;
//     }

//     public String forcaBruta(String alg, String hash, String salt) {
//         for (String pwd : combinations) {
//             String candidateHash = null;
//             if (alg.equals("SHA-512")) {
//                 candidateHash = getSHA512AmbSalt(pwd, salt);
//             } else if (alg.equals("PBKDF2")) {
//                 candidateHash = getPBKDF2AmbSalt(pwd, salt);
//             }

//             if (candidateHash != null && candidateHash.equals(hash)) {
//                 return pwd;
//             }else{
//                 npass ++;
//             }
//         }
//         return null;
//     }

//     private void generateCombinations() {
//         combinations = new ArrayList<>();
//         for (char c1 : forcaBruta) {
//             combinations.add(String.valueOf(c1));
//             for (char c2 : forcaBruta) {
//                 combinations.add("" + c1 + c2);
//                 for (char c3 : forcaBruta) {
//                     combinations.add("" + c1 + c2 + c3);
//                     for (char c4 : forcaBruta) {
//                         combinations.add("" + c1 + c2 + c3 + c4);
//                         for (char c5 : forcaBruta) {
//                             combinations.add("" + c1 + c2 + c3 + c4 + c5);
//                             for (char c6 : forcaBruta) {
//                                 combinations.add("" + c1 + c2 + c3 + c4 + c5 + c6);
//                             }
//                         }
//                     }
//                 }
//             }
//         }
//     }

//     public String getInterval(long t1, long t2) {
//         long millis = t2 - t1;
//         long seg = millis/1000;
//         long min = seg/60;
//         long hor = min/60;
//         long day = hor/24;

//         return ("dies " + (day) + " | hores " + (hor %= 24) + " | minuts " + (min %= 60) + " | segons " + (seg %= 60) + " | millisegons " + (millis %= 1000));
//     }
//     public Hashes(){
//         generateCombinations();
//     }
// }
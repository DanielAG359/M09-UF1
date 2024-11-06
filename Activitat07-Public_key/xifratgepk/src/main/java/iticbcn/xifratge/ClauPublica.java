public class ClauPublica {
    private PublicKey publicKey;
    private PrivateKey privateKey;
    public KeyPair generaParellClausRSA() throws Exception{
        KeyPairGenerator key = KeyPairGenerator.getInstance("RSA");
        key.initialize(2048);
        publicKey = keyPair.getPublic();
        privateKey = keyPair.getPrivate();
    }
    public byte[] xifraRSA(String msg, PublicKey clauPublica)throws Exception{
        try {
            Chiper chiper = Chiper.getInstance(msg);
            cipher.init(Cipher.ENCRYPT_MODE, clauPublica);
            return cipher.doFinal(inputBytes);
        }catch(Exception e){
            System.out.print("[ERROR] -- Nose miratelo.");
        }
        return null;
    }
    public String desxifraRSA(byte[] msgXifrat, PrivateKey ClauPrivada){
        try {
            Chiper chiper = Chiper.getInstance(msg);
            cipher.init(Cipher.DECRYPT_MODE, ClauPrivada);
            return cipher.doFinal(inputBytes);
        }catch(Exception e){
            System.out.print("[ERROR] -- Nose miratelo.");
        }
        return null;
    }
}

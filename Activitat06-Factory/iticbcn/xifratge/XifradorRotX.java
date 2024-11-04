package iticbcn.xifratge;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
public class XifradorRotX implements Xifrador {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private final char[] abecedario = {'a','á','à','ä','b','c','ç','d','e','é','è','ë','f','g','h','i','í','ì','ï','j','k','l','m','n','ñ','o','ó','ò','ö','p','q','r','s','t','u','ú','ù','ü','v','w','x','y','z'};
    private final char[] abecedarioMayusculas = {'A','Á','À','Ä','B','C','Ç','D','E','É','È','Ë','F','G','H','I','Í','Ì','Ï','J','K','L','M','N','Ñ','O','Ó','Ò','Ö','P','Q','R','S','T','U','Ú','Ù','Ü','V','W','X','Y','Z'};
    public String readLine() {
        try {
            String line = reader.readLine();
            if (line == null) {
                throw new RuntimeException("S'ha cridat massa cops Entrada.readLine()");
            }
            return line;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String xifraRotX(String cadena, int num) {
        String stringFinal = "";
        for (int i = 0; i < cadena.length(); i++){
            char lletra = cadena.charAt(i);
            if (Character.isLowerCase(lletra)){
                stringFinal = stringFinal + funcionAbecedario(cadena, num, lletra, true, abecedario);
            }else if(Character.isUpperCase(lletra)){
                stringFinal = stringFinal + funcionAbecedario(cadena, num, lletra, true, abecedarioMayusculas);
            }else{
                stringFinal = stringFinal + lletra;
            }
        }
        return stringFinal;
    }
    public char funcionAbecedario (String cadena, int num, char lletra, boolean binario, char[] abecedarioComplementario){
        for (int j = 0; j < abecedarioComplementario.length; j ++){
            if (abecedarioComplementario[j] == lletra){
                if (binario){
                    while ((j + num) > abecedarioComplementario.length){
                        j = j - abecedarioComplementario.length;
                    }
                    return abecedarioComplementario[(j + num)];
                }else{
                    while ((j - num) < 0){
                        j = j + abecedarioComplementario.length;
                    }
                    return abecedarioComplementario[(j - num)];
                }
            }
        }
        return lletra;
    }
    public String desxifraRotX(String cadena, int num){
        String stringFinal = "";
        for (int i = 0; i < cadena.length(); i++){
            char lletra = cadena.charAt(i);
            if (Character.isLowerCase(lletra)){
                stringFinal = stringFinal + funcionAbecedario(cadena, num, lletra, false ,abecedario);
            }else if(Character.isUpperCase(lletra)){
                stringFinal = stringFinal + funcionAbecedario(cadena, num, lletra, false , abecedarioMayusculas);
            }else{
                stringFinal = stringFinal + lletra;
            }
        }
        return stringFinal;
    }
}
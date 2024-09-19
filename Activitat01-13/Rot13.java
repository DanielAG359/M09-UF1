import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
public class Rot13 {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private final static char[] abecedario = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','ñ','o','p','q','r','s','t','u','v','w','x','y','z'};
    private final static char[] abecedarioMayusculas = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','Ñ','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    public static String readLine() {
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
    public static String xifraRot13(String cadena) {
        String stringFinal = "";
        for (int i = 0; i < cadena.length(); i++){
            char lletra = cadena.charAt(i);
            if (Character.isLowerCase(lletra)){
                for (int j = 0; j < abecedario.length; j ++){
                    if (abecedario[j] == lletra){
                        if ((j + 13) > 27){
                            int t = (j + 13) - 27;
                            stringFinal = stringFinal + abecedario[t];
                        }else{
                            stringFinal = stringFinal + abecedario[(j + 13)];
                        }
                    }
                }
            }else if(Character.isUpperCase(lletra)){
                for (int j = 0; j < abecedarioMayusculas.length; j ++){
                    if (abecedarioMayusculas[j] == lletra){
                        if ((j + 13) > 27){
                            int t = (j + 13) - 27;
                            stringFinal = stringFinal + abecedarioMayusculas[t];
                        }else{
                            stringFinal = stringFinal + abecedarioMayusculas[(j + 13)];
                        }
                    }
                }
            }else{
                stringFinal = stringFinal + lletra;
            }
        }
        return stringFinal;
    }
    public static String desxifraRot13(String cadena){
        String stringFinal = "";
        for (int i = 0; i < cadena.length(); i++){
            char lletra = cadena.charAt(i);
            if (Character.isLowerCase(lletra)){
                for (int j = 0; j < abecedario.length; j ++){
                    if (abecedario[j] == lletra){
                        if ((j - 13) < 0){
                            int t = (j - 13) + 27;
                            stringFinal = stringFinal + abecedario[t];
                        }else{
                            stringFinal = stringFinal + abecedario[(j - 13)];
                        }
                    }
                }
            }else if(Character.isUpperCase(lletra)){
                for (int j = 0; j < abecedarioMayusculas.length; j ++){
                    if (abecedarioMayusculas[j] == lletra){
                        if ((j - 13) < 0){
                            int t = (j - 13) + 27;
                            stringFinal = stringFinal + abecedarioMayusculas[t];
                        }else{
                            stringFinal = stringFinal + abecedarioMayusculas[(j - 13)];
                        }
                    }
                }
            }else{
                stringFinal = stringFinal + lletra;
            }
        }
        return stringFinal;
    }
    public static void main(String[]args){
        String line = readLine();
        System.out.println(xifraRot13(line));
        System.out.println(desxifraRot13(xifraRot13(line)));
    }
}
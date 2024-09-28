import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
public class RotX {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private final static char[] abecedario = {'a','á','à','ä','b','c','ç','d','e','é','è','ë','f','g','h','i','í','ì','ï','j','k','l','m','n','ñ','o','ó','ò','ö','p','q','r','s','t','u','ú','ù','ü','v','w','x','y','z'};
    private final static char[] abecedarioMayusculas = {'A','Á','À','Ä','B','C','Ç','D','E','É','È','Ë','F','G','H','I','Í','Ì','Ï','J','K','L','M','N','Ñ','O','Ó','Ò','Ö','P','Q','R','S','T','U','Ú','Ù','Ü','V','W','X','Y','Z'};
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
    public static String xifraRotX(String cadena, int num) {
        String stringFinal = "";
        for (int i = 0; i < cadena.length(); i++){
            char lletra = cadena.charAt(i);
            if (Character.isLowerCase(lletra)){
                for (int j = 0; j < abecedario.length; j ++){
                    if (abecedario[j] == lletra){
                        if ((j + num) > abecedario.length){
                            int t = (j + num) - abecedario.length;
                            stringFinal = stringFinal + abecedario[t];
                        }else{
                            stringFinal = stringFinal + abecedario[(j + num)];
                        }
                    }
                }
            }else if(Character.isUpperCase(lletra)){
                for (int j = 0; j < abecedarioMayusculas.length; j ++){
                    if (abecedarioMayusculas[j] == lletra){
                        if ((j + num) > abecedarioMayusculas.length){
                            int t = (j + num) - abecedarioMayusculas.length;
                            stringFinal = stringFinal + abecedarioMayusculas[t];
                        }else{
                            stringFinal = stringFinal + abecedarioMayusculas[(j + num)];
                        }
                    }
                }
            }else{
                stringFinal = stringFinal + lletra;
            }
        }
        return stringFinal;
    }
    public static String desxifraRotX(String cadena, int num){
        String stringFinal = "";
        for (int i = 0; i < cadena.length(); i++){
            char lletra = cadena.charAt(i);
            if (Character.isLowerCase(lletra)){
                for (int j = 0; j < abecedario.length; j ++){
                    if (abecedario[j] == lletra){
                        if ((j - num) < 0){
                            int t = (j - num) + abecedario.length;
                            stringFinal = stringFinal + abecedario[t];
                        }else{
                            stringFinal = stringFinal + abecedario[(j - num)];
                        }
                    }
                }
            }else if(Character.isUpperCase(lletra)){
                for (int j = 0; j < abecedarioMayusculas.length; j ++){
                    if (abecedarioMayusculas[j] == lletra){
                        if ((j - num) < 0){
                            int t = (j - num) + abecedarioMayusculas.length;
                            stringFinal = stringFinal + abecedarioMayusculas[t];
                        }else{
                            stringFinal = stringFinal + abecedarioMayusculas[(j - num)];
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
        System.out.println("Escriu:");
        String line = readLine();
        System.out.println("Rotacio:");
        int num = Integer.parseInt(readLine());
        line = xifraRotX(line, num);
        System.out.println(line);
        System.out.println(desxifraRotX(line, num));
    }
}
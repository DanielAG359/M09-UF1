import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
public class Polialfabetic {
    private final static long llavor = 345678987654567L;
    private final static char[] abecedario = {'a','á','à','ä','b','c','ç','d','e','é','è','ë','f','g','h','i','í','ì','ï','j','k','l','m','n','ñ','o','ó','ò','ö','p','q','r','s','t','u','ú','ù','ü','v','w','x','y','z','A','Á','À','Ä','B','C','Ç','D','E','É','È','Ë','F','G','H','I','Í','Ì','Ï','J','K','L','M','N','Ñ','O','Ó','Ò','Ö','P','Q','R','S','T','U','Ú','Ù','Ü','V','W','X','Y','Z'};
    public static List<Character> alfabetList;
    public static List<Character> randomAlfabetList;
    public static String xifraPoliAlfa(String cadena) {
        return funcionAbecedario(cadena, alfabetList, randomAlfabetList);
    }
    public static String funcionAbecedario (String cadena, List<Character> alfabetInicial, List<Character> alfabetIntercambi){
        String stringFinal = "";
        for (int i = 0; i < cadena.length(); i++){
            if (Character.isLetter(cadena.charAt(i))){
                for (int j = 0; j < alfabetInicial.size(); j++){
                    if (cadena.charAt(i)==alfabetInicial.get(j)){
                        stringFinal = stringFinal + alfabetIntercambi.get(j);
                    }
                }
            }else{
                stringFinal = stringFinal + cadena.charAt(i);
            }
        }
        return stringFinal;
    }
    public static String desxifraPoliAlfa(String cadena) {
        return funcionAbecedario(cadena, randomAlfabetList, alfabetList);
    }
    public static void initRandom(long cadena){
        alfabetList = new ArrayList<>();
        randomAlfabetList = new ArrayList<>();
        for(int i = 0; i < abecedario.length; i++){
            alfabetList.add(abecedario[i]);
            randomAlfabetList.add(abecedario[i]);
        }
        Collections.shuffle(randomAlfabetList, new Random(llavor));
    }
    public static void main( String[] args ){
        String msgs[] = { "Test 01 àrbritre, coixí, Perímetre" ,
        "Test 02 Taüll, DÍA, año" ,
        "Test 03 Peça, Òrrius, Bòvila" };

        String msgsXifrats [] = new String [ msgs. length ];

        System . out . println ( "Xifratge: \n --------" );

        for ( int i = 0; i < msgs. length ; i ++) {
        initRandom( llavor );

        msgsXifrats [ i ] = xifraPoliAlfa ( msgs[ i ]);

        System . out . printf ( "%-34s -> %s%n", msgs[ i ], msgsXifrats [ i ]);

        }
        System . out . println ( "Desxifratge: \n -----------" );

        for ( int i = 0; i < msgs. length ; i ++) {
        initRandom( llavor );

        String msg = desxifraPoliAlfa ( msgsXifrats [ i ]);

        System . out . printf ( "%-34s -> %s%n", msgsXifrats [ i ], msg);
        
        }
    }
}

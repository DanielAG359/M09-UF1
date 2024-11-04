package iticbcn.xifratge;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
public class XifradorPolialfabetic implements Xifrador {
    private final long llavor = 345678987654567L;
    private final char[] abecedario = {'a','á','à','ä','b','c','ç','d','e','é','è','ë','f','g','h','i','í','ì','ï','j','k','l','m','n','ñ','o','ó','ò','ö','p','q','r','s','t','u','ú','ù','ü','v','w','x','y','z','A','Á','À','Ä','B','C','Ç','D','E','É','È','Ë','F','G','H','I','Í','Ì','Ï','J','K','L','M','N','Ñ','O','Ó','Ò','Ö','P','Q','R','S','T','U','Ú','Ù','Ü','V','W','X','Y','Z'};
    public List<Character> alfabetList;
    public List<Character> randomAlfabetList;
    public String xifraPoliAlfa(String cadena) {
        return funcionAbecedario(cadena, alfabetList, randomAlfabetList);
    }
    public String funcionAbecedario (String cadena, List<Character> alfabetInicial, List<Character> alfabetIntercambi){
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
    public String desxifraPoliAlfa(String cadena) {
        return funcionAbecedario(cadena, randomAlfabetList, alfabetList);
    }
    public void initRandom(long cadena){
        alfabetList = new ArrayList<>();
        randomAlfabetList = new ArrayList<>();
        for(int i = 0; i < abecedario.length; i++){
            alfabetList.add(abecedario[i]);
            randomAlfabetList.add(abecedario[i]);
        }
        Collections.shuffle(randomAlfabetList, new Random(llavor));
    }
}

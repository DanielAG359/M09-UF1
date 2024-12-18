package iticbcn.xifratge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class XifradorMonoalfabetic implements Xifrador {
    private final char[] abecedario = {'a','á','à','ä','b','c','ç','d','e','é','è','ë','f','g','h','i','í','ì','ï','j','k','l','m','n','ñ','o','ó','ò','ö','p','q','r','s','t','u','ú','ù','ü','v','w','x','y','z','A','Á','À','Ä','B','C','Ç','D','E','É','È','Ë','F','G','H','I','Í','Ì','Ï','J','K','L','M','N','Ñ','O','Ó','Ò','Ö','P','Q','R','S','T','U','Ú','Ù','Ü','V','W','X','Y','Z'};
    public List<Character> alfabetList;
    public List<Character> randomAlfabetList;
    public void permutaAlfabet(){
        alfabetList = new ArrayList<>();
        randomAlfabetList = new ArrayList<>();
        for(int i = 0; i < abecedario.length; i++){
            alfabetList.add(abecedario[i]);
            randomAlfabetList.add(abecedario[i]);
        }
        Collections.shuffle(randomAlfabetList);
    }
    public TextXifrat xifra(String cadena, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
        String stringFinal = "";
        for (int i = 0; i < cadena.length(); i++){
            if (Character.isLetter(cadena.charAt(i))){
                for (int j = 0; j < alfabetList.size(); j++){
                    if (cadena.charAt(i)==alfabetList.get(j)){
                        stringFinal = stringFinal + randomAlfabetList.get(j);
                    }
                }
            }else{
                stringFinal = stringFinal + cadena.charAt(i);
            }
        }
        return new TextXifrat(stringFinal.toString().getBytes());
    }
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }

        StringBuilder result = new StringBuilder();
        for (char c : new String(xifrat.getBytes()).toCharArray()) {
            if (Character.isAlphabetic(c)) {
                for (int j = 0; j < alfabetList.size(); j++){
                    if (c==alfabetList.get(j)){
                        result.append(randomAlfabetList.get(j));
                    }
                }
            } else {
                result.append(c); // Mantener caracteres no alfabéticos
            }
        }
        return result.toString();
    }
}

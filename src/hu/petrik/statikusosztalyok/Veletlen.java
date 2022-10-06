package hu.petrik.statikusosztalyok;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.Normalizer;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public final class Veletlen {
    private Veletlen() {
    }

    private static final Random rnd = new Random();
    private static final List<String> vezNevek = feltolt("files/veznev.txt");
    private static final List<String> ferfiKerNevek = feltolt("files/ferfikernev.txt");
    private static final List<String> noiKerNevek = feltolt("files/noikernev.txt");

    private static List<String> feltolt(String fajlnev) {
        List<String> lista = new ArrayList<>();
        try {
            Scanner file = new Scanner(new File(fajlnev));
            while (file.hasNext()) {
                String sor = file.nextLine();
                lista.add(sor);
            }
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public static int velEgesz(int min, int max) {
        return rnd.nextInt(max - min + 1) + min;
    }

    public static char velKarakter(char min, char max) {
        return (char) velEgesz(min, max);
    }

    public static String velVezetekNev() {
        return vezNevek.get(rnd.nextInt(vezNevek.size()));
    }

    /**
     * Véletlen magyar keresztnév generálása
     * @param nem A generált keresztnév neme. Férfi esetén true, Nő esetén false.
     * @return A generált keresztnév
     */
    public static String velKeresztNev(boolean nem) {
        String keresztNev;
        if (nem) {
            keresztNev = velFerfiKeresztNev();
        } else {
            keresztNev = velNoiKeresztNev();
        }
        return keresztNev;
    }

    private static String velFerfiKeresztNev() {
        return ferfiKerNevek.get(rnd.nextInt(ferfiKerNevek.size()));
    }

    private static String velNoiKeresztNev() {
        return noiKerNevek.get(rnd.nextInt(noiKerNevek.size()));
    }

    /**
     * Véletlen magyar név generálása
     * @param nem A generált név neme. Férfi esetén true, Nő esetén false.
     * @return A generált név
     */
    public static String velTeljesNev(boolean nem) {
        return velVezetekNev() + " " + velKeresztNev(nem);
    }

    public static String velDatum(int ev1, int ev2){
        Random ev = new Random();
        Random honap = new Random();
        Random nap = new Random();
        int generatedEv;
        if(ev1 > ev2){
            generatedEv = ev.nextInt(ev1) + ev2;
        }
        else{
            generatedEv = ev.nextInt(ev2) + ev1;
        }

        int generatedHonap = honap.nextInt(12) + 1;

        if(generatedHonap == 1 || generatedHonap == 3 || generatedHonap == 5 || generatedHonap == 7 || generatedHonap == 8 || generatedHonap == 10 || generatedHonap == 12){
            int generatedNap = nap.nextInt(31) + 1;
            return generatedEv + ":" + generatedHonap + ":" + generatedNap;
        }
        else if(generatedHonap == 2){
            int generatedNap = nap.nextInt(28) + 1;
            return generatedEv + ":" + generatedHonap + ":" + generatedNap;
        }
        else{
            int generatedNap = nap.nextInt(30) + 1;
            return generatedEv + ":" + generatedHonap + ":" + generatedNap;
        }

    }

    public static String velEmail(String nev){
        String nevWithoutAccents = nev.replaceAll("[^\\p{ASCII}]", ""); //nem működik de nincs időm megcsinálni
        String nevWithoutWhitespace = nevWithoutAccents.replaceAll("\\s+","");

        Random rnumber = new Random();
        int generatedNumber = rnumber.nextInt(100)+1;

        return nevWithoutWhitespace + generatedNumber + "@gmail.com";
    }
}
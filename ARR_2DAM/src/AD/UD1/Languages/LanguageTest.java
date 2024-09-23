package UD1.Languages;

import java.util.Locale;
import java.util.logging.Logger;
import java.util.ResourceBundle;
public class LanguageTest {
    private static final Logger log = Logger.getLogger(LanguageTest.class.getName());
    public static void main(String[] args) {
        Locale en = new Locale("en");
        Locale es = new Locale("es");
        Locale fr = new Locale("fr");
        Locale ca = new Locale("ca");
        Locale ru = new Locale("ru");
        ResourceBundle bundleEn = ResourceBundle.getBundle("Languages.messages", en);
        ResourceBundle bundleEs = ResourceBundle.getBundle("Languages.messages", es);
        ResourceBundle bundleFr = ResourceBundle.getBundle("Languages.messages", fr);
        ResourceBundle bundleCa = ResourceBundle.getBundle("Languages.messages", ca);
        ResourceBundle bundleRu = ResourceBundle.getBundle("Languages.messages", ru);


        System.out.println("Greetings in English: " + bundleEn.getString("greeting"));
        System.out.println("Greetings in Spanish: " + bundleEs.getString("greeting"));
        System.out.println("Greetings in French: " + bundleFr.getString("greeting"));
        System.out.println("Greetings in Catalan: " + bundleCa.getString("greeting"));
        System.out.println("Greetings in Russian: " + bundleRu.getString("greeting"));
    }
}

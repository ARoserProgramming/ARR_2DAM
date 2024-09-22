import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;

public class SerializeModules {
    private static Logger log = Logger.getRootLogger();

    public static void main(String[] args) {
        String XML_FILE_ROUTE = "modules.xml";
        String CSV_FILE_ROUTE = "modules.csv";
        String[] names = {"Acceso a datos", "Sistemas de gestion empresarial", "Empresa e iniciativa emprendedora"};
        int[] hours = {280, 280, 120};
        double[] averageGrades = {5.6, 7.3, 6.9};
        String SERIALIZED_FILE_ROUTE = "modules.dat";
        serializeModules(names, hours, averageGrades, SERIALIZED_FILE_ROUTE);
        ArrayList<Module> desarializedModules = deserializeModules(SERIALIZED_FILE_ROUTE);
        System.out.println(desarializedModules);
        generateCSV(names,hours,averageGrades,CSV_FILE_ROUTE);
        readCSV(CSV_FILE_ROUTE);
        generateXML(names,hours,averageGrades,XML_FILE_ROUTE);
    }

    public static void serializeModules(String[] names, int[] hours, double[] averageGrades, String serializedFile) {
        try (ObjectOutput oo = new ObjectOutputStream(new FileOutputStream(serializedFile))) {
            for (int i = 0; i < names.length; i++) {
                Module module = new Module(names[i], hours[i], averageGrades[i]);
                oo.writeObject(module);

            }
        } catch (IOException e) {
            log.error(e);
        }
    }

    public static ArrayList<Module> deserializeModules(String serializedFile) {
        ArrayList<Module> moduleList = new ArrayList<>();
        Boolean finishedLooping = false;
        try (ObjectInput oi = new ObjectInputStream(new FileInputStream(serializedFile))) {
            while (!finishedLooping) {
                try {
                    Module module = (Module) oi.readObject();
                    moduleList.add(module);
                } catch (EOFException e) {
                    finishedLooping = true;
                } catch (ClassNotFoundException e) {
                    log.error(e);
                }
            }
            } catch(FileNotFoundException e){
                log.error(e);
            } catch(IOException e){
                log.error(e);
            }
            if (moduleList.isEmpty())
                log.warn("Error deserializing, an empty module will be returned");
            return moduleList;
        }
    public static void generateCSV(String[] names, int[] hours, double[] averageGrades, String csvFile) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(csvFile))) {
            pw.println("Nombre, Horas, NotaMedia");
            for (int i = 0; i < names.length; i++) {
                pw.println(names[i] + ";" + hours[i] + ";" + averageGrades[i]);
            }
        } catch (IOException e) {
            log.error("Error al generar el archivo CSV", e);
        }
    }
    public static void readCSV(String csvFile){
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null ){
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            log.error(e);
        } catch (IOException e) {
            log.error(e);
        } ;
    }
    public static void generateXML(String[] names, int[] hours, double[] averageGrades, String xmlFile) {
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

            Document document = documentBuilder.newDocument();

            Element root = document.createElement("modules");
            document.appendChild(root);

            for (int i = 0; i < names.length; i++) {
                Element moduleElement = document.createElement("module");

                Element nameElement = document.createElement("name");
                nameElement.appendChild(document.createTextNode(names[i]));
                moduleElement.appendChild(nameElement);

                Element hoursElement = document.createElement("hours");
                hoursElement.appendChild(document.createTextNode(String.valueOf(hours[i])));
                moduleElement.appendChild(hoursElement);

                Element averageGradeElement = document.createElement("averageGrade");
                averageGradeElement.appendChild(document.createTextNode(String.valueOf(averageGrades[i])));
                moduleElement.appendChild(averageGradeElement);

                root.appendChild(moduleElement);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");


            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(xmlFile));

            transformer.transform(domSource, streamResult);

            System.out.println("Archivo XML generado correctamente en: " + xmlFile);

        } catch (ParserConfigurationException | TransformerException e) {
            log.error("Error al generar el archivo XML", e);
        }
    }
}
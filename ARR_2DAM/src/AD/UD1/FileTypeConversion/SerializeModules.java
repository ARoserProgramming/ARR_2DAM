package UD1.FileTypeConversion;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;

public class SerializeModules {
    private static final Logger log = Logger.getRootLogger();
    private static final String XML_FILE_ROUTE = "resources/modules.xml";
    private static final String CSV_FILE_ROUTE = "resources/modules.csv";
    private static final String SERIALIZED_FILE_ROUTE = "resources/modules.dat";

    public static void main(String[] args) {
        File serializedFile = new File(SERIALIZED_FILE_ROUTE);
        File csvFile = new File(CSV_FILE_ROUTE);
        File xmlFile = new File(XML_FILE_ROUTE);
        String[] names = {"Acceso a datos", "Sistemas de gestion empresarial", "Empresa e iniciativa emprendedora"};
        int[] hours = {280, 280, 120};
        double[] averageGrades = {5.6, 7.3, 6.9};

        serializeModules(names, hours, averageGrades, serializedFile);
        ArrayList<MyModule> desarializedModules = deserializeModules(serializedFile);
        System.out.println(desarializedModules);
        generateCSV(names,hours,averageGrades,csvFile);
        readCSV(csvFile);
        generateXML(names,hours,averageGrades,xmlFile);
        readXML(xmlFile);
    }

    public static void serializeModules(String[] names, int[] hours, double[] averageGrades, File serializedFile) {
        try (ObjectOutput oo = new ObjectOutputStream(new FileOutputStream(serializedFile))) {
            for (int i = 0; i < names.length; i++) {
                MyModule module = new MyModule(names[i], hours[i], averageGrades[i]);
                oo.writeObject(module);

            }
        } catch (IOException e) {
            log.error(e);
        }
    }

    public static ArrayList<MyModule> deserializeModules(File serializedFile) {
        ArrayList<MyModule> moduleList = new ArrayList<>();
        boolean finishedLooping = false;
        try (ObjectInput oi = new ObjectInputStream(new FileInputStream(serializedFile))) {
            while (!finishedLooping) {
                try {
                    MyModule module = (MyModule) oi.readObject();
                    moduleList.add(module);
                } catch (EOFException e) {
                    finishedLooping = true;
                } catch (ClassNotFoundException e) {
                    log.error(e);
                }
            }
            } catch(FileNotFoundException e){
                log.error("Error file does not exist " + e);
            } catch(IOException e){
                log.error("Error in io " + e);
            }
            if (moduleList.isEmpty())
                log.warn("Error deserializing, an empty module will be returned");
            return moduleList;
        }
    public static void generateCSV(String[] names, int[] hours, double[] averageGrades, File csvFile) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(csvFile))) {
            pw.println("Nombre, Horas, NotaMedia");
            for (int i = 0; i < names.length; i++) {
                pw.println(names[i] + ";" + hours[i] + ";" + averageGrades[i]);
            }
        } catch (IOException e) {
            log.error("Error al generar el archivo CSV", e);
        }
    }
    public static void readCSV(File csvFile){
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null ){
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            log.error("Error, file does not exist: " + e);
        } catch (IOException e) {
            log.error("Error in io " + e);
        }
    }
    public static void generateXML(String[] names, int[] hours, double[] averageGrades, File xmlFile) {
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


            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(xmlFile);

            transformer.transform(domSource, streamResult);

            System.out.println("Archivo XML generado correctamente en: " + xmlFile);

        } catch (ParserConfigurationException | TransformerException e) {
            log.error("Error al generar el archivo XML", e);
        }
    }
    public static void readXML(File xmlFile) {
        try {

            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);

            document.getDocumentElement().normalize();
            NodeList moduleList = document.getElementsByTagName("module");

            for (int i = 0; i < moduleList.getLength(); i++) {
                Element moduleElement = (Element) moduleList.item(i);
                String name = moduleElement.getElementsByTagName("name").item(0).getTextContent();
                String hours = moduleElement.getElementsByTagName("hours").item(0).getTextContent();
                String averageGrade = moduleElement.getElementsByTagName("averageGrade").item(0).getTextContent();
                System.out.println("Module " + name);
                System.out.println("Hours " + hours);
                System.out.println("AverageGrade " + averageGrade);
                System.out.println("----------------");
            }
        } catch (ParserConfigurationException e) {
            log.error("Error while parsing: " + e);
        } catch (IOException e) {
            throw new RuntimeException("Error in io: " + e);
        } catch (SAXException e) {
            throw new RuntimeException("Error in SAX: " +e);
        }
    }
}
package CurrencyConverter;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.List;
import java.util.Vector;

public class CurrencyFileReader {
    String filePath;

    public CurrencyFileReader(String inputFilePath) {
        this.filePath = inputFilePath;
    }

    NodeList readCurrencyFile(){
        try {
            File inputFile = new File(this.filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("Cube");
            return  nList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> readCurrencies() {
        NodeList nList = this.readCurrencyFile();
        List<String> currencies = new Vector<>();

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                    if (!eElement.getAttribute("currency").isEmpty())
                        currencies.add(eElement.getAttribute("currency"));
                }
            }
        return currencies;
    }

    public double readRate(String currency) {
        NodeList nList = this.readCurrencyFile();
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                if (eElement.getAttribute("currency").equals(currency))
                    return Double.parseDouble(eElement.getAttribute("rate"));
            }
        }
        return -1;
    }
}

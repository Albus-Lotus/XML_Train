package training.epam.kravchenko.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import training.epam.kravchenko.entity.FreightCar;
import training.epam.kravchenko.entity.PassengerCar;
import training.epam.kravchenko.entity.Railcar;
import training.epam.kravchenko.tags.RailcarXMLTag;

public class RailcarsDOMParser {
    private List<Railcar> railcars;
    private DocumentBuilder docBuilder;

    public RailcarsDOMParser() {
        railcars = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public List<Railcar> getRailcars() {
        return railcars;
    }

    public void parseListRailcars(String filename) {
        Document doc;
        try {
            doc = docBuilder.parse(filename);
            Element root = doc.getDocumentElement();
            NodeList railcarsList = root.getElementsByTagName(RailcarXMLTag.FREIGHTCAR.getValue());
            for (int i = 0; i < railcarsList.getLength(); i++) {
                Element railcarElement = (Element) railcarsList.item(i);
                Railcar railcar = parseFreightCar(railcarElement);
                railcars.add(railcar);
            }
            railcarsList = root.getElementsByTagName(RailcarXMLTag.PASSENGERCAR.getValue());
            for (int i = 0; i < railcarsList.getLength(); i++) {
                Element railcarElement = (Element) railcarsList.item(i);
                Railcar railcar = parsePassengerCar(railcarElement);
                railcars.add(railcar);
            }
        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }
    }

    private Railcar parseFreightCar(Element freightCarElement) {
        Railcar freightCar = new FreightCar();
        freightCar.setId(freightCarElement.getAttribute(RailcarXMLTag.ID.getValue()));
        Integer capacity = Integer.parseInt(getElementTextContent(freightCarElement, RailcarXMLTag.CAPACITY.getValue()));
        freightCar.setCapacity(capacity);
        return freightCar;
    }

    private Railcar parsePassengerCar(Element passengerCarElement) {
        Railcar passengerCar = new PassengerCar();
        passengerCar.setId(passengerCarElement.getAttribute(RailcarXMLTag.ID.getValue()));
        Integer capacity = Integer.parseInt(getElementTextContent(passengerCarElement, RailcarXMLTag.CAPACITY.getValue()));
        passengerCar.setCapacity(capacity);
        return passengerCar;
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }
}

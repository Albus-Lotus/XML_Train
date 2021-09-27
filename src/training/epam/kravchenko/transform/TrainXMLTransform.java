package training.epam.kravchenko.transform;

import java.io.FileWriter;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import training.epam.kravchenko.entity.FreightCar;
import training.epam.kravchenko.entity.Railcar;
import training.epam.kravchenko.entity.Train;
import training.epam.kravchenko.tags.RailcarXMLTag;

public class TrainXMLTransform {
    private DocumentBuilder docBuilder;
    Document document;

    public TrainXMLTransform() {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
                .newInstance();
        DocumentBuilder docBuilder = null;
        try {
            docBuilder = documentBuilderFactory.newDocumentBuilder();
            document = docBuilder.newDocument();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void createXMLTrain(String filename, Train train) {
        Element rootElement = document.createElement(RailcarXMLTag.TRAIN.getValue());
        document.appendChild(rootElement);

        String totalFreightCapacity = Integer.toString(train.getTotalFreightCapacity());
        Element totalFreightCapacityElement = document.createElement(RailcarXMLTag.TOTAL_FREIGHT_CAPACITY.getValue());
        totalFreightCapacityElement.appendChild(document.createTextNode(totalFreightCapacity));
        String totalPassengerCapacity = Integer.toString(train.getTotalPassengerCapacity());
        Element totalPassengerCapacityElement = document.createElement(RailcarXMLTag.TOTAL_PASSENGER_CAPACITY.getValue());
        totalPassengerCapacityElement.appendChild(document.createTextNode(totalPassengerCapacity));
        rootElement.appendChild(totalFreightCapacityElement);
        rootElement.appendChild(totalPassengerCapacityElement);

        createXMLRailcars(rootElement, train);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        try {
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new FileWriter(filename));
            transformer.transform(source, result);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException | IOException e) {
            e.printStackTrace();
        }
    }

    private void createXMLRailcars(Element rootElement, Train train) {
        for (Railcar railcar: train.getRailcars()) {
            Element railcarElement;
            if (railcar instanceof FreightCar) {
                railcarElement = document.createElement(RailcarXMLTag.FREIGHTCAR.getValue());
            } else {
                railcarElement = document.createElement(RailcarXMLTag.PASSENGERCAR.getValue());
            }
            railcarElement.setAttribute(RailcarXMLTag.ID.getValue(), railcar.getId());
            Element capacityElement = document.createElement(RailcarXMLTag.CAPACITY.getValue());
            String capacity = Integer.toString(railcar.getCapacity());
            capacityElement.appendChild(document.createTextNode(capacity));
            railcarElement.appendChild(capacityElement);
            rootElement.appendChild(railcarElement);
        }
    }
}

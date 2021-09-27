package training.epam.kravchenko.main;

import training.epam.kravchenko.entity.Train;
import training.epam.kravchenko.parser.RailcarsDOMParser;
import training.epam.kravchenko.transform.TrainXMLTransform;

public class Main {
    private final static String FILE_PATH_TO_READ = "src/training/epam/kravchenko/sources/Railcars.xml";
    private final static String FILE_PATH_TO_WRITE = "src/training/epam/kravchenko/sources/Train.xml";

    public static void main(String[] args) {
        RailcarsDOMParser domParser = new RailcarsDOMParser();
        domParser.parseListRailcars(FILE_PATH_TO_READ);
        Train train = new Train(domParser.getRailcars());
        TrainXMLTransform trainXMLTransform = new TrainXMLTransform();
        trainXMLTransform.createXMLTrain(FILE_PATH_TO_WRITE, train);
    }
}

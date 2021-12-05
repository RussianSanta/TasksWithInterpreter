import FirstTask.RunicInputConverter;
import FirstTask.FTStateMethod;
import FourthTask.AuthomatonShortener;
import SecondTask.Answerer;
import ThirdTask.STStateMethodFour;
import ThirdTask.STStateMethodOne;
import ThirdTask.STStateMethodThree;
import ThirdTask.STStateMethodTwo;
import automations.Automaton;
import automations.impls.AutomatonImpl;
import automations.impls.StateImpl;
import helpers.DefaultInputConverter;
import helpers.FileHelper;
import helpers.InputConverter;

import java.util.ArrayList;

public class InterpreterStarter {
    private static final int START_ENV = 4;

    public static void main(String[] args) {
        InputConverter converter = new DefaultInputConverter();
        String automatonStepsFileName = "";
        switch (START_ENV) {
            case (1):
                //Aaaa
                converter = new RunicInputConverter();
                automatonStepsFileName = "src/main/resources/automates/FirstTask.xlsx";
                parseTable(converter,automatonStepsFileName);
                FTStateMethod.run("Aaaa");
                break;
            case (2):
                //
                automatonStepsFileName = "src/main/resources/automates/SecondTaskOne.xlsx";
                parseTable(converter,automatonStepsFileName);
                Answerer.answer();
                break;
            case (3):
                STStateMethodOne.run("ABAE");
                STStateMethodTwo.run("AACAAACAAACCAAAAABC");
                STStateMethodThree.run("ABCCAAAAAAB");
                STStateMethodFour.run("ACAEAEBDDDDDDDDDDDDDDDD");
                break;
            case (4):
                //Automaton automaton = getAutomaton("src/main/resources/automates/FirstTask.xlsx");
                Automaton automaton = getAutomaton("src/main/resources/automates/FourthTask.xlsx");
                ArrayList<ArrayList<StateImpl>> newSteps = AuthomatonShortener.tryCut(automaton.getStates());
                for (ArrayList<StateImpl> step:newSteps) {
                    step.stream().map(StateImpl::getStateName).forEach(System.out::println);
                    System.out.println("--------");
                }
                break;
        }
    }

    private static void parseTable(InputConverter converter, String automatonStepsFileName) {
        String inputString = FileHelper.getTextFromFile();

        ExcelSheetParser parser = new ExcelSheetParser(converter);
        Automaton automaton = parser.parse(automatonStepsFileName);

        FileHelper.printTextToFile(automaton.execute(inputString));
        automaton.getLogs("src/main/resources/LOGS.txt");
    }

    private static Automaton getAutomaton(String automatonStepsFileName) {
        return new ExcelSheetParser().parse(automatonStepsFileName);
    }
}

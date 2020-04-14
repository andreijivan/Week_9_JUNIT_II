package ro.siit;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Calculator {
    static final Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter output with spacing as in model (10 cm + 1 m - 10 mm): ");
        String output = scanner.nextLine();
        System.out.println("Enter result unit (mm/cm/dm/m/km): ");
        String resultUnit = scanner.nextLine();

        System.out.println("Final result in " + resultUnit.toUpperCase() + " is " + new Calculator().computation(output, resultUnit));
        StatisticsRepository statisticsRepository = new StatisticsRepository(new Calculator());
        System.out.println("Program finished in " + statisticsRepository.nanoTime(output, resultUnit) + " nanoseconds");
        statisticsRepository.fastestTime(output);
    }

    public double computation(String output, String resultUnit) {
        List<String> variables = new ArrayList<>();
        List<Character> operands = new ArrayList<>();
        String copyOutput = output; //need it for logger info

        for (int i = 0; i < output.length(); i++) {
            if (output.charAt(i) == '+') {
                String expression = output.substring(0, i - 1);
                variables.add(expression);
                output = output.substring(i + 1);
                i = 0;
                operands.add('+');
            } else if (output.charAt(i) == '-') {
                String expression = output.substring(0, i - 1);
                variables.add(expression);
                output = output.substring(i + 1);
                i = 0;
                operands.add('-');
            }
        }
        variables.add(output);

        switch (resultUnit.toLowerCase()) {
            case "mm":
                logger.log(Level.DEBUG,copyOutput + " final result in " + resultUnit.toUpperCase() + " equals " + finalResult(variables, operands, "mm"));
                return finalResult(variables, operands, "mm");
            case "cm":
                logger.log(Level.DEBUG,copyOutput + " final result in " + resultUnit.toUpperCase() + " equals " + finalResult(variables, operands, "cm"));
                return finalResult(variables, operands, "cm");
            case "dm":
                logger.log(Level.DEBUG,copyOutput + " final result in " + resultUnit.toUpperCase() + " equals " + finalResult(variables, operands, "dm"));
                return finalResult(variables, operands, "dm");
            case "m":
                logger.log(Level.DEBUG,copyOutput + " final result in " + resultUnit.toUpperCase() + " equals " + finalResult(variables, operands, "m"));
                return finalResult(variables, operands, "m");
            case "km":
                logger.log(Level.DEBUG,copyOutput + " final result in " + resultUnit.toUpperCase() + " equals " + finalResult(variables, operands, "km"));
                return finalResult(variables, operands, "km");
            default:
                System.out.println("Wrong result unit.");
        }
        return 0;//will never be reached
    }

    public static double finalResult(List<String> variables, List<Character> operands, String resultUnit) {
        double resultInMM = convertVariableToMM(variables.get(0));
        double finalResult = 0;
        int i = 1;
        for (char c : operands) {
            while (i < variables.size()) {
                if (c == '+') {
                    resultInMM += convertVariableToMM(variables.get(i));
                    i++;
                    break;
                } else if (c == '-') {
                    resultInMM -= convertVariableToMM(variables.get(i));
                    i++;
                    break;
                }
            }
        }
        switch (resultUnit) {
            case "mm":
                finalResult = resultInMM;
                break;
            case "cm":
                finalResult = resultInMM / 10;
                break;
            case "dm":
                finalResult = resultInMM / 100;
                break;
            case "m":
                finalResult = resultInMM / 1000;
                break;
            case "km":
                finalResult = resultInMM / 1000000;
                break;
        }

        return finalResult;

    }

    public static double convertVariableToMM(String s) {
        double x = 0;
        if (s.contains("mm")) {
            x = convertVariableToInt(s);
        } else if (s.contains("cm")) {
            x = convertVariableToInt(s) * 10;
        } else if (s.contains("dm")) {
            x = convertVariableToInt(s) * 100;
        } else if (s.contains("m") && !s.contains("km")) {
            x = convertVariableToInt(s) * 1000;
        } else if (s.contains("km")) {
            x = convertVariableToInt(s) * 1000000;
        }
        return x;

    }

    public static double convertVariableToInt(String s) {
        return Integer.parseInt(s.replaceAll("[^0-9]", ""));
    }

}


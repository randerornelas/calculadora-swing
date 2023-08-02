package model;

import java.util.ArrayList;
import java.util.List;

public class Memory {
    private static final Memory instance = new Memory();

    private String currentText = "";
    private String bufferText = "";

    private CommandType lastOperation;

    private boolean cleanDisplay;

    private final List<MemoryObserver> observers = new ArrayList<>();

    private Memory() {

    }

    public static Memory getInstance() {
        return instance;
    }

    public void registerObserver(MemoryObserver observer) {
        observers.add(observer);
    }

    public String getCurrentText() {
        return currentText.isEmpty() ? "0" : currentText;
    }

    public void notifyObservers(String text) {
        CommandType commandType = detectCommandType(text);

        if(commandType == CommandType.RESET) {
            currentText = "";
            bufferText = "";
            lastOperation = null;
            cleanDisplay = false;
        } else if(commandType == CommandType.NUMBER) {
            if(text.equals("0") && getCurrentText().equals("0")) {
                return;
            }

            currentText = cleanDisplay ? text : currentText + text;

            cleanDisplay = false;
        } else if(commandType == CommandType.COMMA) {
            if(getCurrentText().contains(",")) {
                return;
            } else if(getCurrentText().equals("0")) {
                currentText = "0" + ",";
            } else {
                currentText += ",";
            }
        } else if(commandType == CommandType.MINUS_SYMBOL) {
            if(getCurrentText().equals("0")) {
                return;
            } else {
                currentText = getCurrentText().contains("-") ?
                        currentText.replace("-", "") : "-" + currentText;
            }
        } else {
            currentText = getResult();
            bufferText = currentText;
            lastOperation = commandType;
            cleanDisplay = true;
        }

        observers.forEach(o -> o.update(getCurrentText()));
    }

    private String getResult() {
        double result = 0;

        if(lastOperation == null || lastOperation == CommandType.EQUALS) {
            return currentText;
        }

        double bufferNumber = Double.parseDouble(bufferText.replace(",", "."));
        double currentNumber = Double.parseDouble(currentText.replace(",", "."));

        if(lastOperation == CommandType.DIVISION) {
            result = bufferNumber / currentNumber;
        } else if(lastOperation == CommandType.MULTIPLICATION) {
            result = bufferNumber * currentNumber;
        } else if(lastOperation == CommandType.SUBSTRACTION) {
            result = bufferNumber - currentNumber;
        } else if(lastOperation == CommandType.SUM) {
            result = bufferNumber + currentNumber;
        }

        String resultString = Double.toString(result).replace(".", ",");

        return resultString.endsWith(",0") ?
                resultString.replace(",0", "") : resultString;
    }

    private CommandType detectCommandType(String text) {
        try {
            Integer.parseInt(text);
            return CommandType.NUMBER;
        } catch(NumberFormatException e) {
            switch (text) {
                case "C" -> {
                    return CommandType.RESET;
                }
                case "÷" -> {
                    return CommandType.DIVISION;
                }
                case "x" -> {
                    return CommandType.MULTIPLICATION;
                }
                case "-" -> {
                    return CommandType.SUBSTRACTION;
                }
                case "+" -> {
                    return CommandType.SUM;
                }
                case "+/-" -> {
                    return CommandType.MINUS_SYMBOL;
                }
                case "," -> {
                    return CommandType.COMMA;
                }
                case "=" -> {
                    return CommandType.EQUALS;
                }
            }
        }

        return null;
    }
}

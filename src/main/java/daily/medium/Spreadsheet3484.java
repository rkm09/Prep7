package daily.medium;

import java.util.HashMap;
import java.util.Map;

public class Spreadsheet3484 {
}

class Spreadsheet {
    Map<String, Integer> cellMap;
    public Spreadsheet(int rows) {
        cellMap = new HashMap<>();
    }

    public void setCell(String cell, int value) {
        cellMap.put(cell, value);
    }

    public void resetCell(String cell) {
        cellMap.put(cell, 0);
    }

    public int getValue(String formula) {
        formula = formula.substring(1);
        String[] operands = formula.split("\\+");
        String leftOperand = operands[0];
        String rightOperand = operands[1];
        int result = 0;
//        process left
        if(leftOperand.matches("\\d+")) {
            result += Integer.parseInt(leftOperand);
        } else {
            result += cellMap.getOrDefault(leftOperand, 0);
        }
//        process right
        if(rightOperand.matches("\\d+")) {
            result += Integer.parseInt(rightOperand);
        } else {
            result += cellMap.getOrDefault(rightOperand, 0);
        }
        return result;
    }
}

/**
 * Your Spreadsheet object will be instantiated and called as such:
 * Spreadsheet obj = new Spreadsheet(rows);
 * obj.setCell(cell,value);
 * obj.resetCell(cell);
 * int param_3 = obj.getValue(formula);
 */
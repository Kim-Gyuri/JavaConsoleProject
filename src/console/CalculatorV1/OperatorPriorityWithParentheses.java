package console.CalculatorV1;

import java.util.Arrays;
import java.util.List;

public enum OperatorPriorityWithParentheses {
    PARENTHESES(0, Arrays.asList("(")),
    PLUS_MINUS(1, Arrays.asList("+", "-")),
    MULTI_DIVIDE(2, Arrays.asList("*","/"));

    private int priority;
    private List<String> operatorList;

    OperatorPriorityWithParentheses(int priority, List<String> operatorList) {
        this.priority = priority;
        this.operatorList = operatorList;
    }

    final static OperatorPriorityWithParentheses findPriority(String operator) {
        return Arrays.stream(OperatorPriorityWithParentheses.values())
                .filter(operatorPriority -> operatorPriority.hasOperator(operator))
                .findAny()
                .orElse(null);
    }

    private boolean hasOperator(String operator) {
        return operatorList.stream().anyMatch(op -> op.equals(operator));
    }

    public int getPriority() {
        return priority;
    }
}

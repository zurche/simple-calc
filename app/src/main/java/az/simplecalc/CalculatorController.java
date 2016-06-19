package az.simplecalc;

import com.udojava.evalex.Expression;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * Created by az on 18/06/16.
 */
public class CalculatorController {

    private static final String STRING_COMMA = ".";
    private List<Character> validOperators = Arrays.asList('+', '-', '/', '*');
    private List<Character> validNumbers = Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');

    private final CalculatorScreenActions mCalculatorScreenActions;
    private String mCurrentStringExpression;


    public CalculatorController(CalculatorScreenActions calculatorScreenActions) {
        mCalculatorScreenActions = calculatorScreenActions;
        mCurrentStringExpression = "";
    }

    public void onOperatorAdd(String value) {
        boolean isCommaAddedToExpression = false;

        if (isValueAnOperator(value) && mCurrentStringExpression.length() > 0) {
            char lastCharacterOfExpression = mCurrentStringExpression.charAt(mCurrentStringExpression.length() - 1);

            if (isValueAnOperator(String.valueOf(lastCharacterOfExpression))) {
                clearLastCharOfExpression();
            }
        } else if (value.equals(STRING_COMMA)) {
            char[] expressionArray = mCurrentStringExpression.toCharArray();
            for (char c : expressionArray) {
                if (c == STRING_COMMA.toCharArray()[0]) {
                    isCommaAddedToExpression = true;
                }
                if (validOperators.contains(c)) {
                    isCommaAddedToExpression = false;
                }
            }

            // If las character of expression is either a number or an operator, do not add the comma to the expression.
            char lastCharacterOfExpression = mCurrentStringExpression.charAt(mCurrentStringExpression.length() - 1);
            if (validOperators.contains(lastCharacterOfExpression) || validNumbers.contains(lastCharacterOfExpression)) {
                isCommaAddedToExpression = true;
            }
        }

        if (!isCommaAddedToExpression) {
            mCurrentStringExpression += value;
            mCalculatorScreenActions.updateCurrentExpression(mCurrentStringExpression);
        }
    }

    public void onClearExpression() {
        mCurrentStringExpression = "";
        mCalculatorScreenActions.updateCurrentExpression(mCurrentStringExpression);
        mCalculatorScreenActions.showResult("");
    }

    public void onCalculateResult() {
        clearLastValueIfItIsAnOperator();

        Expression expression = new Expression(mCurrentStringExpression);

        BigDecimal bigDecimalResult = expression.eval();

        double doubleResult = bigDecimalResult.doubleValue();

        String stringResult;

        if (isValueInteger(doubleResult)) {
            int roundedValue = (int) Math.round(doubleResult);
            stringResult = String.valueOf(roundedValue);
        } else {
            stringResult = String.valueOf(doubleResult);
        }

        mCalculatorScreenActions.showResult(stringResult);
        mCurrentStringExpression = stringResult;
    }

    private boolean isValueAnOperator(String value) {
        return validOperators.contains(value.toCharArray()[0]);
    }

    private void clearLastValueIfItIsAnOperator() {
        if (isValueAnOperator(String.valueOf(getLastCharOfExpression()))) {
            clearLastCharOfExpression();
            mCalculatorScreenActions.updateCurrentExpression(mCurrentStringExpression);
        }
    }

    private void clearLastCharOfExpression() {
        mCurrentStringExpression = mCurrentStringExpression.substring(0, mCurrentStringExpression.length() - 1);
    }

    private char getLastCharOfExpression() {
        int currentExpressionLastValuePosition = mCurrentStringExpression.length() - 1;
        return mCurrentStringExpression.charAt(currentExpressionLastValuePosition);
    }

    private boolean isValueInteger(double number) {
        int roundedValue = (int) Math.round(number);
        return number % roundedValue == 0;
    }
}

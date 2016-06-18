package az.simplecalc;

import com.udojava.evalex.Expression;

import java.math.BigDecimal;

/**
 * Created by az on 18/06/16.
 */
public class CalculatorController {

    private final CalculatorScreenActions mCalculatorScreenActions;
    private String mCurrentStringExpression;

    public CalculatorController(CalculatorScreenActions calculatorScreenActions) {
        mCalculatorScreenActions = calculatorScreenActions;
        mCurrentStringExpression = "";
    }

    void onOperatorAdd(String value) {
        mCurrentStringExpression += value;
        mCalculatorScreenActions.updateCurrentExpression(mCurrentStringExpression);
    }

    public void onClearsExpression() {
        mCurrentStringExpression = "";
        mCalculatorScreenActions.updateCurrentExpression(mCurrentStringExpression);
    }

    public void onCalculateResult() {
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
    }

    private boolean isValueInteger(double number) {
        int roundedValue = (int) Math.round(number);
        return number % roundedValue == 0;
    }
}

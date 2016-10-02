package az.simplecalc.presenter;

import com.udojava.evalex.Expression;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * Created by az on 18/06/16.
 */
public class CalculatorPresenter implements CalculatorContract.Presenter{

    private static final String STRING_COMMA = ".";
    public static final String PERCENTAGE = "%";
    public static final String SCIENTIFIC_NOTATION_CHAR = "E";
    public static final String INFINITY = "Infinity";
    private List<Character> validOperators = Arrays.asList('+', '-', '/', '*');

    private boolean isNumberPositive = true;

    private final CalculatorContract.View view;
    private String mCurrentStringExpression;


    public CalculatorPresenter(CalculatorContract.View view) {
        this.view = view;
        mCurrentStringExpression = "";
    }

    @Override
    public void onExpressionSignChange() {
        mCurrentStringExpression = isNumberPositive ?
                "-" + mCurrentStringExpression :
                mCurrentStringExpression.substring(1, mCurrentStringExpression.length());
        isNumberPositive = !isNumberPositive;
        view.updateCurrentExpression(mCurrentStringExpression);
    }

    @Override
    public void onOperatorAdd(String value) {
        if (mCurrentStringExpression.isEmpty() && value.equals(STRING_COMMA)) {
            view.showInvalidExpressionMessage();
        } else {
            boolean isCommaAddedToExpression = false;

            if ((isValueAnOperator(value) || value.equals(PERCENTAGE)) && mCurrentStringExpression.length() > 0) {
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

                // If last character of expression is either a number or an operator, do not add the comma to the expression.
                char lastCharacterOfExpression = mCurrentStringExpression.charAt(mCurrentStringExpression.length() - 1);
                if (validOperators.contains(lastCharacterOfExpression)) {
                    isCommaAddedToExpression = true;
                }
            }

            if (!isCommaAddedToExpression) {
                mCurrentStringExpression += value;
                view.updateCurrentExpression(mCurrentStringExpression);
            }
        }

    }

    @Override
    public void onClearExpression() {
        mCurrentStringExpression = "";
        view.updateCurrentExpression(mCurrentStringExpression);
        view.showResult("");
    }

    @Override
    public void onCalculateResult() {
        if (mCurrentStringExpression.isEmpty() || mCurrentStringExpression.contains(INFINITY)) {
            view.showInvalidExpressionMessage();
        } else {
            clearLastValueIfItIsAnOperator();

            mCurrentStringExpression = mCurrentStringExpression.replaceAll(PERCENTAGE, "/100");

            Expression expression = new Expression(mCurrentStringExpression);

            BigDecimal bigDecimalResult = expression.eval();

            double doubleResult = bigDecimalResult.doubleValue();

            String stringResult;

            if (isValueInteger(doubleResult) && !isScientificNotation(Double.toString(doubleResult))) {
                int roundedValue = (int) Math.round(doubleResult);
                stringResult = String.valueOf(roundedValue);
            } else {
                stringResult = Double.toString(doubleResult);
            }

            view.showResult(stringResult);
            mCurrentStringExpression = stringResult;
        }
    }

    private boolean isValueAnOperator(String value) {
        return validOperators.contains(value.toCharArray()[0]);
    }

    private void clearLastValueIfItIsAnOperator() {
        if (isValueAnOperator(String.valueOf(getLastCharOfExpression()))) {
            clearLastCharOfExpression();
            view.updateCurrentExpression(mCurrentStringExpression);
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

    private boolean isScientificNotation(String numberString) {
        try {
            new BigDecimal(numberString);
        } catch (NumberFormatException e) {
            return false;
        }

        return numberString.toUpperCase().contains(SCIENTIFIC_NOTATION_CHAR);
    }
}

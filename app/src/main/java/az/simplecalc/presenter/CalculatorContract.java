package az.simplecalc.presenter;

/**
 * Created by az on 18/06/16.
 */
public interface CalculatorContract {

    interface View {
        void showResult(String stringResult);

        void updateCurrentExpression(String currentStringExpression);

        void showInvalidExpressionMessage();
    }

    interface Presenter {
        void onOperatorAdd(String operator);

        void onClearExpression();

        void onCalculateResult();

        void onExpressionSignChange();
    }

}

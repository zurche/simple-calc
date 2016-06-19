package az.simplecalc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import az.simplecalc.util.FontsOverride;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Calculator extends AppCompatActivity implements CalculatorScreenActions {

    @BindView(R.id.ac_button)
    RelativeLayout ac_button;
    @BindView(R.id.percentage_button)
    RelativeLayout percentage_button;
    @BindView(R.id.value_switch_button)
    RelativeLayout value_switch_button;
    @BindView(R.id.divide_button)
    RelativeLayout divide_button;
    @BindView(R.id.seven_button)
    RelativeLayout seven_button;
    @BindView(R.id.eight_button)
    RelativeLayout eight_button;
    @BindView(R.id.nine_button)
    RelativeLayout nine_button;
    @BindView(R.id.multiply_button)
    RelativeLayout multiply_button;
    @BindView(R.id.four_button)
    RelativeLayout four_button;
    @BindView(R.id.five_button)
    RelativeLayout five_button;
    @BindView(R.id.six_button)
    RelativeLayout six_button;
    @BindView(R.id.plus_button)
    RelativeLayout plus_button;
    @BindView(R.id.one_button)
    RelativeLayout one_button;
    @BindView(R.id.two_button)
    RelativeLayout two_button;
    @BindView(R.id.three_button)
    RelativeLayout three_button;
    @BindView(R.id.minus_button)
    RelativeLayout minus_button;
    @BindView(R.id.zero_button)
    RelativeLayout zero_button;
    @BindView(R.id.comma_button)
    RelativeLayout comma_button;
    @BindView(R.id.equals_button)
    RelativeLayout equals_button;

    @BindView(R.id.expression_field)
    TextView expression_field;
    @BindView(R.id.expression_result)
    TextView expression_result;

    private String mCurrentStringExpression;
    private CalculatorController mCalculatorController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        setupCustomFont();

        ButterKnife.bind(this);

        mCalculatorController = new CalculatorController(this);

        mCurrentStringExpression = "";
    }

    @OnClick({R.id.zero_button,
            R.id.one_button, R.id.two_button, R.id.three_button,
            R.id.four_button, R.id.five_button, R.id.six_button,
            R.id.seven_button, R.id.eight_button, R.id.nine_button,
            R.id.divide_button, R.id.multiply_button, R.id.minus_button, R.id.plus_button, R.id.percentage_button,
            R.id.equals_button,
            R.id.comma_button,
            R.id.ac_button,
            R.id.value_switch_button})
    void buttonClicked(View view) {
        switch (view.getId()) {
            case R.id.zero_button:
                mCalculatorController.onOperatorAdd(getString(R.string.zero));
                break;
            case R.id.one_button:
                mCalculatorController.onOperatorAdd(getString(R.string.one));
                break;
            case R.id.two_button:
                mCalculatorController.onOperatorAdd(getString(R.string.two));
                break;
            case R.id.three_button:
                mCalculatorController.onOperatorAdd(getString(R.string.three));
                break;
            case R.id.four_button:
                mCalculatorController.onOperatorAdd(getString(R.string.four));
                break;
            case R.id.five_button:
                mCalculatorController.onOperatorAdd(getString(R.string.five));
                break;
            case R.id.six_button:
                mCalculatorController.onOperatorAdd(getString(R.string.six));
                break;
            case R.id.seven_button:
                mCalculatorController.onOperatorAdd(getString(R.string.seven));
                break;
            case R.id.eight_button:
                mCalculatorController.onOperatorAdd(getString(R.string.eight));
                break;
            case R.id.nine_button:
                mCalculatorController.onOperatorAdd(getString(R.string.nine));
                break;
            case R.id.divide_button:
                mCalculatorController.onOperatorAdd(getString(R.string.divide_operator));
                break;
            case R.id.multiply_button:
                mCalculatorController.onOperatorAdd(getString(R.string.multiply_expression));
                break;
            case R.id.plus_button:
                mCalculatorController.onOperatorAdd(getString(R.string.plus));
                break;
            case R.id.minus_button:
                mCalculatorController.onOperatorAdd(getString(R.string.minus));
                break;
            case R.id.comma_button:
                mCalculatorController.onOperatorAdd(getString(R.string.comma_expression));
                break;
            case R.id.ac_button:
                mCalculatorController.onClearExpression();
                break;
            case R.id.equals_button:
                mCalculatorController.onCalculateResult();
                break;
        }
    }

    private void setupCustomFont() {
        FontsOverride.setDefaultFont(this, "DEFAULT", "Montserrat-Light.ttf");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "Montserrat-Light.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "Montserrat-Light.ttf");
        FontsOverride.setDefaultFont(this, "SANS_SERIF", "Montserrat-Light.ttf");
    }

    @Override
    public void showResult(String stringResult) {
        expression_result.setText(stringResult);
    }

    @Override
    public void updateCurrentExpression(String currentStringExpression) {
        expression_field.setText(currentStringExpression);
    }
}

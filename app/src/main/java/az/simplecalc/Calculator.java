package az.simplecalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.udojava.evalex.Expression;

import java.math.BigDecimal;

import az.simplecalc.util.FontsOverride;

public class Calculator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupCustomFont();

        BigDecimal result = null;

        Expression expression = new Expression("20*10+15");

        result = expression.eval();

        System.out.println("20*10+15 = " + result);

        setContentView(R.layout.activity_calculator);
    }

    private void setupCustomFont() {
        FontsOverride.setDefaultFont(this, "DEFAULT", "Montserrat-Light.ttf");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "Montserrat-Light.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "Montserrat-Light.ttf");
        FontsOverride.setDefaultFont(this, "SANS_SERIF", "Montserrat-Light.ttf");
    }
}

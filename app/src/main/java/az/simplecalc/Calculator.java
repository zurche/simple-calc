package az.simplecalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import az.simplecalc.util.FontsOverride;

public class Calculator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupCustomFont();

        setContentView(R.layout.activity_calculator);
    }

    private void setupCustomFont() {
        FontsOverride.setDefaultFont(this, "DEFAULT", "Montserrat-Light.ttf");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "Montserrat-Light.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "Montserrat-Light.ttf");
        FontsOverride.setDefaultFont(this, "SANS_SERIF", "Montserrat-Light.ttf");
    }
}

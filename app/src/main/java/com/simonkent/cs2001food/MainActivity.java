package com.simonkent.cs2001food;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // The first line below uses the test implementation
    //FavouriteFoodManager foodManager = new TestFavouriteFoodManager();
    // Swap to this line (commenting out the one above) to use the Servlet version of the foodManager
    FavouriteFoodManager foodManager = new ServletFoodManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.press_button);

        button.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          TextView foodView = (TextView)findViewById(R.id.food_textview);
                                          foodView.setText(foodManager.getFavouriteFood());
                                      }
                                  }
        );

    }
}

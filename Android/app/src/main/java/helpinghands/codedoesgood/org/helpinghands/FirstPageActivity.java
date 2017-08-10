package helpinghands.codedoesgood.org.helpinghands;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FirstPageActivity extends AppCompatActivity
    implements View.OnClickListener{

    private static final String TAG = FirstPageActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        // get reference objects from view
        TextView titleText = (TextView) findViewById(R.id.title_first_page);
        TextView descriptionText = (TextView) findViewById(R.id.description_first_page);
        Button quickTourButton = (Button) findViewById(R.id.quick_tour_button);
        Button continueSignInButton = (Button) findViewById(R.id.continue_with_sign_in_button);

        // Apply font
        titleText.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(),String.format("fonts/Lato/Lato-Bold.ttf")));
        descriptionText.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(),String.format("fonts/Lato/Lato-Regular.ttf")));
        quickTourButton.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(),String.format("fonts/Lato/Lato-Light.ttf")));
        continueSignInButton.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(),String.format("fonts/Lato/Lato-Bold.ttf")));

        // Register button on click listeners
        quickTourButton.setOnClickListener(this);
        continueSignInButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.quick_tour_button:
                Log.d(TAG,"Clicked on button 'Take a Quick Tour'.");
                break;

            case R.id.continue_with_sign_in_button:
                Log.d(TAG,"Clicked on button 'Sign in with existing account'");
                Intent loginActivity = new Intent(this, LoginActivity.class);
                startActivity(loginActivity);
                finish();
                break;
        }
    }
}

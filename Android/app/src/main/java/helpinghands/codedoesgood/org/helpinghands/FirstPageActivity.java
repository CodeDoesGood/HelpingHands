package helpinghands.codedoesgood.org.helpinghands;

import android.app.Activity;
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
    private static final int REQUEST_LOGIN = 384;
    private static final int REQUEST_ONBOARD = 981;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        init();
    }

    private void init(){
        // get reference objects from view
        TextView titleText = (TextView) findViewById(R.id.title_first_page);
        TextView descriptionText = (TextView) findViewById(R.id.description_first_page);
        Button quickTourButton = (Button) findViewById(R.id.quick_tour_button);
        Button continueSignInButton = (Button) findViewById(R.id.continue_with_sign_in_button);

        // Apply font
        titleText.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/Lato/Lato-Bold.ttf"));
        descriptionText.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/Lato/Lato-Regular.ttf"));
        quickTourButton.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/Lato/Lato-Light.ttf"));
        continueSignInButton.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/Lato/Lato-Bold.ttf"));

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
                startActivityForResult(loginActivity,REQUEST_LOGIN);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_LOGIN:
                if(resultCode == RESULT_OK){
                    // Result OK, start Home activity
                    Log.d(TAG,"RESULT OK");
                }
            break;

            case REQUEST_ONBOARD:
                break;
        }
    }
}

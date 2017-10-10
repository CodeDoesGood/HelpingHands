package helpinghands.codedoesgood.org.helpinghands;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

public class Search extends AppCompatActivity
    implements View.OnClickListener{

    private String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        init();
    }

    private void init(){
        ImageButton searchBack = (ImageButton) findViewById(R.id.search_close);
        searchBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.search_close:
                setResult(RESULT_OK);
                finish();
                break;

            default:
                break;
        }
    }
}

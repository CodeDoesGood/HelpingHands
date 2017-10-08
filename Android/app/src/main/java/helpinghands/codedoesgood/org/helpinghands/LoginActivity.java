package helpinghands.codedoesgood.org.helpinghands;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class LoginActivity extends AppCompatActivity
    implements View.OnClickListener{

    private String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
    }

    private void init(){
        // Initialize with first fragment: get mobile number
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.login_content, new FragmentLoginMobileNumber())
                .commit();

        // Register click event listner
        ImageButton closeButton = (ImageButton) findViewById(R.id.login_button_close);
        closeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_button_close:
                setResult(RESULT_CANCELED);
                finish();
            default:
                break;
        }
    }

    public static class FragmentLoginMobileNumber extends Fragment
        implements View.OnClickListener{

        private String TAG = getClass().getSimpleName();
        private EditText inputMobileNumber;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_login_number,container,false);
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            init();

        }

        private void init(){
            // Animate the fregment view with fade-in
            try {
                getView().startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fadein));
            }catch (NullPointerException e){
                e.printStackTrace();
            }

            // get element views
            Button sendButton = (Button) getActivity().findViewById(R.id.login_fragment_button_send);
            inputMobileNumber = (EditText) getActivity().findViewById(R.id.login_fragment_input_mobile);

            // set onclick events
            sendButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.login_fragment_button_send:
                    Log.d(TAG,"onClick -> send");
                    String mobileNumber = inputMobileNumber.getText().toString();
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.login_content, new FragmentLoginVerify())
                            .commit();
                    break;

                default:
                    break;
            }
        }
    }

    public static class FragmentLoginVerify extends Fragment
        implements View.OnClickListener{

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_login_verify,container,false);
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            Button buttonSubmit = (Button) getActivity().findViewById(R.id.login_fragment_button_verify);
            buttonSubmit.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.login_fragment_button_verify:
                    getActivity().setResult(RESULT_OK);
                    getActivity().finish();
                    break;

                default:
                    break;
            }
        }
    }
}

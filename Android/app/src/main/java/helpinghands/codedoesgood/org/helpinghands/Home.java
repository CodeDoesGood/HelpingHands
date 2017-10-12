package helpinghands.codedoesgood.org.helpinghands;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Home extends AppCompatActivity
    implements View.OnClickListener, TabLayout.OnTabSelectedListener{

    private String TAG = getClass().getSimpleName();
    private TabLayout optionTabs;
    private Toolbar homeToolbar;
    private static final int REQUEST_SEARCH = 253;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();
    }

    private void init(){
        // Get Fragment Manager
        fragmentManager = getSupportFragmentManager();

        // initialize home toolbar
        homeToolbar = (Toolbar) findViewById(R.id.home_toolbar);
        homeToolbar.setTitle(getText(R.string.title_home));
        homeToolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        homeToolbar.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimary,null));
        homeToolbar.setNavigationIcon(R.drawable.ic_helping_hands);
        setSupportActionBar(homeToolbar);

        // initialize options toolbar//
        optionTabs = (TabLayout) findViewById(R.id.home_options);
        optionTabs.addTab(optionTabs
                .newTab()
                .setIcon(R.drawable.ic_explore_primary_24dp)
                .setText(R.string.title_home)
                .setTag(R.string.title_home)
        );

        optionTabs.addTab(optionTabs
                .newTab()
                .setIcon(R.drawable.ic_search_black_24dp)
                .setText(R.string.title_search)
                .setTag(R.string.title_search)
        );

        optionTabs.addTab(optionTabs
                .newTab()
                .setIcon(R.drawable.ic_favourite_black_24dp)
                .setText(R.string.title_favourite)
                .setTag(R.string.title_favourite)
        );

        optionTabs.addTab(optionTabs
                .newTab()
                .setIcon(R.drawable.ic_post_black_24dp)
                .setText(R.string.title_post)
                .setTag(R.string.title_post)
        );

        optionTabs.addTab(optionTabs
                .newTab()
                .setIcon(R.drawable.ic_settings_black_24dp)
                .setText(R.string.title_settings)
                .setTag(R.string.title_settings)
        );

        optionTabs.addOnTabSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu_toolbar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        // Change view on tab selected
        Log.d(TAG,"onTabSelected: "+tab.getText());
        String stringSelectedTab = tab.getText().toString();
        try{
            if(stringSelectedTab.equals(getString(R.string.title_home))){
                // On home tab selected
                if(setSelectedTab(0)){
                    homeToolbar.setTitle(getText(R.string.title_home));
                    setFragment(getText(R.string.title_home).toString());
                }
            }else if(stringSelectedTab.equals(getString(R.string.title_search))) {
                // On search tab selected
                if(setSelectedTab(1)){
                }
            }else if(stringSelectedTab.equals(getString(R.string.title_favourite))){
                // On favourite tab selected
                if(setSelectedTab(2)){
                    homeToolbar.setTitle(getText(R.string.title_favourite));
                    setFragment(getText(R.string.title_favourite).toString());
                }
            }else if(stringSelectedTab.equals(getString(R.string.title_post))){
                // On post tab selected
                if(setSelectedTab(3)){
                    homeToolbar.setTitle(getText(R.string.title_post));
                    setFragment(getText(R.string.title_post).toString());
                }
            }else if(stringSelectedTab.equals(getString(R.string.title_settings))){
                // On settings tab selected
                if(setSelectedTab(4)){
                    homeToolbar.setTitle(getText(R.string.title_settings));
                }
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        String reselectedTab = tab.getText().toString();

        if(reselectedTab.equals(getString(R.string.title_search))){
            openSearchActivity();
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onClick(View view) {

    }

    private boolean setSelectedTab(int index){

        // check if selected tab index is within tab count
        if( (index > optionTabs.getTabCount() ) || index < 0 ){
            return false;
        }

        if(optionTabs != null) {
            try {
                // reset tab icons with black color
                optionTabs.getTabAt(0).setIcon(R.drawable.ic_explore_black_24dp);
                optionTabs.getTabAt(1).setIcon(R.drawable.ic_search_black_24dp);
                optionTabs.getTabAt(2).setIcon(R.drawable.ic_favourite_black_24dp);
                optionTabs.getTabAt(3).setIcon(R.drawable.ic_post_black_24dp);
                optionTabs.getTabAt(4).setIcon(R.drawable.ic_settings_black_24dp);

                // set selected tab icon with primary color
                switch (index){
                    case 0:
                        optionTabs.getTabAt(index).setIcon(R.drawable.ic_explore_primary_24dp);
                        break;
                    case 1:
                        optionTabs.getTabAt(index).setIcon(R.drawable.ic_search_primary_24dp);
                        openSearchActivity();
                        break;
                    case 2:
                        optionTabs.getTabAt(index).setIcon(R.drawable.ic_favourite_primary_24dp);
                        break;
                    case 3:
                        optionTabs.getTabAt(index).setIcon(R.drawable.ic_post_primary_24dp);
                        break;
                    case 4:
                        optionTabs.getTabAt(index).setIcon(R.drawable.ic_settings_primary_24dp);
                        showSettingsActivity();
                        break;
                }

                return  true;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }

        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_SEARCH:
                // On Search Activity Result
                TabLayout.Tab tab = optionTabs.getTabAt(0);
                try {
                    tab.select();
                }catch (NullPointerException e){
                    e.printStackTrace();
                }
                setSelectedTab(0);
                break;
            default:
                break;
        }
    }

    private void openSearchActivity(){
        Intent searchIntent = new Intent(Home.this, Search.class);
        startActivityForResult(searchIntent,REQUEST_SEARCH);
    }

    private void showSettingsActivity(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.home_content,new SettingsFragment()).commit();
    }

    private void setFragment(String frag){
        switch (frag){
            case "Home":
                fragmentManager.beginTransaction()
                        .replace(R.id.home_content, new FragmentHome())
                        .commit();
                break;
            case "Favourite":
                fragmentManager.beginTransaction()
                        .replace(R.id.home_content, new FragmentFavourite())
                        .commit();
                break;
            case "New post":
                fragmentManager.beginTransaction()
                        .replace(R.id.home_content, new FragmentPost())
                        .commit();
                break;

            default:
                break;
        }
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {

        private String TAG = getClass().getSimpleName();

        @Override
        public void onCreatePreferences(Bundle bundle, String s) {
            // Load the Preferences from the XML file
            addPreferencesFromResource(R.xml.hh_preferences);
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            Log.d(TAG,"onViewCreated Preferences");
        }
    }

    public static class FragmentHome extends Fragment {

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_home,container,false);
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
        }
    }

    public static class FragmentFavourite extends Fragment {

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_favourite,container,false);
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
        }
    }

    public static class FragmentPost extends Fragment {

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_post,container,false);
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
        }
    }

}

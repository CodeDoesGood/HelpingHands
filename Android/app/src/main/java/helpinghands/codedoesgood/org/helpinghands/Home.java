package helpinghands.codedoesgood.org.helpinghands;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Home extends AppCompatActivity
    implements View.OnClickListener, TabLayout.OnTabSelectedListener{

    private String TAG = getClass().getSimpleName();
    private TabLayout optionTabs;
    private Toolbar homeToolbar;
    private static final int REQUEST_SEARCH = 253;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();
    }

    private void init(){
        // initialize home toolbar
        homeToolbar = (Toolbar) findViewById(R.id.home_toolbar);
        homeToolbar.setTitle("Helping Hands");
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

                }
            }else if(stringSelectedTab.equals(getString(R.string.title_search))) {
                // On search tab selected
                if(setSelectedTab(1)){

                }
            }else if(stringSelectedTab.equals(getString(R.string.title_favourite))){
                // On favourite tab selected
                if(setSelectedTab(2)){

                }
            }else if(stringSelectedTab.equals(getString(R.string.title_post))){
                // On post tab selected
                if(setSelectedTab(3)){

                }
            }else if(stringSelectedTab.equals(getString(R.string.title_settings))){
                // On settings tab selected
                if(setSelectedTab(4)){

                }
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

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
                        Intent searchIntent = new Intent(Home.this, Search.class);
                        startActivityForResult(searchIntent,REQUEST_SEARCH);
                        break;
                    case 2:
                        optionTabs.getTabAt(index).setIcon(R.drawable.ic_favourite_primary_24dp);
                        break;
                    case 3:
                        optionTabs.getTabAt(index).setIcon(R.drawable.ic_post_primary_24dp);
                        break;
                    case 4:
                        optionTabs.getTabAt(index).setIcon(R.drawable.ic_settings_primary_24dp);
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
                break;
            default:
                break;
        }
    }
}

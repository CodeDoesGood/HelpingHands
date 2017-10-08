package helpinghands.codedoesgood.org.helpinghands;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Home extends AppCompatActivity
    implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();
    }

    private void init(){
        TabLayout optionTabs = (TabLayout) findViewById(R.id.home_options);
        TextView title = (TextView) findViewById(R.id.home_title);
        title.setText(R.string.title_home);

        optionTabs.addTab(optionTabs
                .newTab()
                .setIcon(R.drawable.ic_explore_primary_24dp)
                .setText(R.string.title_home)
        );

        optionTabs.addTab(optionTabs
                .newTab()
                .setIcon(R.drawable.ic_search_black_24dp)
                .setText(R.string.title_search)
        );

        optionTabs.addTab(optionTabs
                .newTab()
                .setIcon(R.drawable.ic_favourite_black_24dp)
                .setText(R.string.title_favourite)
        );

        optionTabs.addTab(optionTabs
                .newTab()
                .setIcon(R.drawable.ic_post_black_24dp)
                .setText(R.string.title_post)
        );

        optionTabs.addTab(optionTabs
                .newTab()
                .setIcon(R.drawable.ic_settings_black_24dp)
                .setText(R.string.title_settings)
        );
    }

    @Override
    public void onClick(View view) {

    }
}

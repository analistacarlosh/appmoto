package br.com.chfmr.appmotos;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import br.com.chfmr.appmotos.avaliableApp.ui.AvaliableActivityFragment;
import br.com.chfmr.appmotos.commonApp.adapter.MainFragmentPagerAdapter;
import br.com.chfmr.appmotos.engagementApp.ui.EngagementActivityFragment;
import br.com.chfmr.appmotos.motorcycleCompany.ui.motocycleCompanyListFragment;
import br.com.chfmr.appmotos.motorcycleProfessional.ui.MotocycleProfessionalListFragment;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the ViewPager and set it' PagerAdapter
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager_main);
        //viewPager.setAdapter(new SampleFragmentPagerAdapter(getSupportFragmentManager(), MainActivity.this));
        if(viewPager != null){
            setupViewPager(viewPager);
        }

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs_main);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager){
        MainFragmentPagerAdapter adapter = new MainFragmentPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new motocycleCompanyListFragment(), "Cooperativas");
        adapter.addFragment(new MotocycleProfessionalListFragment(), "Moto Táxistas");
        //adapter.addFragment(new motocycleCompanyMapsFragment(), "Moto Táxistas");
        adapter.addFragment(new EngagementActivityFragment(), "Compartilhe");
        adapter.addFragment(new AvaliableActivityFragment(), "Avalie");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

package br.com.jortec.jorliano.transporte;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SwitchDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import br.com.jortec.jorliano.transporte.adapter.TabsAdapter;
import br.com.jortec.jorliano.transporte.extras.SlidingTabLayout;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Drawer.Result navegadorDrawer;
    private AccountHeader.Result accountHeader;
    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;
    int itemDrawerSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        toolbar.setTitle(R.string.tituloPrincipal);
        setSupportActionBar(toolbar);

        //TABS
        viewPager = (ViewPager) findViewById(R.id.vp_tabs);
        viewPager.setAdapter(new TabsAdapter(getSupportFragmentManager(),this));

        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.tbs_layout);
        slidingTabLayout.setSelectedIndicatorColors(getResources().getColor(android.R.color.white));
        //slidingTabLayout.setBackgroundColor(getResources().getColor(android.R.color.white));
        slidingTabLayout.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                navegadorDrawer.setSelection(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
        slidingTabLayout.setViewPager(viewPager);


        // NAVIGATION DRAWER
        // HEADER
        accountHeader = new AccountHeader()
                .withActivity(this)
                .withCompactStyle(false)
                .withSavedInstance(savedInstanceState)
                .withThreeSmallProfileImages(true)
                        //.withHeaderBackground(R.drawable.tema)
                .withHeaderBackground(android.R.drawable.screen_background_dark_transparent)
                .addProfiles(
                        new ProfileDrawerItem().withName("Jorliano").withEmail("jorliano@gmail.com").withIcon(getResources().getDrawable(R.drawable.sandeiro))
                )
                .build();

        // BODY
        navegadorDrawer = new Drawer()
                .withActivity(this)
                .withToolbar(toolbar)
                .withDisplayBelowToolbar(false)
                .withActionBarDrawerToggleAnimated(true)
                .withDrawerGravity(Gravity.START)
                .withSavedInstance(savedInstanceState)
                .withActionBarDrawerToggle(true)
                .withAccountHeader(accountHeader)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.drawer_item_transporte),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_peças),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_configurações),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_sobre),
                        new DividerDrawerItem(),
                        new SwitchDrawerItem().withName(R.string.drawer_item_notificacao).withChecked(true)

                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem iDrawerItem) {
                        itemDrawerSelected = i;
                        viewPager.setCurrentItem(i);
                    }
                }).build();
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

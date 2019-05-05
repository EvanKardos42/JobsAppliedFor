package com.example.jobsappliedfor;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.jobsappliedfor.Fragments.FragmentMain;

public class MainActivity extends AppCompatActivity {
    FragmentMain frag;
    String toolbarTitle ="Companies";
    Toolbar tb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tb = findViewById(R.id.my_toolbar);
        tb.setTitle(toolbarTitle);
        setSupportActionBar(tb);

        // fragments implimentation code
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        frag = new FragmentMain();

        transaction.add(R.id.content_frame,frag).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.app_bar_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_get_all_applied:
                frag.sortTheList(FragmentMain.Menu_Select.APPLIED);
                break;
            case R.id.menu_sory_by_letter:
                frag.sortTheList(FragmentMain.Menu_Select.FIRST_LETTER);
                break;
            case R.id.menu_get_all_not_applied:
                frag.sortTheList(FragmentMain.Menu_Select.NOT_APPLIED);
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}

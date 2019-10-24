package com.example.dagger2demo.ui.Main;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import com.example.dagger2demo.BaseActivity;
import com.example.dagger2demo.R;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";
    private DrawerLayout drawableLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawableLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        init();

    }


   private void init(){
       NavController controller = Navigation.findNavController(this, R.id.nav_host_fragment);
       NavigationUI.setupActionBarWithNavController(this, controller, drawableLayout);
       NavigationUI.setupWithNavController(navigationView, controller);
       navigationView.setNavigationItemSelectedListener(this);
   }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout: {
                storedData.deleteUser();
                return true;
            }
            case android.R.id.home:
                if(drawableLayout.isDrawerOpen(GravityCompat.START)){
                    drawableLayout.closeDrawer(GravityCompat.START);
                    return true;
                }else{
                    return false;
                }

            default:
                return super.onOptionsItemSelected(item);
        }
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()){
            case R.id.nav_profile:
                NavOptions navOptions = new NavOptions.Builder().setPopUpTo(R.id.main, true).build();
                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.profile_screen, null, navOptions);
                break;
            case R.id.nav_post:
                if(isValidDestination(R.id.post_screen)){
                    Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.post_screen);
                }
                break;
        }
        menuItem.setCheckable(true);
        drawableLayout.closeDrawer(GravityCompat.START);
        return true;
    }



    private boolean isValidDestination(int destination){
        return destination != Navigation.findNavController(this, R.id.nav_host_fragment).getCurrentDestination().getId();
    }



    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(Navigation.findNavController(this, R.id.nav_host_fragment), drawableLayout);
    }
}

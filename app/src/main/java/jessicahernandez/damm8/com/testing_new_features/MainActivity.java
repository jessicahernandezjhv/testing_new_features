package jessicahernandez.damm8.com.testing_new_features;

import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements FragmentPlatos.OnFragmentInteractionListener,
        FragmentHacerReserva.OnFragmentInteractionListener, FragmentVerReservas.OnFragmentInteractionListener, FragmentWelcome.OnFragmentInteractionListener {
    Toolbar toolbar;
    FragmentWelcome welcomeScreen;
    FragmentPlatos platosMenu;
    FragmentHacerReserva hacerReserva;
    FragmentVerReservas verReservas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        welcomeScreen = new FragmentWelcome();
        platosMenu = new FragmentPlatos();
        hacerReserva = new FragmentHacerReserva();
        verReservas = new FragmentVerReservas();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragments, welcomeScreen).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (item.getItemId()){
            case R.id.menuPlatosID:
                transaction.replace(R.id.contenedorFragments, platosMenu);
                break;
            case R.id.menuHacerReservaID:
                transaction.replace(R.id.contenedorFragments, hacerReserva);
                break;
            case R.id.menuVerReservaID:
                transaction.replace(R.id.contenedorFragments, verReservas);
                break;
        }
        transaction.commit();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

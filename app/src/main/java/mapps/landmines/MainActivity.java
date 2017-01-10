package mapps.landmines;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import mapps.landmines.db.MinesDataSource;
import mapps.landmines.model.DetailMineModel;
import mapps.landmines.model.MineModel;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    private static final int MENU_ITEM_LOGOUT = 1001;
    public static final String MINE_ID = "MINE_ID";
    private CoordinatorLayout coordinatorLayout;

    private List<MineModel> newMineModel;

    MineListAdapter adapter;
    ListView lv;

    MinesDataSource datasource;
    SearchView searchView;

    private GlobalVariables appContext;

    private class GetDBTask extends AsyncTask<String, Void, List<MineModel>> {

        @Override
        protected void onPreExecute() {
            datasource = new MinesDataSource(MainActivity.this);
            datasource.open();

        }


        @Override
        protected List<MineModel> doInBackground(String... params) {

            Log.d(TAG, " ********* doInBackground: ");
            newMineModel = datasource.findAll();
            return newMineModel;

        }

        @Override
        protected void onPostExecute(List<MineModel> result) {

            newMineModel = result;
            adapter = new MineListAdapter(
                    MainActivity.this, R.layout.list_item, newMineModel);
            lv = (ListView) findViewById(R.id.listView);
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);

                    MineModel mineModel = newMineModel.get(position);
                    DetailMineModel detail = getDetail(mineModel.getMineId());

                    // pass parceable MineModel to DetailActivity
                    //intent.putExtra(".model.MineModel", mineModel);

                    intent.putExtra(".model.DetailMineModel", detail);

                    startActivity(intent);
                }
            });

        }

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        datasource.close();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        appContext = (GlobalVariables) getApplicationContext();

        if (newMineModel == null) {
            Log.d(TAG, " newMineModel == null");
            new GetDBTask().execute();

        }else{
            Log.d(TAG, " newMineModel not null");
            newMineModel = appContext.getNewMineModel();
        }

        handleIntent(getIntent());

    }

    private DetailMineModel getDetail(String id) {
        return datasource.findMineWithID(id);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }


    private List<MineModel> handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search your data somehow

            // else cancelled search
            //else return null;
        }

        return null;
    }


    @Override
    protected void onResume() {
        super.onResume();
//        datasource.open();
    }

    @Override
    protected void onPause() {
        super.onPause();
 //       datasource.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        final MenuItem searchItem = menu.findItem(R.id.search);
        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        //searchView.setIconified(true);

        //*** setOnQueryTextFocusChangeListener ***
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {

                    // handles up arrow after search to go back to list all mines
                    // reset listview to all
                    adapter.filter("");
                    lv.invalidate();
                }
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d(TAG, "query string=" + query);
                adapter.filter(query.toString().trim());
                lv.invalidate();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String searchQuery) {

                return true;
            }
        });


        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {



//                adapter.filter("");
//                lv.invalidate();
//
//                Toast t = Toast.makeText(MainActivity.this, "close", Toast.LENGTH_SHORT);
//                t.show();

                return false;
            }
        });

        MenuItemCompat.setOnActionExpandListener(searchItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // Do something when collapsed
                Toast t = Toast.makeText(MainActivity.this, "collapse", Toast.LENGTH_SHORT);
                return true;  // Return true to collapse action view
            }

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                Toast t = Toast.makeText(MainActivity.this, "expand", Toast.LENGTH_SHORT);
                // Do something when expanded
                return true;  // Return true to expand action view
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {

            // list all landmines when click on listview icon on toolbar
            case R.id.action_list_all:
                searchView.setQuery("", false);
                searchView.clearFocus();
                adapter.filter("");
                lv.invalidate();
                return true;

//            case R.id.action_settings:
//                Snackbar.make(coordinatorLayout,
//                        "You selected settings", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                return true;

            case R.id.action_about:
                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                return true;

            case MENU_ITEM_LOGOUT:
                Snackbar.make(coordinatorLayout,
                        "You selected Logout", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);

        if (newMineModel != null && appContext != null )
            appContext.setNewMineModel(newMineModel);
    }

}

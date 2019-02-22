package www.prankapp.suyash.grupeeassignment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private ImageButton crossbutton;
    private ImageButton tickbuttton;
    private String previousUrl;
    private ProgressBar progressBar;
    private DatabaseHandler databaseHandler;
    private boolean tickPressed = false;
    private String url = "https://dog.ceo/api/breeds/image/random";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHandler = new DatabaseHandler(this);
        imageView = findViewById(R.id.imageviewdog);
        crossbutton = findViewById(R.id.buttoncross);
        tickbuttton = findViewById(R.id.buttonlike);
        progressBar = findViewById(R.id.progresscircular);
        imageView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        parseData();

        crossbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                crossbutton.setBackgroundResource(R.drawable.redcross);
                parseData();
            }
        });

        tickbuttton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                tickbuttton.setBackgroundResource(R.drawable.greentick);
                tickPressed = true;
                parseData();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.viewlist) {
            startActivity(new Intent(this, LikedProfiles.class));
        }
        return super.onOptionsItemSelected(item);
    }

    private void parseData() {

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    String status = response.getString("status");
                    String imageUrl = response.getString("message");
                    Picasso.get().load(imageUrl).into(imageView);
                    progressBar.setVisibility(View.GONE);
                    imageView.setVisibility(View.VISIBLE);
                    crossbutton.setBackgroundResource(R.drawable.blackcross);
                    tickbuttton.setBackgroundResource(R.drawable.blacktick);

                    if (tickPressed) {
                        String date = getDate();
                        boolean b = databaseHandler.insertDateAndUrl(date, previousUrl);
                        tickPressed = false;
                    }

                    previousUrl = imageUrl;

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);

    }


    private String getDate() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss");
        String formattedDate = df.format(c.getTime());
        return formattedDate;

    }

}

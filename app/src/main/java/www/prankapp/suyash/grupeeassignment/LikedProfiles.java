package www.prankapp.suyash.grupeeassignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class LikedProfiles extends AppCompatActivity {
    private LikeProfileAdapter likeProfileAdapter;
    private RecyclerView recyclerView;
    private DatabaseHandler db;
    private ArrayList<LikeProfileModel> dogsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DatabaseHandler(this);
        setContentView(R.layout.activity_liked_profiles);
        recyclerView = findViewById(R.id.recyclerListView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        getDogsData();
    }

    public void getDogsData() {
        ArrayList<String> arrayList = db.returnDate();
        ArrayList<String> arrayList2 = db.returnPicData();
        dogsList = Parser.parseAllDogs(arrayList, arrayList2);
        likeProfileAdapter = new LikeProfileAdapter(dogsList);
        recyclerView.setAdapter(likeProfileAdapter);

    }
}

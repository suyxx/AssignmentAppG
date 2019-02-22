package www.prankapp.suyash.grupeeassignment;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LikeProfileAdapter extends RecyclerView.Adapter<LikeProfileAdapter.MyViewHolder> {

    private ArrayList<LikeProfileModel> totalDogs = new ArrayList<>();

    public LikeProfileAdapter(ArrayList<LikeProfileModel> arrayList) {
        totalDogs = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View myView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recy_list_item, viewGroup, false);
        return new MyViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        LikeProfileModel model = totalDogs.get(i);
        Picasso.get().load(model.getImageUrl()).into(myViewHolder.imageView);
        myViewHolder.textView.setText(model.getDate());
    }

    @Override
    public int getItemCount() {
        return totalDogs.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;

        public MyViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.listimageview);
            textView = view.findViewById(R.id.date);
        }
    }

}

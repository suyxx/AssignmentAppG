package www.prankapp.suyash.grupeeassignment;

import java.util.ArrayList;

public class Parser {

    public static ArrayList<LikeProfileModel> parseAllDogs(ArrayList<String> datelist, ArrayList<String> dogsList) {
        ArrayList<LikeProfileModel> dogsModelArrayList = new ArrayList<>();

        for (int i = 0; i < datelist.size(); i++) {
            LikeProfileModel likeProfileModel = new LikeProfileModel();
            String date = datelist.get(i);
            String dogimage = dogsList.get(i);
            likeProfileModel.setDate(date);
            likeProfileModel.setImageUrl(dogimage);
            dogsModelArrayList.add(likeProfileModel);
        }
        return dogsModelArrayList;
    }
}

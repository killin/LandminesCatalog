package mapps.landmines.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MineModel implements Parcelable {

    private String mineId;
    private String name;
    private String type;
    private String countryOrigin;
    private String countriesUsed;
    private String imageFilename;
    private String description = "test activity_description";

    private String assemblies;
    private String methodOfOperation;
    private String structure;
    private String markings;
    private String alert;


    private boolean isFavorite = false;


    public MineModel() {
    }
    protected MineModel(Parcel in) {
        mineId = in.readString();
        name = in.readString();
        type = in.readString();
        countryOrigin = in.readString();
        countriesUsed = in.readString();
        imageFilename = in.readString();
    }

//    public MineModel(String mineId, String name, String type, String origin, String used, String imageFilename) {
//        this.mineId = mineId;
//        this.name = name;
//        this.type = type;
//        this.countryOrigin = origin;
//        this.countriesUsed = used;
//        this.imageFilename = imageFilename;
//    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mineId);
        dest.writeString(name);
        dest.writeString(type);
        dest.writeString(countryOrigin);
        dest.writeString(countriesUsed);
        dest.writeString(imageFilename);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MineModel> CREATOR = new Creator<MineModel>() {
        @Override
        public MineModel createFromParcel(Parcel source) {
            return new MineModel(source);
        }

        @Override
        public MineModel[] newArray(int size) {
            return new MineModel[size];
        }
    };


    public boolean getIsFavorite() {
        return isFavorite;
    }
    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public void toggleFavorite() {
        isFavorite = !isFavorite;
    }

    public void setMineId(String mineId) {
        this.mineId = mineId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCountryOrigin(String countryOrigin) {
        this.countryOrigin = countryOrigin;
    }

    public void setCountriesUsed(String countriesUsed) {
        this.countriesUsed = countriesUsed;
    }

    public void setImageFilename(String imageFilename) {
        this.imageFilename = imageFilename;
    }

    public void setDescription(String type) {
        this.description = description;
    }

    public void setAssemblies(String assemblies) {
        this.assemblies = assemblies;
    }

    public void setMethodOfOperation(String methodOfOperation) {
        this.methodOfOperation = methodOfOperation;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public void setMarkings(String markings) {
        this.markings = markings;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }


    public String getType() {
        return type;
    }



    public String getCountryOrigin() {
        return countryOrigin;
    }

    public String getCountriesUsed() {
        return countriesUsed;
    }

    public String getMineId() {
        return mineId;
    }

    public String getName() {
        return name;
    }


    public String getImageFilename() {
        return imageFilename;
    }

    public String getDescription() {
        return description;
    }



}

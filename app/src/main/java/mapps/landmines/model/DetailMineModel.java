package mapps.landmines.model;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by RK on 10/3/2016.
 */
public class DetailMineModel implements Parcelable{

    private String mineId;
    private String name;
    private String type;
    private String countryOrigin;
    private String countriesUsed;
    private String imageFilename;
    private String description;

    private String assemblies;
    private String methodOfOperation;
    private String structure;
    private String markings;
    private String alert;

    private String width;
    private String height;
    private String weight;
    private String metallicWeight;
    private String explosiveWeight;
    private String fragRange;
    private String chargeWeight;
    private String componentMaterials;
    private String caseMaterials;
    private String detectability;
    private String explosive;
    private String transport;
    private String hazard;
    private String images;
    private String neutralization;
    private String disarming;
    private String placement;




    public DetailMineModel() {
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mineId);
        dest.writeString(name);
        dest.writeString(type);
        dest.writeString(countryOrigin);
        dest.writeString(countriesUsed);
        dest.writeString(imageFilename);
        dest.writeString(description);
        dest.writeString(assemblies);
        dest.writeString(methodOfOperation);
        dest.writeString(structure);
        dest.writeString(markings);
        dest.writeString(alert);
        dest.writeString(width);
        dest.writeString(height);
        dest.writeString(weight);
        dest.writeString(metallicWeight);
        dest.writeString(explosiveWeight);
        dest.writeString(fragRange);
        dest.writeString(chargeWeight);
        dest.writeString(componentMaterials);
        dest.writeString(caseMaterials);
        dest.writeString(detectability);
        dest.writeString(explosive);
        dest.writeString(transport);
        dest.writeString(hazard);
        dest.writeString(images);
        dest.writeString(neutralization);
        dest.writeString(disarming);
        dest.writeString(placement);
    }
    protected DetailMineModel(Parcel in) {
        mineId = in.readString();
        name = in.readString();
        type = in.readString();
        countryOrigin = in.readString();
        countriesUsed = in.readString();
        imageFilename = in.readString();
        description = in.readString();
        assemblies = in.readString();
        methodOfOperation = in.readString();
        structure = in.readString();
        markings = in.readString();
        alert = in.readString();
        width = in.readString();
        height = in.readString();
        weight = in.readString();
        metallicWeight = in.readString();
        explosiveWeight = in.readString();
        fragRange = in.readString();
        chargeWeight = in.readString();
        componentMaterials = in.readString();
        caseMaterials = in.readString();
        detectability = in.readString();
        explosive = in.readString();
        transport = in.readString();
        hazard = in.readString();
        images = in.readString();
        neutralization = in.readString();
        disarming = in.readString();
        placement = in.readString();
    }

    public static final Creator<DetailMineModel> CREATOR = new Creator<DetailMineModel>() {
        @Override
        public DetailMineModel createFromParcel(Parcel in) {

            return new DetailMineModel(in);
        }

        @Override
        public DetailMineModel[] newArray(int size) {

            return new DetailMineModel[size];
        }
    };

    public String getMineId() {
        return mineId;
    }

    public void setMineId(String mineId) {
        this.mineId = mineId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCountryOrigin() {
        return countryOrigin;
    }

    public void setCountryOrigin(String countryOrigin) {
        this.countryOrigin = countryOrigin;
    }

    public String getCountriesUsed() {
        return countriesUsed;
    }

    public void setCountriesUsed(String countriesUsed) {
        this.countriesUsed = countriesUsed;
    }

    public String getImageFilename() {
        return imageFilename;
    }

    public void setImageFilename(String imageFilename) {
        this.imageFilename = imageFilename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssemblies() {
        return assemblies;
    }

    public void setAssemblies(String assemblies) {
        this.assemblies = assemblies;
    }

    public String getMethodOfOperation() {
        return methodOfOperation;
    }

    public void setMethodOfOperation(String methodOfOperation) {
        this.methodOfOperation = methodOfOperation;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public String getMarkings() {
        return markings;
    }

    public void setMarkings(String markings) {
        this.markings = markings;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getMetallicWeight() {
        return metallicWeight;
    }

    public void setMetallicWeight(String metallicWeight) {
        this.metallicWeight = metallicWeight;
    }

    public String getExplosiveWeight() {
        return explosiveWeight;
    }

    public void setExplosiveWeight(String explosiveWeight) {
        this.explosiveWeight = explosiveWeight;
    }

    public String getFragRange() {
        return fragRange;
    }

    public void setFragRange(String fragRange) {
        this.fragRange = fragRange;
    }

    public String getChargeWeight() {
        return chargeWeight;
    }

    public void setChargeWeight(String chargeWeight) {
        this.chargeWeight = chargeWeight;
    }

    public String getComponentMaterials() {
        return componentMaterials;
    }

    public void setComponentMaterials(String componentMaterials) {
        this.componentMaterials = componentMaterials;
    }

    public String getCaseMaterials() {
        return caseMaterials;
    }

    public void setCaseMaterials(String caseMaterials) {
        this.caseMaterials = caseMaterials;
    }

    public String getDetectability() {
        return detectability;
    }

    public void setDetectability(String detectability) {
        this.detectability = detectability;
    }

    public String getExplosive() {
        return explosive;
    }

    public void setExplosive(String explosive) {
        this.explosive = explosive;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public String getHazard() {
        return hazard;
    }

    public void setHazard(String hazard) {
        this.hazard = hazard;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getNeutralization() {
        return neutralization;
    }

    public void setNeutralization(String neutralization) {
        this.neutralization = neutralization;
    }

    public String getDisarming() {
        return disarming;
    }

    public void setDisarming(String disarming) {
        this.disarming = disarming;
    }

    public String getPlacement() {
        return placement;
    }

    public void setPlacement(String placement) {
        this.placement = placement;
    }



}

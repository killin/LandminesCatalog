package mapps.landmines;

import android.app.Application;

import java.util.List;

import mapps.landmines.model.DetailMineModel;
import mapps.landmines.model.MineModel;

/**
 * Created by RK on 11/17/2016.
 */
public class GlobalVariables extends Application {

    private List<MineModel> newMineModel = null;
    private DetailMineModel detailMineModel;

    private String mine_name;
    private String imageStr;

    public List<MineModel> getNewMineModel() {
        return newMineModel;
    }

    public void setNewMineModel(List<MineModel> newMineModel) {
        this.newMineModel = newMineModel;
    }

    public DetailMineModel getDetailMineModel() {
        return detailMineModel;
    }

    public void setDetailMineModel(DetailMineModel detailMineModel) {
        this.detailMineModel = detailMineModel;
    }

    public String getMine_name() {
        return mine_name;
    }

    public void setMine_name(String mine_name) {
        this.mine_name = mine_name;
    }

    public String getImageStr() {
        return imageStr;
    }

    public void setImageStr(String imageStr) {
        this.imageStr = imageStr;
    }
}

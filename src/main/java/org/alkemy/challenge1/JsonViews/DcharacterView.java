package org.alkemy.challenge1.JsonViews;

import org.alkemy.challenge1.domain.Dcharacter;

import java.util.ArrayList;
import java.util.List;

public class DcharacterView {
    private String name;
    private String picture;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public static List<DcharacterView> convertFromDcharacterList(List<Dcharacter> dchars) {
        List<DcharacterView> lstDcharView = new ArrayList<>();
        for(Dcharacter dchar: dchars) {
           DcharacterView dcharView = new DcharacterView(dchar.getName(), dchar.getPicture());
           lstDcharView.add(dcharView);
        }
        return lstDcharView;
    }

    public DcharacterView(String name, String picture) {
        this.name = name;
        this.picture = picture;
    }
}

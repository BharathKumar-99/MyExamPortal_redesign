package com.jrcreations.myexamportal.UI.Selection;

public class RowModel {
    String name,pic;

    public RowModel(String name, String pic) {
        this.name = name;
        this.pic = pic;
    }

    public RowModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}

package rs.fon.emobill.models;

public class Banner {
    private int banner_id;
    private String image;

    public Banner(int banner_id, String image) {
        this.banner_id = banner_id;
        this.image = image;
    }

    public int getBanner_id() {
        return banner_id;
    }

    public void setBanner_id(int banner_id) {
        this.banner_id = banner_id;
    }

    public String getImage() {
        return String.format("https://emobiletech.000webhostapp.com/img/banner//%s", image);
    }

    public void setImage(String image) {
        this.image = image;
    }
}

package rs.fon.emobill.models;

public class Orders {
    private int orderID;
    private int cartID;
    private String title;
    private String image;
    private String city;
    private int zip_code;
    private String address;
    private String phone;
    private int quantity;

    public Orders(int orderID, int cartID, String title, String image, String city, int zip_code, String address, String phone, int quantity) {
        this.orderID = orderID;
        this.cartID = cartID;
        this.title = title;
        this.image = image;
        this.city = city;
        this.zip_code = zip_code;
        this.address = address;
        this.phone = phone;
        this.quantity = quantity;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return String.format("https://emobiletech.000webhostapp.com/img/products/%s", image);
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZip_code() {
        return zip_code;
    }

    public void setZip_code(int zip_code) {
        this.zip_code = zip_code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return zip_code+" - "+city+", "+address+", "+phone;
    }
}

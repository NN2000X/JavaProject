package mall.entity;

// for simplicity in cart & order display, with no real storage in database, partly solve the data redundancy problem
public class ShortDetail {
    private Integer idproduct;
    private String name;
    private Float price;
    private Integer quantity;
    private Float subtotal;

    public ShortDetail(Integer idproduct, String name, Float price, Integer quantity) {
        this.idproduct = idproduct;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.subtotal = price * quantity;
    }

    public Integer getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(Integer idproduct) {
        this.idproduct = idproduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Float subtotal) {
        this.subtotal = subtotal;
    }
}

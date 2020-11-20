package mall.entity;

public class Product {
    private Integer idproduct;
    private String name;
    private Float price;
    private Integer stock;
    private Integer catalogid;
    private String description;

    public Product(Integer idproduct, String name, Float price, Integer stock, Integer catalogid, String description) {
        this.idproduct = idproduct;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.catalogid = catalogid;
        this.description = description;
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getCatalogid() {
        return catalogid;
    }

    public void setCatalogid(Integer catalogid) {
        this.catalogid = catalogid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

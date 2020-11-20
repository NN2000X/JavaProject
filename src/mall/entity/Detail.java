package mall.entity;

public class Detail {
    private Integer iddetail;
    private Integer orderid;
    private Integer productid;
    private Integer quantity;

    public Detail(Integer iddetail, Integer orderid, Integer productid, Integer quantity) {
        this.iddetail = iddetail;
        this.orderid = orderid;
        this.productid = productid;
        this.quantity = quantity;
    }

    public Integer getIddetail() {
        return iddetail;
    }

    public void setIddetail(Integer iddetail) {
        this.iddetail = iddetail;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

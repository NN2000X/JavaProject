package mall.entity;

public class Catalog {
    private Integer idcatalog;
    private String name;
    private Integer parentid;   // parentid for base catalogs is 0

    public Catalog(Integer idcatalog, String name, Integer parentid) {
        this.idcatalog = idcatalog;
        this.name = name;
        this.parentid = parentid;
    }

    public Integer getIdcatalog() {
        return idcatalog;
    }

    public void setIdcatalog(Integer idcatalog) {
        this.idcatalog = idcatalog;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }
}

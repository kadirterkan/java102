package patika.patikastore.entities;

public class Brand implements Comparable<Brand> {

    private Integer brandId;
    private String brandName;

    public Brand(Integer brandId, String brandName) {
        this.brandId = brandId;
        this.brandName = brandName;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @Override
    public int compareTo(Brand o) {
        return this.brandName.compareTo(o.getBrandName());
    }

    public void printBrand() {
        System.out.println(" - " + this.brandName);
    }
}

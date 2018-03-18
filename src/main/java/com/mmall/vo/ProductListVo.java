package com.mmall.vo;

import java.math.BigDecimal;

/**
 * product list value object
 *
 * @author facedamon facedamon
 * @create 2018/3/18
 */
public class ProductListVo {
    private Integer id;
    private Integer categoryId;
    private String name;
    private String imageHost;
    private String mainImage;
    private Integer status;
    private BigDecimal price;
    private String subtitle;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageHost() {
        return imageHost;
    }

    public void setImageHost(String imageHost) {
        this.imageHost = imageHost;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductListVo)) return false;

        ProductListVo that = (ProductListVo) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getCategoryId() != null ? !getCategoryId().equals(that.getCategoryId()) : that.getCategoryId() != null)
            return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        if (getImageHost() != null ? !getImageHost().equals(that.getImageHost()) : that.getImageHost() != null)
            return false;
        if (getMainImage() != null ? !getMainImage().equals(that.getMainImage()) : that.getMainImage() != null)
            return false;
        if (getStatus() != null ? !getStatus().equals(that.getStatus()) : that.getStatus() != null) return false;
        if (getPrice() != null ? !getPrice().equals(that.getPrice()) : that.getPrice() != null) return false;
        return getSubtitle() != null ? getSubtitle().equals(that.getSubtitle()) : that.getSubtitle() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getCategoryId() != null ? getCategoryId().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getImageHost() != null ? getImageHost().hashCode() : 0);
        result = 31 * result + (getMainImage() != null ? getMainImage().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        result = 31 * result + (getSubtitle() != null ? getSubtitle().hashCode() : 0);
        return result;
    }
}

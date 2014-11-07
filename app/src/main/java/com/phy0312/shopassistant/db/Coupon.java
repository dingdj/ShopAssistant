package com.phy0312.shopassistant.db;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table COUPON.
 */
public class Coupon {

    private Long id;
    private String CouponId;
    private String StoreId;
    private String Name;
    private Long StartTime;
    private Long EndTime;
    private Integer Category;
    private String Description;
    private java.util.Date CreateTime;

    public Coupon() {
    }

    public Coupon(Long id) {
        this.id = id;
    }

    public Coupon(Long id, String CouponId, String StoreId, String Name, Long StartTime, Long EndTime, Integer Category, String Description, java.util.Date CreateTime) {
        this.id = id;
        this.CouponId = CouponId;
        this.StoreId = StoreId;
        this.Name = Name;
        this.StartTime = StartTime;
        this.EndTime = EndTime;
        this.Category = Category;
        this.Description = Description;
        this.CreateTime = CreateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCouponId() {
        return CouponId;
    }

    public void setCouponId(String CouponId) {
        this.CouponId = CouponId;
    }

    public String getStoreId() {
        return StoreId;
    }

    public void setStoreId(String StoreId) {
        this.StoreId = StoreId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public Long getStartTime() {
        return StartTime;
    }

    public void setStartTime(Long StartTime) {
        this.StartTime = StartTime;
    }

    public Long getEndTime() {
        return EndTime;
    }

    public void setEndTime(Long EndTime) {
        this.EndTime = EndTime;
    }

    public Integer getCategory() {
        return Category;
    }

    public void setCategory(Integer Category) {
        this.Category = Category;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public java.util.Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(java.util.Date CreateTime) {
        this.CreateTime = CreateTime;
    }

}

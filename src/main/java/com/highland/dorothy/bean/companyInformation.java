package com.highland.dorothy.bean;


import com.highland.dorothy.bean.ENUM.Voltage;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class companyInformation {
    @Id
    @GeneratedValue
    private Integer id;
    //电压等级
    @Column
    @Enumerated(EnumType.STRING)
    private Voltage voltageLevel;
    //用户地址
    private String address;
    //用户代码
    private String code;
    //用电类别
    private String electricityCategory;
    //所属供电公司
    private String electricitySupplyCompany;
    //电力用户
    @Column(nullable = false)
    private String name;
    //负责人
    private String personInCharge;
    //备注
    private String remark;
    //序列号
    private String serial;
    //用户简称
    private String shortName;
    //富裕电量基数
    @Column(scale = 3)
    private BigDecimal surplusRadix;
    //交易品种
    private String tradingType;
    //变压器容量
    private Integer transformerCapacity;

    public companyInformation() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Voltage getVoltageLevel() {
        return voltageLevel;
    }

    public void setVoltageLevel(Voltage voltageLevel) {
        this.voltageLevel = voltageLevel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getElectricityCategory() {
        return electricityCategory;
    }

    public void setElectricityCategory(String electricityCategory) {
        this.electricityCategory = electricityCategory;
    }

    public String getElectricitySupplyCompany() {
        return electricitySupplyCompany;
    }

    public void setElectricitySupplyCompany(String electricitySupplyCompany) {
        this.electricitySupplyCompany = electricitySupplyCompany;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersonInCharge() {
        return personInCharge;
    }

    public void setPersonInCharge(String personInCharge) {
        this.personInCharge = personInCharge;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public BigDecimal getSurplusRadix() {
        return surplusRadix;
    }

    public void setSurplusRadix(BigDecimal surplusRadix) {
        this.surplusRadix = surplusRadix;
    }

    public String getTradingType() {
        return tradingType;
    }

    public void setTradingType(String tradingType) {
        this.tradingType = tradingType;
    }

    public Integer getTransformerCapacity() {
        return transformerCapacity;
    }

    public void setTransformerCapacity(Integer transformerCapacity) {
        this.transformerCapacity = transformerCapacity;
    }
}

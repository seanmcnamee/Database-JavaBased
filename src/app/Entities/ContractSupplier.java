package app.Entities;

import java.sql.Date;

public class ContractSupplier {
    private int contractNum;
    private Date dateOfContract;
    private int supplierNum;
    private String supplierAddress;
    private String supplierName;

    public ContractSupplier(int contractNum, Date dateOfContract, int supplierNum, String supplierAddress, String supplierName) {
        this.contractNum = contractNum;
        this.dateOfContract = dateOfContract;
        this.supplierNum = supplierNum;
        this.supplierAddress = supplierAddress;
        this.supplierName = supplierName;
    }

    public int getContractNum() {
        return contractNum;
    }

    public Date getDateOfContract() {
        return dateOfContract;
    }

    public int getSupplierNum() {
        return supplierNum;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public String getSupplierName() {
        return supplierName;
    }
}
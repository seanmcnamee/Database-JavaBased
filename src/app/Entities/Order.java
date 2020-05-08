package app.Entities;

import java.sql.Date;

public class Order {
    private int orderNum;
    private Date dateRequired;
    private Date dateCompleted;
    private int projectNum;
    private int contractNum;

    public Order(int orderNum, Date dateRequired, Date dateCompleted, int projectNum, int contractNum) {
        this.orderNum = orderNum;
        this.dateRequired = dateRequired;
        this.dateCompleted = dateCompleted;
        this.projectNum = projectNum;
        this.contractNum = contractNum;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public Date getDateRequired() {
        return dateRequired;
    }

    public Date getDateCompleted() {
        return dateCompleted;
    }

    public int getProjectNum() {
        return projectNum;
    }

    public int getContractNum() {
        return contractNum;
    }

}
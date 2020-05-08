package app.Entities;

public class Item {
    private int itemNum;
    private String itemDescription;

    public Item(int num, String description) {
        this.itemNum = num;
        this.itemDescription = description;
    }

    public int getItemNum() {
        return itemNum;
    }

    public String getItemDescription() {
        return itemDescription;
    }
    
}
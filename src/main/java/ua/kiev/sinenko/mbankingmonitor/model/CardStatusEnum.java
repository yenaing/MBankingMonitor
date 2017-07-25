package ua.kiev.sinenko.mbankingmonitor.model;

public enum CardStatusEnum {
    INACTIVE("Inactive"),
    OPEN("Open"),
    LOST("Lost"),
    STOLEN("Stolen");

    private String value;
    
    CardStatusEnum(String value){
       this.value=value; 
    }
    
    public String getValue(){
        return value;
    }
}

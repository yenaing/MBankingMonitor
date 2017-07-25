package ua.kiev.sinenko.mbankingmonitor.model;

public enum MBankingSmsState {
    FATAL_ERROR(-4,"Фатальная ошибка обработки. Повтор ещё будет"),
    ERROR(-3,"Ошибка обработки. Будет повтор."),
    PARSE_ERROR(-2,"Ошибка разбора"),
    NOT_SIGNED_PHONE_ERROR(-1,"Не найден номер телефона"),
    NEW(0,"SMS не обрабатывалось"),
    PROCESSED_INCOMMING(2,"SMS, сформированное Mbanking, обработано"),
    PROCESSED_ORIGINATED(1,"SMS, сформированное Worker, обработано"),
    PROCESSING(3,"SMS в состоянии обработки");
    
    private String description;
    private int value;

    MBankingSmsState(int value,String desc){
        this.value=value;
        this.description=desc;
    }
    
    public String getDescription(){
        return description;
    }
    
    public int getValue(){
        return value;
    }
    
}

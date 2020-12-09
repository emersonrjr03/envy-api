package br.com.egp.envy.enums;

public enum TransactionType {

    INCOME(1),
    OUTCOME(-1);

    private Integer code;

    TransactionType(Integer code){
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
    public static TransactionType toEnum(Integer code){
        for(TransactionType type : values()){
            if(type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }
}
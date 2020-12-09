package br.com.egp.envy.enums;

public enum EnvelopeType {

    GOAL(1),
    BUDGET(2);

    private Integer code;

    EnvelopeType(Integer code){
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
    public static EnvelopeType toEnum(Integer code){
        for(EnvelopeType type : values()){
            if(type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }
}

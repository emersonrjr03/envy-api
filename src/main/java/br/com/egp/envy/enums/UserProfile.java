package br.com.egp.envy.enums;

public enum UserProfile {

    ADMIN(1, "ROLE_ADMIN"),
    USER(2, "ROLE_USER");

    private int cod;
    private String descricao;

    private UserProfile(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static UserProfile toEnum(Integer cod) {
        if(cod == null) {
            return null;
        }
        for(UserProfile up : UserProfile.values()) {
            if(cod.equals(up.getCod()))
                return up;
        }
        throw new IllegalArgumentException("Id inválido " + cod);
    }
}

package cn.zpro.ware.entity;

/**
 * @author zhanggl
 */

public enum SexEum {

    MAN((short) 0, "男"),
    WOMAN((short) 1, "女");

    private Short value;
    private String desc;

    SexEum(Short value, String desc) {
        this.value = value;
        this.desc = desc;
    }
    public Short getValue() {
        return value;
    }
    public String getDesc() {
        return desc;
    }

    public static SexEum findSexEum(Short value){
        for (SexEum sexEum : SexEum.values()) {
            if(sexEum.getValue().equals(value)){
                return sexEum;
            }
        }
        return null;
    }
}

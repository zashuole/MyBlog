package com.blog.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.Getter;

@Getter
public enum ReportReasonEnum implements IEnum<String> {
    
    SPAM("spam", "垃圾广告"),
    ATTACK("attack", "恶意攻击"),
    PORN("porn", "色情内容"),
    POLITICAL("political", "政治敏感"),
    OTHER("other", "其他原因");
    
    private final String value;
    private final String desc;
    
    ReportReasonEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }
    
    @Override
    public String getValue() {
        return this.value;
    }
    
    /**
     * 根据value获取枚举
     */
    public static ReportReasonEnum getByValue(String value) {
        if (value == null) {
            return null;
        }
        for (ReportReasonEnum reason : values()) {
            if (reason.getValue().equals(value)) {
                return reason;
            }
        }
        return null;
    }
    
    /**
     * 获取描述
     */
    public static String getDesc(String value) {
        ReportReasonEnum reason = getByValue(value);
        return reason == null ? "" : reason.getDesc();
    }
    
    /**
     * 检查是否为有效的举报原因
     */
    public static boolean isValidReason(String value) {
        return getByValue(value) != null;
    }
}
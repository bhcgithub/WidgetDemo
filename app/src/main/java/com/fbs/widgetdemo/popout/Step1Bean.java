package com.fbs.widgetdemo.popout;

import java.io.Serializable;
import java.util.List;

/**
 * Description:
 * Date: Created by Caojing on 2019/8/20 20:15
 */
public class Step1Bean implements Serializable {

    /**
     * cardTypeName : 证件类型
     * cardType : [{"valueName":"身份证","valueCode":"1"}]
     */

    private String cardTypeName;
    private List<CardTypeBean> cardType;

    public String getCardTypeName() {
        return cardTypeName;
    }

    public void setCardTypeName(String cardTypeName) {
        this.cardTypeName = cardTypeName;
    }

    public List<CardTypeBean> getCardType() {
        return cardType;
    }

    public void setCardType(List<CardTypeBean> cardType) {
        this.cardType = cardType;
    }

    public static class CardTypeBean implements Serializable {
        /**
         * valueName : 身份证
         * valueCode : 1
         */

        private String valueName;
        private String valueCode;

        public String getValueName() {
            return valueName;
        }

        public void setValueName(String valueName) {
            this.valueName = valueName;
        }

        public String getValueCode() {
            return valueCode;
        }

        public void setValueCode(String valueCode) {
            this.valueCode = valueCode;
        }
    }
}

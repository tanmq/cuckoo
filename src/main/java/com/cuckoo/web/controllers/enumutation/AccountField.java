package com.cuckoo.web.controllers.enumutation;

/**
 * Created by tanmq on 2017/3/5.
 */
public enum AccountField {
    NAME(1),PHONE(2),EMAIL(3);

        private int value;

        AccountField(int value) {
            this.value = value;
        }

        public static AccountField valOf(int value) {
            switch (value) {
                case 1:
                    return NAME;
                case 2:
                    return PHONE;
                case 3:
                    return EMAIL;
                default:
                    return null;
            }
        }
}

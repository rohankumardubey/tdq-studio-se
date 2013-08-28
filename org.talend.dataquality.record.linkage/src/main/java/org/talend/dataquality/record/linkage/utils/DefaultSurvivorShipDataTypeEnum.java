// ============================================================================
//
// Copyright (C) 2006-2013 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.dataquality.record.linkage.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * created by HHB on 2013-8-23 Detailled comment
 * 
 */
public enum DefaultSurvivorShipDataTypeEnum {
    BOOLEAN(0, "Boolean", "Boolean"),
    DATE(1, "Date", "Date"),
    NUMBER(2, "Number", "Number"),
    STRING(3, "String", "String");

    private int index;

    private String value;

    private String componentValueName;

    DefaultSurvivorShipDataTypeEnum(int index, String value, String componentValueName) {
        this.index = index;
        this.value = value;
        this.componentValueName = componentValueName;
    }

    public int getIndex() {
        return this.index;
    }

    public String getValue() {
        return this.value;
    }

    public String getComponentValueName() {
        return this.componentValueName;
    }

    public static String[] getAllTypes() {
        List<String> list = new ArrayList<String>();
        for (DefaultSurvivorShipDataTypeEnum theType : values()) {
            list.add(theType.getValue());
        }
        return list.toArray(new String[list.size()]);
    }

    public static DefaultSurvivorShipDataTypeEnum getTypeByValue(String value) {
        for (DefaultSurvivorShipDataTypeEnum element : DefaultSurvivorShipDataTypeEnum.values()) {
            if (element.getValue().equalsIgnoreCase(value)) {
                return element;
            }
        }

        return null;
    }

    /**
     * 
     * 
     * @param index
     * @return null can not find this index
     */
    public static DefaultSurvivorShipDataTypeEnum getTypeByIndex(int index) {
        for (DefaultSurvivorShipDataTypeEnum element : DefaultSurvivorShipDataTypeEnum.values()) {
            if (element.getIndex() == index) {
                return element;
            }
        }

        return null;
    }
}
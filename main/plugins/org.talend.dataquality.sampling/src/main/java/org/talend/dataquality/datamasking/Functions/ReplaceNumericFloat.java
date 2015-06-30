// ============================================================================
//
// Copyright (C) 2006-2015 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.dataquality.datamasking.Functions;

/**
 * created by jgonzalez on 22 juin 2015. See ReplaceNumeric.
 *
 */
public class ReplaceNumericFloat extends ReplaceNumeric<Float> {

    @Override
    public Float generateMaskedRow(Float f) {
        if (f == null && keepNull) {
            return null;
        } else {
            super.init();
            if (f != null) {
                String str = f.toString();
                String res = str.replaceAll("\\d", param); //$NON-NLS-1$
                return Float.valueOf(res);
            } else {
                return 0.0f;
            }
        }
    }
}
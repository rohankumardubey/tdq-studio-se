// ============================================================================
//
// Copyright (C) 2006-2020 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.dq.dbms;

import org.talend.dataquality.PluginConstant;
import org.talend.utils.ProductVersion;

import orgomg.cwm.objectmodel.core.Expression;

public class SAPHanaDbmsLanguage extends DbmsLanguage {

    private static final String SAPHANA_IDENTIFIER_QUOTE = "\""; //$NON-NLS-1$

    /**
     * The Constructor of class
     */
    public SAPHanaDbmsLanguage() {
        super();
    }

    /**
     * @param dbmsType
     * @param dbVersion
     */
    public SAPHanaDbmsLanguage(String dbmsType, ProductVersion dbVersion) {
        super(dbmsType, dbVersion);
    }

    /**
     * @param dbmsType
     */
    public SAPHanaDbmsLanguage(String dbmsType) {
        super(dbmsType);
    }

    @Override
    public String getHardCodedQuoteIdentifier() {
        return this.SAPHANA_IDENTIFIER_QUOTE;
    }

    @Override
    public String charLength(String columnName) {
        return " LENGTH(" + columnName + ") "; //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public String extractRegularExpressionFunction(Expression expression, String regexp) {
        return "LIKE_REGEXPR"; //$NON-NLS-1$
    }

    @Override
    public String regexLike(String element, String regex) {
        String regularExpressionFunction = this.getRegularExpressionFunction();
        if (null == regularExpressionFunction || PluginConstant.EMPTY_STRING.equals(regularExpressionFunction)
                || existEmptyInParameter(element, regex)) {
            return null;
        }
        String functionNameSQL = element + " " + regularExpressionFunction + " " + regex;//$NON-NLS-1$ //$NON-NLS-2$

        return surroundWithSpaces(functionNameSQL);
    }

    @Override
    public String regexNotLike(String element, String regex) {
        String regularExpressionFunction = this.getRegularExpressionFunction();
        if (null == regularExpressionFunction || PluginConstant.EMPTY_STRING.equals(regularExpressionFunction)
                || existEmptyInParameter(element, regex)) {
            return null;
        }
        String functionNameSQL = element + " NOT " + regularExpressionFunction + " " + regex;//$NON-NLS-1$ //$NON-NLS-2$

        return surroundWithSpaces(functionNameSQL);
    }

}

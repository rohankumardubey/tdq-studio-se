// ============================================================================
//
// Copyright (C) 2006-2014 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.dataquality.record.linkage.constant;

import org.talend.dataquality.record.linkage.Messages;

/**
 * @author scorreia
 * 
 * The names of the available record matchers.
 */
public enum RecordMatcherType {
    simpleVSRMatcher(Messages.getString("RecordMatcherType.simpleVSRMatcher")), //$NON-NLS-1$
    T_SwooshAlgorithm(Messages.getString("RecordMatcherType.T_SwooshAlgorithm")); //$NON-NLS-1$

    private final String label;

    RecordMatcherType(String label) {
        this.label = label;
    }

    /**
     * Getter for label.
     * 
     * @return the label
     */
    public String getLabel() {
        return this.label;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return label;
    }

}

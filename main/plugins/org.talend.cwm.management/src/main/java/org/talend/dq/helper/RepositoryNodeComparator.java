// ============================================================================
//
// Copyright (C) 2006-2018 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.dq.helper;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

import org.apache.log4j.Logger;
import org.talend.repository.model.IRepositoryNode;

/**
 * comparator for IRepositoryNode.
 * 
 * DOC Administrator class global comment. Detailled comment
 */
public class RepositoryNodeComparator implements Comparator<IRepositoryNode> {

    protected static Logger log = Logger.getLogger(RepositoryNodeComparator.class);

    private boolean showRefreshDuration = true;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");

    public int compare(IRepositoryNode o1, IRepositoryNode o2) {

        if (o1 == null || o2 == null) {
            return 0;
        }

        long start = System.currentTimeMillis();
        Date date1 = new Date();
        if (showRefreshDuration) {
            log.error("getDisplayLabel() start: " + sdf.format(date1));
        }
        // ===========================
        String label1 = RepositoryNodeHelper.getDisplayName(o1);
        String label2 = RepositoryNodeHelper.getDisplayName(o2);
        // ===========================
        Date date2 = new Date();
        long end = System.currentTimeMillis();
        if (showRefreshDuration) {
            log.error("getDisplayLabel() end: " + sdf.format(date2));
            long duration = end - start;
            log.error("getDisplayLabel() duration: " + duration);
        }

        if ("".equals(label1) || "".equals(label2)) { //$NON-NLS-1$ //$NON-NLS-2$
            return 0;
        }

        start = System.currentTimeMillis();
        date1 = new Date();
        if (showRefreshDuration) {
            log.error("toUpperCase().compareTo() start: " + sdf.format(date1));
        }
        // ===========================
        int result = label1.toUpperCase().compareTo(label2.toUpperCase());
        // ===========================
        date2 = new Date();
        end = System.currentTimeMillis();
        if (showRefreshDuration) {
            log.error("toUpperCase().compareTo() end: " + sdf.format(date2));
            long duration = end - start;
            log.error("toUpperCase().compareTo() duration: " + duration);
        }

        return result;
        // return String.CASE_INSENSITIVE_ORDER.compare(label1, label2);
    }

}

// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.cwm.compare.ui.actions.provider;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.talend.cwm.compare.ui.actions.ReloadDatabaseAction;
import org.talend.dq.nodes.foldernode.IFolderNode;

/**
 * DOC rli class global comment. Detailled comment
 */
public class ReloadDatabaseProvider extends CommonActionProvider {

    private static final String RELOADDATABASE_MENUTEXT = "Reload database list";

    private static final String RELOADTABLES_MENUTEXT = "Reload table list";

    private static final String RELOADVIEWS_MENUTEXT = "Reload view list";

    private static final String RELOADCOLUMNS_MENUTEXT = "Reload column list";

    public ReloadDatabaseProvider() {
    }

    public void fillContextMenu(IMenuManager menu) {
        Object obj = ((TreeSelection) this.getContext().getSelection()).getFirstElement();
        String menuText = RELOADDATABASE_MENUTEXT;
        if (obj instanceof IFolderNode) {
            IFolderNode folderNode = (IFolderNode) obj;
            switch (folderNode.getFolderNodeType()) {
            case IFolderNode.TABLEFOLDER_NODE_TYPE:
                menuText = RELOADTABLES_MENUTEXT;
                break;
            case IFolderNode.VIEWFOLDER_NODE_TYPE:
                menuText = RELOADVIEWS_MENUTEXT;
                break;
            case IFolderNode.COLUMNFOLDER_NODE_TYPE:
                menuText = RELOADCOLUMNS_MENUTEXT;
                break;
            default:
            }
        }
        menu.add(new ReloadDatabaseAction(obj, menuText));
    }
}

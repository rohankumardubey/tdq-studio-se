// ============================================================================
//
// Copyright (C) 2006-2010 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.dataprofiler.core.ui.action.provider;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFolder;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.TreeSelection;
import org.talend.dataprofiler.core.service.AbstractSvnRepositoryService;
import org.talend.dataprofiler.core.service.GlobalServiceRegister;
import org.talend.dataprofiler.core.ui.action.actions.CreateConnectionAction;
import org.talend.resource.ResourceManager;
import org.talend.resource.ResourceService;

/**
 * @author rli
 * 
 */
public class NewConnectionActionProvider extends AbstractCommonActionProvider {

    protected static Logger log = Logger.getLogger(NewConnectionActionProvider.class);

    /**
     * 
     */
    public NewConnectionActionProvider() {
    }

    /**
     * Adds a submenu to the given menu with the name "New Component".
     */
    public void fillContextMenu(IMenuManager menu) {
        // MOD mzhao user readonly role on svn repository mode.
        if (!isShowMenu()) {
            return;
        }
        // MOD mzhao user readonly role on svn repository mode.
        AbstractSvnRepositoryService svnReposService = GlobalServiceRegister.getDefault().getSvnRepositoryService(
                AbstractSvnRepositoryService.class);
        if (svnReposService != null && svnReposService.isReadonly()) {
            return;
        }
        Object obj = ((TreeSelection) this.getContext().getSelection()).getFirstElement();

        if (obj instanceof IFolder) {
            IFolder folder = (IFolder) obj;

            if (ResourceService.isSubFolder(ResourceManager.getConnectionFolder(), folder)
                    || ResourceService.isSubFolder(ResourceManager.getMDMConnectionFolder(), folder)) {
                CreateConnectionAction createConnectionAction = new CreateConnectionAction(folder);
                menu.add(createConnectionAction);
                // menu.insertBefore("group.edit", createConnectionAction);
            }
        }
    }
}

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
package org.talend.dataprofiler.core.pattern;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.help.HelpSystem;
import org.eclipse.help.IContext;
import org.eclipse.help.IHelpResource;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.talend.dataprofiler.core.ImageLib;
import org.talend.dataprofiler.core.PluginConstant;
import org.talend.dataprofiler.help.HelpPlugin;
import org.talend.dataquality.domain.pattern.ExpressionType;

/**
 * DOC qzhang class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 1 2006-09-29 17:06:40Z nrousseau $
 * 
 */
public class CreatePatternAction extends Action {

    private IFolder folder;

    private ExpressionType type;

    /**
     * DOC qzhang AddSqlFileAction constructor comment.
     * 
     * @param folder
     * @param type
     */
    public CreatePatternAction(IFolder folder, ExpressionType type) {
        switch (type) {
        case SQL_LIKE:
            setText("Create a new sql pattern");
            break;
        default:
            setText("Create a new regular pattern");
            break;
        }
        setImageDescriptor(ImageLib.getImageDescriptor(ImageLib.PATTERN_REG));
        this.folder = folder;
        this.type = type;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        CreatePatternWizard fileWizard = new CreatePatternWizard(folder, type);
        IContext context = HelpSystem.getContext(HelpPlugin.PATTERN_CONTEXT_HELP_ID);
        IHelpResource[] relatedTopics = context.getRelatedTopics();
        String href = relatedTopics[0].getHref();
        switch (type) {
        case SQL_LIKE:
            href = relatedTopics[1].getHref();
            break;
        default:
            break;
        }
        WizardDialog dialog = new CheatSheetWizardDialog(Display.getDefault().getActiveShell(), fileWizard, href);
        fileWizard.setWindowTitle(getText());
        if (WizardDialog.OK == dialog.open()) {
            try {
                folder.refreshLocal(IResource.DEPTH_INFINITE, null);
                IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(fileWizard.getLocation());
                PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(new FileEditorInput(file),
                        PluginConstant.PATTERN_EDITOR);
            } catch (CoreException e) {
                e.printStackTrace();
            }
        }
    }
}

// ============================================================================
//
// Copyright (C) 2006-2009 Talend Inc. - www.talend.com
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

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.compare.diff.metamodel.AbstractDiffExtension;
import org.eclipse.emf.compare.diff.metamodel.AddModelElement;
import org.eclipse.emf.compare.diff.metamodel.DiffGroup;
import org.eclipse.emf.compare.diff.metamodel.RemoveModelElement;
import org.eclipse.emf.compare.util.AdapterUtils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import orgomg.cwm.objectmodel.core.ModelElement;

/**
 * 
 * DOC mzhao 2009-03-10 class global comment. Detailled comment
 */
public class CompareModelStructureLabelProvider extends LabelProvider {

    /**
     * We use this generic label provider, but we want to customize some aspects that's why we choose to aggregate it.
     */
    /* package */AdapterFactoryLabelProvider adapterProvider;

    /**
     * Default constructor.
     */
    public CompareModelStructureLabelProvider() {
        adapterProvider = new AdapterFactoryLabelProvider(AdapterUtils.getAdapterFactory());

    }

    /**
     * Returns the platform icon for a given {@link IFile}. If not an {@link IFile}, delegates to the
     * {@link AdapterFactoryLabelProvider} to get the {@link Image}.
     * 
     * @param object Object to get the {@link Image} for.
     * @return The platform icon for the given object.
     * @see AdapterFactoryLabelProvider#getImage(Object)
     */
    @Override
    public Image getImage(Object object) {
        Image image = null;
        if (object instanceof AbstractDiffExtension) {
            image = (Image) ((AbstractDiffExtension) object).getImage();
        }
        if (object instanceof IFile) {
            image = PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FILE);
        } else {
            if (image == null) {
                image = adapterProvider.getImage(object);
            }
        }
        return image;
    }

    /**
     * Returns the name of the given {@link IFile}, delegates to {@link AdapterFactoryLabelProvider#getText(Object)} if
     * not an {@link IFile}. MOD mzhao 2009-03-10 adapte for upper panel customized displaying items.
     * 
     * @param object Object we seek the name for.
     * @return The name of the given object.
     * @see AdapterFactoryLabelProvider#getText(Object)
     */
    @Override
    public String getText(Object object) {
        String text = null;
        if (object instanceof AbstractDiffExtension) {
            text = ((AbstractDiffExtension) object).getText();
        } else {
            if (object instanceof IFile) {
                text = ((IFile) object).getName();
            } else {
                if (object instanceof DiffGroup) {
                    DiffGroup diffGroup = (DiffGroup) object;
                    int subChanges = diffGroup.getSubchanges();
                    text = subChanges + " change(s) in model";
                    if (diffGroup.getLeftParent() != null && diffGroup.getLeftParent() instanceof ModelElement) {
                        text += ":\"" + ((ModelElement) diffGroup.getLeftParent()).getName() + "\"";
                    }
                } else if (object instanceof AddModelElement) {

                    AddModelElement addModelElement = (AddModelElement) object;

                    String modelName = "";
                    EObject leftElement = addModelElement.getRightElement();
                    if (leftElement != null && leftElement instanceof ModelElement) {
                        modelName = ((ModelElement) leftElement).getName();
                    }
                    text = "model \"" + modelName + "\" has been added";

                } else if (object instanceof RemoveModelElement) {
                    RemoveModelElement removeModelElement = (RemoveModelElement) object;
                    String modelName = "";
                    EObject leftElement = removeModelElement.getLeftElement();
                    if (leftElement != null && leftElement instanceof ModelElement) {
                        modelName = ((ModelElement) leftElement).getName();
                    }
                    EObject rightElement = removeModelElement.getLeftElement();
                    if (rightElement != null && rightElement instanceof ModelElement) {
                        modelName = ((ModelElement) rightElement).getName();
                    }
                    text = "model \"" + modelName + "\" has been removed";
                } else {
                    text = object.toString();
                }

            }
        }
        return text;
    }
}

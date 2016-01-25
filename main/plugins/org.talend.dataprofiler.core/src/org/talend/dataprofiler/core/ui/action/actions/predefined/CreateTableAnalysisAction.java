// ============================================================================
//
// Copyright (C) 2006-2016 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.dataprofiler.core.ui.action.actions.predefined;

import org.eclipse.jface.wizard.WizardDialog;
import org.talend.dataprofiler.core.i18n.internal.DefaultMessagesImpl;
import org.talend.dataprofiler.core.model.TableIndicator;
import org.talend.dataprofiler.core.ui.action.AbstractPredefinedTableAnalysisAction;
import org.talend.dq.nodes.indicator.type.IndicatorEnum;

/**
 * DOC xqliu class global comment. Detailled comment
 */
public class CreateTableAnalysisAction extends AbstractPredefinedTableAnalysisAction {

    public CreateTableAnalysisAction() {
        super(DefaultMessagesImpl.getString("CreateTableAnalysisAction.tableAnalysis"), null); //$NON-NLS-1$
    }

    @Override
    protected TableIndicator[] getPredefinedTableIndicator() {
        IndicatorEnum[] allwedEnumes = new IndicatorEnum[1];
        allwedEnumes[0] = IndicatorEnum.WhereRuleIndicatorEnum;

        return composePredefinedTableIndicator(allwedEnumes);
    }

    @Override
    protected WizardDialog getPredefinedDialog() {
        return null;
    }

    @Override
    protected boolean isAllowed() {
        return true;
    }

    @Override
    protected boolean preDo() {
        return true;
    }

}

package net.sourceforge.sqlexplorer.rcp;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.WorkbenchAdvisor;


/**
 * Main entry point used to run SQL Explorer as standalone client.
 * 
 * @author Davy Vanherbergen
 */
public class SQLExplorerApplication {

        
    /**
     * Build workbench and launch it..
     * 
     * @see org.eclipse.core.runtime.IPlatformRunnable#run(java.lang.Object)
     */
    public Object run(Object args) throws Exception {

        Display display = null;
        
        try {
            display = PlatformUI.createDisplay();
            WorkbenchAdvisor advisor = new SQLExplorerWorkbenchAdvisor();
            int rc = PlatformUI.createAndRunWorkbench(display, advisor);            
            return (rc == PlatformUI.RETURN_RESTART ? Integer.valueOf(23) : Integer.valueOf(0));
    
        } finally {
            
            if (display != null) {
                display.dispose();
            }
        }
        
    }

}

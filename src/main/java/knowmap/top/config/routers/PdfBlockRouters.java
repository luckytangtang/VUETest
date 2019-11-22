package knowmap.top.config.routers;

import com.jfinal.config.Routes;
import knowmap.top.managers.pdfBlock.controller.PdfBlockController;

public class PdfBlockRouters extends Routes {
    @Override
    public void config() {
        super.add("/pdfBlocks", PdfBlockController.class);
    }
}

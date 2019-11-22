package knowmap.top.config.routers;

import com.jfinal.config.Routes;
import knowmap.top.managers.pdfFile.controller.PdfFileController;

public class PdfFileRouters extends Routes {
    @Override
    public void config() {
        super.add("/pdfFiles", PdfFileController.class);
    }
}

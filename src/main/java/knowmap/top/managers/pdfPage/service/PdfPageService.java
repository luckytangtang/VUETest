package knowmap.top.managers.pdfPage.service;

import knowmap.top.managers.pdfPage.entity.PdfPage;
import knowmap.top.managers.pdfPage.queryParam.PdfPageQuery;

import java.util.List;

public interface PdfPageService {

    List<PdfPage> getPagesByBlobId(PdfPageQuery pdfPageQuery);

    PdfPage getPagesById(Long id);

    Long savePdfPage(PdfPage pdfPage);
}

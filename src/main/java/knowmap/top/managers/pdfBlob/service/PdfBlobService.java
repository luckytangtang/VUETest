package knowmap.top.managers.pdfBlob.service;

import knowmap.top.managers.pdfBlob.entity.PdfBlob;
import knowmap.top.managers.pdfBlob.queryParam.PdfBlobQuery;

public interface PdfBlobService {
    Long save(PdfBlob pdfBlob);

    PdfBlob queryById(PdfBlobQuery query);

    PdfBlob queryByChecksum(PdfBlobQuery query);
}

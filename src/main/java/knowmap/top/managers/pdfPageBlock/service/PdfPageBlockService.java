package knowmap.top.managers.pdfPageBlock.service;
import knowmap.top.managers.pdfPageBlock.entity.PdfPageBlock;

import java.util.List;

public interface PdfPageBlockService {

   boolean savePdfPageBlock(PdfPageBlock pdfPageBlock);


   PdfPageBlock getPdfPageBlock(Long pageId,Long pdfBlockId);

}

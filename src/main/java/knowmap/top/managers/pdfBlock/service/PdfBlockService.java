package knowmap.top.managers.pdfBlock.service;

import knowmap.top.managers.pdfBlock.entity.PdfBlock;
import knowmap.top.managers.pdfBlock.queryParam.BlockQuery;

import java.util.List;

public interface PdfBlockService {

    List<PdfBlock> getBlocksByPageId(Long pageId);

    PdfBlock getBlockById(Long id);

    Long savePdfBlock(PdfBlock pdfBlock);

    List<PdfBlock> getBlocksByPage(BlockQuery blockQuery);

}

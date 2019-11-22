package knowmap.top.managers.pdfPageBlock.service;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import knowmap.top.managers.pdfPageBlock.entity.PdfPageBlock;
import knowmap.top.utils.RecordUtils;

public class PdfPageBlockServiceImpl implements PdfPageBlockService {
    @Override
    public boolean savePdfPageBlock(PdfPageBlock pdfPageBlock) {
        Record record= RecordUtils.getInstance().objToRecord(pdfPageBlock,PdfPageBlock.class);
        return Db.save("pdf_page_block",record);
    }

    @Override
    public PdfPageBlock getPdfPageBlock(Long pageId, Long pdfBlockId) {
        Record record=Db.findFirst(Db.getSql("pdfPageBlock.fun001.01"),pageId,pdfBlockId);
        PdfPageBlock pdfPageBlock=RecordUtils.getInstance().recordToObj(record,PdfPageBlock.class);
        return pdfPageBlock;
    }
}

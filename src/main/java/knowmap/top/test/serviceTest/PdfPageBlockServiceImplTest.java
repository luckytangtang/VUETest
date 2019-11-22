package knowmap.top.test.serviceTest;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import knowmap.top.managers.pdfPageBlock.entity.PdfPageBlock;
import knowmap.top.utils.RecordUtils;
import org.junit.Test;

import static org.junit.Assert.*;

public class PdfPageBlockServiceImplTest extends BaseTest {

    @Test
    public void savePdfPageBlock() {
        PdfPageBlock pdfPageBlock=new PdfPageBlock();
        pdfPageBlock.setAreaVersion(4);
        pdfPageBlock.setPageId(2L);
        pdfPageBlock.setPdfBlockId(1L);
        Record record= RecordUtils.getInstance().objToRecord(pdfPageBlock,PdfPageBlock.class);
        Db.save("pdf_page_block",record);
    }

    @Test
    public void getPdfPageBlock() {
        System.out.println(Db.findFirst(Db.getSql("pdfPageBlock.fun001.01"),2L,1L));
    }
}
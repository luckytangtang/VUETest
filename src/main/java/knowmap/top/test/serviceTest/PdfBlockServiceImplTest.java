package knowmap.top.test.serviceTest;


import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import knowmap.top.managers.pdfBlock.entity.PdfBlock;
import knowmap.top.utils.RecordUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class PdfBlockServiceImplTest extends BaseTest{

    @Test
    public void getBlocksByPageId() {
        List<PdfBlock> blocks=new ArrayList<>();
        List<Record> recordList= Db.find(Db.getSql("pdfBlock.fun001.01"),10L);
        recordList.stream().forEach(
                record -> blocks.add(RecordUtils.getInstance().recordToObj(record,PdfBlock.class))
        );
        System.out.println(blocks);
    }

    @Test
    public void getBlockById() {
        Long id=1L;
        System.out.println(Db.findFirst(Db.getSql("pdfBlock.fun001.02"),id));
    }

    @Test
    public void savePdfBlock() {
        PdfBlock pdfBlock=new PdfBlock();
        pdfBlock.setType(3);
        pdfBlock.setBlockOrder(3);
        pdfBlock.setFontFamily("xiaogua");
        pdfBlock.setFontSize(12);
        pdfBlock.setTextBody("dew");
        pdfBlock.setPageId(10L);
        Record record= RecordUtils.getInstance().objToRecord(pdfBlock,PdfBlock.class);
        Db.save("pdf_block",record);
    }
}
package knowmap.top.test.serviceTest;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import knowmap.top.managers.pdfPage.entity.PdfPage;
import knowmap.top.utils.RecordUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PdfPageServiceImplTest extends BaseTest {

    @Test
    public void getPagesByBlobId() {
        List<PdfPage> pdfPages=new ArrayList<>();
        List<Record>recordList= Db.find(Db.getSql("pdfPage.fun001.01"),1L);
        recordList.stream().forEach(
                record -> {
                    pdfPages.add(RecordUtils.getInstance().recordToObj(record,PdfPage.class));
                }
        );
        System.out.println(pdfPages);
    }

    @Test
    public void getPagesById() {
        System.out.println(Db.findFirst(Db.getSql("pdfPage.fun001.02"),1L));
    }

    @Test
    public void savePdfPage() {
        PdfPage pdfPage=new PdfPage();
        pdfPage.setBlobId(1L);
        pdfPage.setFontFamily("xiaozhuan");
        pdfPage.setColumnWidth((float) 12.2);
        pdfPage.setFontSize(14);
        pdfPage.setHeight((float) 2.7);
        Record record= RecordUtils.getInstance().objToRecord(pdfPage,PdfPage.class);
        Db.save("pdf_page",record);

    }
}
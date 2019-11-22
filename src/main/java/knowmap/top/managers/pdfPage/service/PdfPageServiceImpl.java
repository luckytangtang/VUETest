package knowmap.top.managers.pdfPage.service;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import knowmap.top.managers.pdfPage.entity.PdfPage;
import knowmap.top.managers.pdfPage.queryParam.PdfPageQuery;
import knowmap.top.utils.RecordUtils;

import java.util.ArrayList;
import java.util.List;

public class PdfPageServiceImpl  implements PdfPageService {
    @Override
    public List<PdfPage> getPagesByBlobId(PdfPageQuery pdfPageQuery) {
        List<PdfPage> pdfPages=new ArrayList<>();
        Db.template(Db.getSql("pdfPage.fun001.01"),pdfPageQuery.getBlobId()).paginate(pdfPageQuery.getPageIndex(),pdfPageQuery.getPageSize())
                .getList().stream().forEach(
                record ->
                    pdfPages.add(RecordUtils.getInstance().recordToObj(record,PdfPage.class)));
        return pdfPages;
    }

    @Override
    public PdfPage getPagesById(Long id) {
        Record record=Db.findFirst(Db.getSql("pdfPage.fun001.02"),id);
        PdfPage pdfPage=RecordUtils.getInstance().recordToObj(record,PdfPage.class);
        return pdfPage;
    }

    /**
     * 成功 返回用户的id,失败则返回-l
     * @param pdfPage
     * @return
     */
    @Override
    public Long savePdfPage(PdfPage pdfPage) {
        Record record=RecordUtils.getInstance().objToRecord(pdfPage,PdfPage.class);
        return Db.save("pdf_page",record)?record.get("id"):-1L;
    }
}

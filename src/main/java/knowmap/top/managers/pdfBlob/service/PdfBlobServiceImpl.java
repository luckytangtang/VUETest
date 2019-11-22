package knowmap.top.managers.pdfBlob.service;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import knowmap.top.managers.pdfBlob.entity.PdfBlob;
import knowmap.top.managers.pdfBlob.queryParam.PdfBlobQuery;
import knowmap.top.utils.RecordUtils;

import java.util.List;

public class PdfBlobServiceImpl implements PdfBlobService {
    @Override
    public Long save(PdfBlob pdfBlob) {
        Record record = RecordUtils.getInstance().objToRecord(pdfBlob, PdfBlob.class);
        return Db.save("pdf_blob", record) ? record.getLong("id") : -1L;
    }

    @Override
    public PdfBlob queryById(PdfBlobQuery query) {
        List<Record> records = Db.find(Db.getSql("pdfBlob.fun002.01"), query.getId());
        if (records == null || records.size() == 0) {
            return null;
        }

        return RecordUtils.getInstance().recordToObj(records.get(0), PdfBlob.class);
    }

    @Override
    public PdfBlob queryByChecksum(PdfBlobQuery query) {

        List<Record> records = Db.find(Db.getSql("pdfBlob.fun002.02"), query.getChecksum());
        if (records == null || records.size() == 0) {
            return null;
        }
        return RecordUtils.getInstance().recordToObj(records.get(0), PdfBlob.class);
    }
}

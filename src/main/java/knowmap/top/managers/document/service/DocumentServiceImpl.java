package knowmap.top.managers.document.service;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import knowmap.top.managers.document.entity.Document;
import knowmap.top.managers.document.queryParam.DocumentQuery;
import knowmap.top.utils.RecordUtils;

import java.util.ArrayList;
import java.util.List;

public class DocumentServiceImpl implements DocumentService {
    /**
     *
     * @param document
     * @return -1 error or document's id
     */
    @Override
    public Long save(knowmap.top.managers.document.entity.Document document) {
        Record record = RecordUtils.getInstance().objToRecord(document, Document.class);
        return Db.save("document", record) ? record.getLong("id") : -1L;
    }


    @Override
    public List<Record> getDocuments(DocumentQuery query) {
        List<Document> documents = new ArrayList<>();

       return Db.find(Db.getSql("document.fun002.01"), query.getUserId(), query.getPageStart(), query.getPageSize());
    }

    @Override
    public int deleteById(Long docId, Long userId) {
        return Db.update(Db.getSql("document.fun003.01"), docId, userId);
    }

    @Override
    public Document getDocumentById(Long docId) {
        List<Record> records = Db.find(Db.getSql("document.fun002.02"), docId);
        if (records == null || records.size() == 0) {
            return null;
        }

        return RecordUtils.getInstance().recordToObj(records.get(0), Document.class);
    }
}

package knowmap.top.managers.document.service;

import com.jfinal.plugin.activerecord.Record;
import knowmap.top.managers.document.entity.Document;
import knowmap.top.managers.document.queryParam.DocumentQuery;

import java.util.List;

public interface DocumentService {
    Long save(Document document);

    List<Record> getDocuments(DocumentQuery query);

    int deleteById(Long docId, Long userId);

    Document getDocumentById(Long docId);
}

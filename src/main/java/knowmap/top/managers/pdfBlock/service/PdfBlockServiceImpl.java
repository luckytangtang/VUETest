package knowmap.top.managers.pdfBlock.service;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import knowmap.top.managers.pdfBlock.entity.PdfBlock;
import knowmap.top.managers.pdfBlock.queryParam.BlockQuery;
import knowmap.top.utils.RecordUtils;

import java.util.ArrayList;
import java.util.List;

public class PdfBlockServiceImpl implements PdfBlockService {
    @Override
    public List<PdfBlock> getBlocksByPageId(Long pageId) {
        List<PdfBlock> blocks=new ArrayList<>();
        List<Record> recordList= Db.find(Db.getSql("pdfBlock.fun001.01"),pageId);
        recordList.stream().forEach(
                record -> blocks.add(RecordUtils.getInstance().recordToObj(record,PdfBlock.class))
        );
        return blocks;
    }

    @Override
    public PdfBlock getBlockById(Long id) {
        Record record=Db.findFirst(Db.getSql("pdfBlock.fun001.02"),id);
        PdfBlock pdfBlock=RecordUtils.getInstance().recordToObj(record,PdfBlock.class);
        return pdfBlock;
    }

    /**
     *
     *成功返回用户id,失败返回-1
     * @param pdfBlock
     * @return
     */
    @Override
    public Long savePdfBlock(PdfBlock pdfBlock) {
        Record record=RecordUtils.getInstance().objToRecord(pdfBlock,PdfBlock.class);
        return Db.save("pdf_block",record)?record.get("id"):-1L;
    }

    @Override
    public List<PdfBlock> getBlocksByPage(BlockQuery blockQuery) {
        List<PdfBlock> blocks = new ArrayList<>();
        Db.find(Db.getSql("pdfBlock.fun001.03"), blockQuery.getAreaVersion(), blockQuery.getBlobId(), blockQuery.getPageStart(), blockQuery.getPageSize()).stream().forEach(
                record -> blocks.add(RecordUtils.getInstance().recordToObj(record, PdfBlock.class))
        );

        return blocks;
    }
}

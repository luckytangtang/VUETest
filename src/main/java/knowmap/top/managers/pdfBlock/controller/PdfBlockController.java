package knowmap.top.managers.pdfBlock.controller;

import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import knowmap.top.common.ResponseObj;
import knowmap.top.common.ResponseStatus;
import knowmap.top.managers.document.entity.Document;
import knowmap.top.managers.document.service.DocumentService;
import knowmap.top.managers.document.service.DocumentServiceImpl;
import knowmap.top.managers.pdfBlock.entity.PdfBlock;
import knowmap.top.managers.pdfBlock.queryParam.BlockQuery;
import knowmap.top.managers.pdfBlock.service.PdfBlockService;
import knowmap.top.managers.pdfBlock.service.PdfBlockServiceImpl;
import knowmap.top.serviceCode.PdfBlockCode;

import java.util.List;

public class PdfBlockController extends Controller {
    @Inject(DocumentServiceImpl.class)
    DocumentService documentService;

    @Inject(PdfBlockServiceImpl.class)
    PdfBlockService pdfBlockService;

    static Log log = Log.getLog(PdfBlockController.class);
    /**
     * 分页查询block
     */
    public void getBlocks() {
        ResponseObj responseObj = new ResponseObj();

        try {
            Integer pageSize = getInt("pageSize");
            Integer pageIndex = getInt("pageIndex");

            Long docId = getLong("docId");
            log.info(pageSize + " " + pageIndex + " " + docId);
            Document document = documentService.getDocumentById(docId);
            BlockQuery blockQuery = new BlockQuery();
            blockQuery.setPageIndex(pageIndex).setPageSize(pageSize);

            List<PdfBlock> list = pdfBlockService.getBlocksByPage(
                    blockQuery.setAreaVersion(document.getAreaVersion()).
                            setBlobId(document.getBlobId())
            );

            responseObj.setData(list).
                    setCode(ResponseStatus.Ok.getCode()).
                    setServiceMessage(PdfBlockCode.OK.getMessage()).
                    setServiceCode(PdfBlockCode.OK.getCode());

        } catch (Exception ex) {
            log.error("PdfBlockController#getBlocks", ex);
            responseObj.setCode(ResponseStatus.Error.getCode()).setMessage("服务器错误");
        }

        renderJson(responseObj);
    }
}

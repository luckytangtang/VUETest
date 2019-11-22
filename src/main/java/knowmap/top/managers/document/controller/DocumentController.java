package knowmap.top.managers.document.controller;

import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import knowmap.top.common.ResponseObj;
import knowmap.top.common.ResponseStatus;
import knowmap.top.managers.document.queryParam.DocumentQuery;
import knowmap.top.managers.document.service.DocumentService;
import knowmap.top.managers.document.service.DocumentServiceImpl;
import knowmap.top.serviceCode.DocumentCode;

public class DocumentController extends Controller {
    @Inject(DocumentServiceImpl.class)
    DocumentService documentService;

    static Log log = Log.getLog(DocumentController.class);

    public void getList() {
        Long userId = getLong("userId");
        ResponseObj responseObj = new ResponseObj();
        try {
            DocumentQuery query = new DocumentQuery();
            query.setUserId(userId);
            query.setPageIndex(getInt("pageIndex"));
            query.setPageSize(getInt("pageSize"));
            responseObj.setData(documentService.getDocuments(query)).
                    setCode(ResponseStatus.Ok.getCode()).
                    setServiceCode(DocumentCode.OK.getCode()).
                    setServiceMessage(DocumentCode.OK.getMessage());
        } catch (Exception ex) {
            log.error("DocumentController#getList", ex);
            responseObj.setData(ex.getCause());
            responseObj.setMessage(ex.getMessage());
            responseObj.setCode(ResponseStatus.Error.getCode());
        }

        System.out.println(getResponse().getHeader("token"));

        renderJson(responseObj);
    }


    public void remove() {
        Long docId = getLong("docId");
        Long userId = getLong("userId");
        ResponseObj responseObj = new ResponseObj();

        try {
            if (documentService.deleteById(docId, userId) > 0) {
                responseObj.setMessage("ok");
                responseObj.setCode(ResponseStatus.Ok.getCode());
            } else {
                responseObj.setMessage("failure");
                responseObj.setCode(ResponseStatus.Ok.getCode());
            }
        } catch (Exception ex) {
            responseObj.setData(ex.getCause());
            responseObj.setMessage(ex.getMessage());
            responseObj.setCode(ResponseStatus.Error.getCode());
        }

        renderJson(responseObj);
    }


}

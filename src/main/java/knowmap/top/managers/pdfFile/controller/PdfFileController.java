package knowmap.top.managers.pdfFile.controller;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.aop.Inject;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.json.FastJson;
import com.jfinal.log.Log;

import com.jfinal.plugin.activerecord.tx.Tx;
import com.jfinal.upload.UploadFile;
import knowmap.top.common.PdFBlobStatus;
import knowmap.top.common.ResponseObj;
import knowmap.top.common.ResponseStatus;

import knowmap.top.common.TaskStatus;
import knowmap.top.config.Interceptors.TokenInterceptor;
import knowmap.top.managers.document.entity.Document;
import knowmap.top.managers.document.service.DocumentService;
import knowmap.top.managers.document.service.DocumentServiceImpl;
import knowmap.top.managers.pdfBlob.entity.PdfBlob;
import knowmap.top.managers.pdfBlob.queryParam.PdfBlobQuery;
import knowmap.top.managers.pdfBlob.service.PdfBlobService;
import knowmap.top.managers.pdfBlob.service.PdfBlobServiceImpl;
import knowmap.top.managers.pdfFile.decorate.DecorateStream;
import knowmap.top.managers.task.entity.Task;
import knowmap.top.managers.task.service.TaskService;
import knowmap.top.managers.task.service.TaskServiceImpl;
import knowmap.top.utils.FilePathUtils;
import knowmap.top.utils.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Timestamp;

public class PdfFileController extends Controller {

    private static Log log = Log.getLog(PdfFileController.class);

    @Inject(PdfBlobServiceImpl.class)
    private PdfBlobService pdfBlobService;

    @Inject(DocumentServiceImpl.class)
    private DocumentService documentService;

    @Inject(TaskServiceImpl.class)
    private TaskService taskService;

    // 新增记录
    @Before({Tx.class})
    public void savePdfFile() {
        ResponseObj responseObj = new ResponseObj();
        try {
            // TODO 启动token需要修改这里
//            UploadFile file = (UploadFile)getRequest().getAttribute("uploadFile");
            UploadFile file = getFile();
            File pdfFile = file.getFile();
            Long userId = getParaToLong("userId");
            String author = get("author");
            String title = get("title");
            long blobId;
            Task task = new Task();
            try (
                    InputStream inputStream = new FileInputStream(pdfFile);
                    DecorateStream decorateStream = new DecorateStream(inputStream);
                    InputStream stream1 = decorateStream.getInputStream();
                    InputStream stream2 = decorateStream.getInputStream()
            ) {
                String checksum = FileUtils.getChecksum(stream1);
                PdfBlobQuery query = new PdfBlobQuery();
                query.setChecksum(checksum);
                if (pdfBlobService.queryByChecksum(query) == null) {
                    FileUtils.save(stream2, FilePathUtils.getPdfPath(checksum));
                    // 保存一个blob
                    PdfBlob pdfBlob = new PdfBlob();
                    pdfBlob.setChecksum(checksum).
                            setCreateTime(new Timestamp(System.currentTimeMillis())).
                            setFileSize(pdfFile.length()).
                            setStatus(PdFBlobStatus.UnDo.getCode()).
                            setUserId(userId);
                    blobId = pdfBlobService.save(pdfBlob);
                } else {
                    blobId = pdfBlobService.queryByChecksum(query).getId();
                }

            }

            // 保存一个document
            Document document = new Document();
            document.setAreaVersion(0).
                    setUserId(userId).
                    setAuthor(author).
                    setBlobId(blobId).
                    setCreateTime(new Timestamp(System.currentTimeMillis())).
                    setFileName(pdfFile.getName()).
                    setTitle(title).
                    setStatus(0).
                    setId(documentService.save(document)); // 保存doc

            task.setStatus(TaskStatus.JsonUndo.getValue());

            task.setDocId(document.getBlobId());

            taskService.saveTask(task);

            responseObj.setData(document);
            responseObj.setMessage("上传成功");
            responseObj.setCode(ResponseStatus.Ok.getCode());
        } catch (Exception ex) {
            log.info(ex.getMessage());
            responseObj.setCode(ResponseStatus.Error.getCode());
            responseObj.setMessage("上传文件失败");
        }

        renderJson(responseObj);
    }

    // 注意此为分页查询，参数必须带上pageSize, pageIndex
    @ActionKey("/pdfFile/get/PdfFiles")
    public void getPdfFiles() {

    }

    @ActionKey(value = "/pdfFile/get/pdfFiles/id")
    public void getPdfFileById() {

    }

    // 记录整体修改
    @ActionKey(value = "/pdfFile/put/pdfFiles/id")
    public void updateTotalPdfFile() {

    }

    // 记录部分修改
    @ActionKey(value = "/pdfFile/patch/pdfFiles/id")
    public void updatePartPdfFile() {

    }

    // 单条删除
    @ActionKey(value = "/pdfFile/delete/pdfFiles/id")
    public void deletePdfFile() {

    }

    // 批量删除
    @ActionKey(value = "/pdfFile/delete/pdfFiles")
    public void deletePdfFiles() {

    }
}

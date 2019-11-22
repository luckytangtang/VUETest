package knowmap.top.managers.downloadFile.controller;

import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import knowmap.top.common.ResponseObj;
import knowmap.top.common.ResponseStatus;
import knowmap.top.managers.downloadFile.constants.BlockConstant;
import knowmap.top.managers.downloadFile.constants.HtmlConstant;
import knowmap.top.managers.document.service.DocumentService;
import knowmap.top.managers.document.service.DocumentServiceImpl;
import knowmap.top.managers.pdfBlob.entity.PdfBlob;
import knowmap.top.managers.pdfBlob.queryParam.PdfBlobQuery;
import knowmap.top.managers.pdfBlob.service.PdfBlobService;
import knowmap.top.managers.pdfBlob.service.PdfBlobServiceImpl;
import knowmap.top.managers.pdfBlock.entity.PdfBlock;
import knowmap.top.managers.pdfBlock.service.PdfBlockService;
import knowmap.top.managers.pdfBlock.service.PdfBlockServiceImpl;
import knowmap.top.utils.FilePathUtils;

import java.io.*;
import java.net.URL;
import java.sql.Blob;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class CommonFileController extends Controller {
    @Inject(PdfBlobServiceImpl.class)
    PdfBlobService pdfBlobService;

    @Inject(DocumentServiceImpl.class)
    DocumentService documentService;

    @Inject(PdfBlockServiceImpl.class)
    PdfBlockService pdfBlockService;

    static Log log = Log.getLog(CommonFileController.class);

    /**
     * 获取HTML
     */
    public void getResetHtml() {
        File temp = null;
        try {
//            Long docId = getLong("docId");
//            Integer pageIndex = getInt("pageIndex");
//            Long pageId = getLong("pageId");

            // 测试开始
            Integer pageIndex = 1;
            Long docId = 1L;
            Long pageId = 1L;
            // 测试结束

            List<PdfBlock> blocks = pdfBlockService.getBlocksByPageId(pageId);
            StringBuilder sb = new StringBuilder();
            sb.append(HtmlConstant.getHead(
                    HtmlConstant.getCssUrl(docId)
            ));

            Collections.sort(blocks, Comparator.comparing(PdfBlock::getBlockOrder));

            blocks.stream().forEach(block -> {
                if (block.getType() == BlockConstant.TEXT) {
                    sb.append(
                            "<p>" + block.getTextBody() + "</p>"
                    );
                } else {
                    String position = block.getX1() + "-" + block.getY1() + "-" + block.getX2() + "-" + block.getY2();
                    sb.append(
                            "<p><img src='" + HtmlConstant.getImage(
                                    pageIndex, docId, position
                            ) + "' /></p>"
                    );
                }
            });


            sb.append(HtmlConstant.getTail());
            byte[] bytes = sb.toString().getBytes();
            temp = File.createTempFile("pattern", ".html");
            FileOutputStream fos = new FileOutputStream(temp);
            fos.write(bytes, 0, bytes.length);
            fos.flush();
            renderFile(temp);
            fos.close();
        } catch (Exception ex) {
            log.error("CommonFileController#getResetHtml", ex);
            renderJson(new ResponseObj().
                    setCode(ResponseStatus.Error.getCode()).
                    setMessage("error"));
        } finally {
            if (temp != null) {
                temp.deleteOnExit();
            }
        }


    }

    /**
     * 获取css
     */
    public void getCssFile() {
        try {
//            Long docId = getLong("docId");
//            Long blobId = documentService.getDocumentById(docId).getBlobId();
//            String checksum = pdfBlobService.queryById(new PdfBlobQuery().setId(blobId)).getChecksum();
            String checksum = "123";
            String cssPath = FilePathUtils.getCssPath(checksum);
            renderFile(new File(cssPath));
        } catch (Exception ex) {
            log.error("CommonFileController#getCssFile", ex);
            renderJson(
                    new ResponseObj().
                            setCode(ResponseStatus.Error.getCode()).
                            setMessage("error")
            );
        }

    }

    /**
     * 获取block图片
     */
    public void getBlockImage() {
        try {
//            Integer pageIndex = getInt("pageIndex");
//            String position = get("position");
//            Long docId = getLong("docId");
//            Long blobId = documentService.getDocumentById(docId).getBlobId();
//            String checksum = pdfBlobService.queryById(new PdfBlobQuery().setId(blobId)).getChecksum();
            String checksum = "123";
            Integer pageIndex = 1;
            Integer order = 13;
            String position = "316.81451416015625_395.7598876953125_555.9303588867188_585.5872802734375";
            String path = FilePathUtils.getBlockImagePath(checksum, pageIndex, position, order);
            System.out.println(path);
            renderFile(new File(path));
        } catch (Exception ex) {
            log.error("CommonFileController#getBlockImage", ex);
            renderJson(
                    new ResponseObj().
                            setCode(ResponseStatus.Error.getCode()).
                            setMessage("error")
            );
        }


    }


    public void getPdfJson() {
        try {
//            Long docId = getLong("docId");
//            Integer pageIndex = getInt("pageIndex");
//            Long blobId = documentService.getDocumentById(docId).getBlobId();
//            String checksum = pdfBlobService.queryById(new PdfBlobQuery().setId(blobId)).getChecksum();
            String checksum = "123";
            Integer pageIndex = 1;
            String jsonPath = FilePathUtils.getJsonPath(checksum, pageIndex);
            renderFile(new File(jsonPath));
        } catch (Exception ex) {
            log.error("CommonFileController#getPdfJson", ex);
            renderJson(new ResponseObj().
                    setCode(ResponseStatus.Error.getCode()).
                    setMessage("error"));
        }

    }


    /**
     * 获取PDF源文件
     */
    public void getPdfFile() {
        try {
            Long docId = getLong("docId");
            Long blobId = documentService.getDocumentById(docId).getBlobId();
            String checksum = pdfBlobService.queryById(new PdfBlobQuery().setId(blobId)).getChecksum();
//            String checksum = "123";
            String pdfPath = FilePathUtils.getPdfPath(checksum);

            renderFile(new File(pdfPath));
        } catch (Exception ex) {
            log.error("CommonFileController#getPdfFile", ex);
            renderJson(new ResponseObj().
                    setCode(ResponseStatus.Error.getCode()).
                    setMessage("error"));
        }
    }

    public void getFileStream(){
        try {
           Long docId = getLong("docId");
            Long blobId = documentService.getDocumentById(docId ).getBlobId();
            System.out.println("进来了");
            String checksum = pdfBlobService.queryById(new PdfBlobQuery().setId(blobId)).getChecksum();
            String pdfPath = FilePathUtils.getPdfPath(checksum);
            File file=new File(pdfPath);
            String filename=file.getName();
            BufferedInputStream br=new BufferedInputStream(new FileInputStream(file));
            byte [] buf=new byte[1024];
            int len=0;
            getResponse().reset();
            URL u=new URL("file:///"+pdfPath);
            getResponse().setContentType(u.openConnection().getContentType());
            getResponse().setHeader("Content-Disposition","inline;filename="+filename);
            OutputStream out =getResponse().getOutputStream();
            System.out.println("二次进来了");
            while ((len=br.read(buf))>0)
                out.write(buf,0,len);
            br.close();
            out.close();
            renderNull();
        } catch (Exception ex) {
            log.error("CommonFileController#getPdfFile", ex);
            renderJson(new ResponseObj().
                    setCode(ResponseStatus.Error.getCode()).
                    setMessage("error"));
        }

    }
}

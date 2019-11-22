package knowmap.top.managers.pdfFile.decorate;

import java.io.*;

/**
 *  解决 InputStream 不可重复读
 */
public class DecorateStream implements AutoCloseable {

    private ByteArrayOutputStream byteArrayOutputStream;

    private InputStream inputStream;

    @Override
    public void close() throws Exception {
        inputStream.close();
    }

    public DecorateStream(InputStream in) throws IOException {
        inputStream = in;
        byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = inputStream.read(buffer)) > -1 ) {
            byteArrayOutputStream.write(buffer, 0, len);
        }
        byteArrayOutputStream.flush();
    }


    public InputStream getInputStream() {
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }
}

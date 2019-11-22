package knowmap.top.managers.downloadFile.constants;

import com.jfinal.kit.PropKit;

public class HtmlConstant {
    public static String getHead(String cssUrl) {
        return  "<html>\n" +
                "<head> \n" +
                "<meta charset=\"utf-8\"> \n" +
                "<title>test</title> \n" +
                "<link rel=\"stylesheet\" type=\"text/css\" href='" + cssUrl + "'>\n" +
                "</head>\n" +
                "<body>";
    }

    private static String COMMON;

    static  {
        PropKit.use("config.txt");
        COMMON = PropKit.get("fileBaseUrl");
    }



    public static String getCssUrl(Long docId) {
        return COMMON + "getCssFile?docId=" + docId;
    }

    public static String getImage(Integer pageIndex, Long docId, String position) {
        return COMMON + "getBlockImage?" +
                "position=" + position +
                "&docId=" + docId +
                "&pageIndex=" + pageIndex;
    }


    public static String getTail() {
        return "</body>\n" +
                "</html>";
    }

    public static void main(String[] args) {

    }
}

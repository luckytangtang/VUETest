package knowmap.top.utils;

import lombok.Getter;

public class FilePathUtils {
    static {
        if ("windows".equals(System.getProperties().getProperty("os.name"))) {
           // ROOT = "/Users/xiahui/knowmap-file";
            ROOT = "D:/root/knowmap-file";
        } else {
            ROOT = "D:/root/knowmap-file";
//            ROOT = "/Users/xiahui/knowmap-file";
        }
    }

    @Getter
    private static String ROOT;

    private static String pdfDir = ROOT + "/" + "pdfFile/";

    private static String jsonDir = ROOT + "/" + "jsonFile/";

    private static String imageFile = ROOT + "/" + "imagePdf/";

    private static String cssPath = ROOT + "/" + "cssFile/";

    public static String getJsonPath(String checksum, Integer pageIndex) {
        return jsonDir + checksum + "/" + pageIndex + ".json";
    }

    public static String getPdfPath(String checksum) {
        return pdfDir + checksum + ".pdf";
    }

    public static String getCssPath(String checksum) {
        return cssPath + checksum + ".css";
    }

    public static String getBlockImagePath(String checksum, Integer pageIndex, String position, Integer order) {
        return imageFile + checksum + '/' + pageIndex + '/' + order + "_" + position + ".jpg";
    }

    public static String getROOT() {
        return ROOT;
    }
}

package bank.model;

import bank.common.Constants;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FolderFileManager {

    private final static Charset ENCODING_ = StandardCharsets.UTF_8;

    public static boolean createFolder(String folderName) {
        File folder = new File(folderName);
        return folder.mkdir();
    }

    public static boolean createFile(String fileName) throws IOException {
        File file = new File(fileName);
        return file.createNewFile();
    }

    public static long createUserFile() throws IOException {
        File folder = new File(Constants.FOLDER_NAME_USER);
        if (!folder.exists()) {
            boolean folderCreated = folder.mkdir();
            if (folderCreated) {
                showMessage("User folder created");
            } else {
                return Constants.ErrorCode.USER_FOLDER_CREATION_FAILED;
            }
        }

        long fileName = System.currentTimeMillis();
        File file = new File(Constants.FOLDER_NAME_USER + System.currentTimeMillis() + ".txt");
        boolean isFileCreated = file.createNewFile();
        return isFileCreated? fileName : Constants.ErrorCode.USER_FILE_CREATION_FAILED;
    }

    public static void write(String fileName, JSONObject object) throws IOException {
        if (object == null) return;
        String data = object.toJSONString();
        File file = new File(fileName);
        FileUtils.writeStringToFile(file, data, ENCODING_);
    }

    public static JSONObject read(String fileName) throws IOException, ParseException {
        File file = new File(fileName);
        JSONParser parser = new JSONParser();
        return (JSONObject) parser.parse(FileUtils.readFileToString(file, ENCODING_));
    }

    public static List<String> getUserAccountList() {
        File folder = new File(Constants.FOLDER_NAME_USER);
        File[] accounts = folder.listFiles();
        List<String> accountList = new ArrayList<>();
        for (File file:
             accounts) {
            String fileName = file.getName();
            accountList.add(fileName.substring(0, fileName.length() - 4));
        }

        return accountList;
    }

    public static void showMessage(String message) {
        System.out.println(message);
    }

}

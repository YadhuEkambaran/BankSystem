package bank.model;

import org.json.simple.JSONObject;

public class TestFolderFileCreation {

    public static void main(String args[]) {
        FolderFileManager.createFolder("Employee");
        try {
            boolean isFileCreated = FolderFileManager.createFile("Employee/employees.txt");
            if (isFileCreated) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("username", "yadhu");
                jsonObject.put("dateofbirth", "12/10/1992");
                FolderFileManager.write("Employee/employees.txt", jsonObject);
            } else {

            }

            JSONObject details = FolderFileManager.read("Employee/employees.txt");
            System.out.println(details.get("username"));

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}

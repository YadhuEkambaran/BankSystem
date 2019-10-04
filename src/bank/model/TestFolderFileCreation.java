package bank.model;

import org.json.simple.JSONObject;

public class TestFolderFileCreation {

    public static void main(String args[]) {
        FolderFileManager.createFolder("Employee");
        try {
            boolean isFileCreated = FolderFileManager.createFile("Employee/121323131.txt");
            if (isFileCreated) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("username", "yadhu");
                jsonObject.put("dateofbirth", "12/10/1992");
                FolderFileManager.write("Employee/121323131.txt", jsonObject);
            } else {

            }

            JSONObject details = FolderFileManager.read("Employee/121323131.txt");
            System.out.println(details.get("username"));

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}

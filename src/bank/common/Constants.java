package bank.common;

public interface Constants {

    String FOLDER_NAME_EMPLOYEE = "employee/";
    String FOLDER_NAME_USER = "user/";


     interface JsonKeys {
         String USER_FIRST_NAME = "first_name";
         String USER_MIDDLE_NAME = "middle_name";
         String USER_LAST_NAME = "last_name";
         String USER_DOB = "user_dob";
         String USER_SIN = "user_sin";
         String USER_ADDRESS = "user_address";
         String USER_CITY = "user_city";
         String USER_PROVINCE = "user_provice";
         String USER_COUNTRY = "user_country";
         String USER_MOBILE_NO = "user_mobile_no";
         String USER_BALANCE = "user_balance";
     }

     interface ErrorCode {
         int USER_FOLDER_CREATION_FAILED = 401;
         int USER_FILE_CREATION_FAILED = 402;

     }
}

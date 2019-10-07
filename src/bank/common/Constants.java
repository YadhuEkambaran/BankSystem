package bank.common;

public interface Constants {

    String FOLDER_NAME_EMPLOYEE = "employee/";
    String FOLDER_NAME_USER = "user/";
    String FILE_FORMAT = ".txt";

    int ACCOUNT_TYPE_CHECKING = 1;
    int ACCOUNT_TYPE_SAVINGS = 2;



     interface JsonKeys {
         String USER_DETAILS = "user_details";

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
         String USER_ACCOUNT_TYPE = "user_account_type";
         String USER_PIN = "user_pin";

         String USER_DEPOSIT = "deposit";
         String USER_DEPOSIT_DATE = "deposit_date";
         String USER_DEPOSIT_AMOUNT = "deposit_amount";

         String USER_WITHDRAW = "withdraw";
         String USER_WITHDRAW_DATE = "withdraw_date";
         String USER_WITHDRAW_AMOUNT = "withdraw_amount";

         String USER_TRANSFER = "transfer";
         String USER_TRANSFER_ACCOUNT = "transfer_account_no";
         String USER_TRANSFER_DATE = "transfer_date";
         String USER_TRANSFER_AMOUNT = "transfer_amount";

         String USER_DEBIT_CARD_NO = "debit_card_no";
         String USER_DEBIT_CARD_NO_EXPIRY_DATE = "debit_card_no_expiry_date";
         String USER_DEBIT_CARD_NO_EXPIRY_YEAR = "debit_card_no_expiry_year";
         String USER_DEBIT_CARD_NO_CVV = "cvv";

     }

     interface ErrorCode {
         int USER_FOLDER_CREATION_FAILED = 401;
         int USER_FILE_CREATION_FAILED = 402;

     }
}

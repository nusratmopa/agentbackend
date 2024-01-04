package bbl.agent.banking.constants;

/**
 * @author Nusrat Jahan Tarin
 */
public interface Constants {


    String SUPER_ADMIN_EMAIL = "superadmin@gems.gov.bd";
    String SYSTEM = "system";
    String SAVE_SUCCESS = "তথ্য সংরক্ষণ সফলভাবে সম্পন্ন হয়েছে";
    String SAVE_FAILED = "তথ্য সংরক্ষণ ব্যর্থ হয়েছে";

    String UPDATE_SUCCESS = "তথ্য হালনাগাদ সফলভাবে সম্পন্ন হয়েছে";
    String UPDATE_FAILED = "তথ্য হালনাগাদ ব্যর্থ হয়েছে";

    String DELETE_SUCCESS = "তথ্য মুছে ফেলার প্রক্রিয়া সফলভাবে সম্পন্ন হয়েছে";
    String DELETE_FAILED = "তথ্য মুছে ফেলার প্রক্রিয়া ব্যর্থ হয়েছে";

    String ADD_SUCCESS = "Successfully Added";
    String ADD_FAILED = "Failed to add data.";

    String REMOVE_SUCCESS = "Successfully Removed";

    String UPLOAD_SUCCESS = "Successfully Uploaded";

    String INCREASE_SUCCESS = "Successfully Increased";

    String DECREASE_SUCCESS = "Successfully Decreased";

    String FETCH_SUCCESS = "Successfully Fetch Data";
    String FETCH_FAILED = "দুঃখিত, কোন তথ্য খুঁজে পাওয়া যায়নি";

    String AVAILABLE = "Available.";
    String NOT_AVAILABLE = "Not available.";

    String ALREADY_EXIST = "Already exist.";
    String DOES_NOT_EXIST = "Doesn't exist.";


    String DRAFT_SAVED_SUCCESS = "খসড়া সফলভাবে সংরক্ষিত হয়েছে";
    String DRAFT_SAVE_FAILED = "খসড়া সংরক্ষণ করতে ব্যর্থ হয়েছে";
    String DRAFT_SUBMISSION_SUCCESS = "পর্যালোচনার জন্য দাখিল সম্পন্ন হয়েছে";
    String DRAFT_SUBMISSION_FAILED = "পর্যালোচনার জন্য দাখিল ব্যর্থ হয়েছে";

    String CURRENT_PASSWORD_MISMATCH = "দুঃখিত, আপনার বর্তমান পাসওয়ার্ড সঠিক নয়";
    String CONFIRM_PASSWORD_MISMATCH = "দুঃখিত, আপনার পাসওয়ার্ডে অমিল রয়েছে, পুনরায় প্রদান করুন";
    String PASSWORD_UPDATE_SUCCESS = "পাসওয়ার্ড হালনাগাদ সফলভাবে সম্পন্ন হয়েছে";
    String PASSWORD_UPDATE_FAILED = "দুঃখিত, পাসওয়ার্ড হালনাগাদ ব্যর্থ হয়েছে";


    String USER_NOT_EXIST_WITH_EMAIL_OR_PHONE = "দুঃখিত, এই ইমেইল বা ফোন নম্বর দিয়ে কোন ব্যবহারকারী খুঁজে পাওয়া যায়নি";
    String USER_NOT_EXIST_WITH_ORGANIZATION = "দুঃখিত, এই কর্মকর্তা/কর্মচারী কোন অফিসে কর্মরত নেই";
    String ACCOUNT_IS_LOCKED = "দুঃখিত, আপনার অ্যাকাউন্টটি সাময়িক সময়ের জন্য বন্ধ রাখা হয়েছে, হেল্পলাইনে যোগাযোগ করুন";
    String ACCOUNT_IS_INACTIVE = "দুঃখিত, আপনার অ্যাকাউন্টটি বর্তমানে সচল নেই, হেল্পলাইনে যোগাযোগ করুন";
    String LOGIN_FAILED = "Invalid username or password";
    String OTP_SEND_SUCCESSFULLY = "ওয়ান টাইম পাসওয়ার্ড সফলভাবে প্রেরণ করা হয়েছে";
    String FAILED_TO_RECOVER_PASSWORD = "দুঃখিত, পাসওয়ার্ড পুনরুদ্ধার করতে ব্যর্থ হয়েছে";
    String OTP_VERIFY_SUCCESS = "ওয়ান টাইম পাসওয়ার্ড সফলভাবে যাচাই করা হয়েছে";
    String OTP_VERIFY_FAILED = "দুঃখিত, ওয়ান টাইম পাসওয়ার্ড যাচাই করা যায়নি";
    String UPLOAD_JOINING_ENDORSEMENT_FILE = "দুঃখিত, যোগদানের পৃষ্ঠাংকন-কপি আপলোড করুন";
    String UPLOAD_ORDER_FILE = "দুঃখিত, আদেশের কপি আপলোড করুন";

    String APPLICATION_INIT_SUCCESS = "খসড়ার তথ্য যাচাই-বাছাইয়ের জন্য পর্যালোচনাকারীর নিকট সফলভাবে প্রেরিত হয়েছে";
    String APPLICATION_INIT_FAILED = "দুঃখিত, খসড়ার তথ্য যাচাই-বাছাইয়ের জন্য পর্যালোচনাকারীর নিকট প্রেরণ করতে ব্যর্থ হয়েছে";

    String QUOTA_IFO_INVALID = "দুঃখিত, কোটা সংক্রান্ত খসড়ার তথ্য সঠিক নয়।";
    String QUOTA_ONLINE_FF_NUMBER_REQUIRED = "অনলাইন মুক্তিযোদ্ধা নম্বর প্রয়োজন।";
    String QUOTA_FF_GAZETTE_NUMBER_REQUIRED = "গেজেট নম্বর প্ৰয়োজন।";


    String ROLE_IS_USING = "দুঃখিত, এই রোল বর্তমানে ব্যবহৃত হচ্ছে";
    String FILE_UPDATE_FAILED = "দুঃখিত, ফাইল হালনাগাদ করতে ব্যর্থ হয়েছে";
    String FROM_DATE_TO_DATE_ERROR = "দুঃখিত, শুরুর তারিখ এবং শেষের তারিখ সমঞ্জস্যপূর্ন নয়।";


    String USER_NOT_FOUND = "দুঃখিত, কোন ব্যবহারকারীর তথ্য খুঁজে পাওয়া যায়নি";

    String POSTING_BASIC_SALARY_PAYSCALE_MISMATCH = "মূল বেতন উল্লেখিত বেতন স্কেল আর সাথে সামঞ্জস্যপূর্ন নয়";

    String TRAINING_EXIST = "একই নামে ট্রেনিং বিদ্যমান";
    String TRAINING_MARKSHEET_REQUIRED = "দুঃখিত, গ্রেডের তথ্য প্রদান করলে মার্কশীট প্রদান বাধ্যতামূলক";
    String HAS_NO_PERMISSION = "দুঃখিত, আপনার পর্যাপ্ত অনুমতি নেই।";

    String PORTAL_OPINION_SUBMIT_SUCCESS = "আপনার মতামত সফলভাবে জমা হয়েছে ।";

    String INCORRECT_YOUTUBE_LINK = "সঠিক ইউটিউব লিংক লিখুন";

}

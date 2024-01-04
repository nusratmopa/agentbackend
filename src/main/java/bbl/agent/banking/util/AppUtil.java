package bbl.agent.banking.util;

import bbl.agent.banking.exceptions.AgentBankingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @author Nusrat Jahan Tarin
 */

@Slf4j
@Component
public class AppUtil {

    public static DateFormat DD_MMM_YYYY = new SimpleDateFormat("dd-MMM-yyyy");
    public static DateFormat YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");
    public static DateFormat YYYY_MM_DD_HH_MM_SS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static DateFormat DD_MMM_YYYY_HH_MM_SS = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");

    public synchronized long getMaxVersion() {
        return Calendar.getInstance().getTimeInMillis();
    }

    public synchronized static UUID toUUID(Object obj) {
        try {
            return UUID.fromString(obj.toString());
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return null;
    }

    public synchronized static UUID toUUID(String str) {
        try {
            return UUID.fromString(str);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return null;
    }

    public static UUID getUUID(Map<?, ?> obj, String key) {
        if (obj == null || key == null) {
            return null;
        }
        try {
            return UUID.fromString(obj.get(key).toString());
        } catch (Exception ex) {
        }

        return null;
    }

    public static String getString(Map<?, ?> obj, String key) {
        if (obj == null || key == null) return "";
        try {
            return obj.get(key).toString();
        } catch (Exception ex) {
            return "";
        }
    }

    public static long getLong(Map<?, ?> obj, String key) {
        if (obj == null || key == null) return 0;
        try {
            return toLong(obj.get(key));
        } catch (Exception ex) {
            return 0;
        }
    }

    public static int getInt(Map<?, ?> obj, String key) {
        if (obj == null || key == null) return 0;
        try {
            return toInt(obj.get(key));
        } catch (Exception ex) {
            return 0;
        }
    }

    public static boolean getBoolean(Map<?, ?> obj, String key) {
        if (obj == null || key == null) return false;
        try {
            return toBoolean(obj.get(key));
        } catch (Exception ex) {
            return false;
        }
    }

    public static double getDouble(Map<?, ?> obj, String key) {
        if (obj == null || key == null) return 0.0;
        try {
            return toDouble(obj.get(key));
        } catch (Exception ex) {
            return 0.0;
        }
    }

    public static float getFloat(Map<?, ?> obj, String key) {
        if (obj == null || key == null) return 0.0f;
        try {
            return toFloat(obj.get(key));
        } catch (Exception ex) {
            return 0.0f;
        }
    }

    public static String toString(Object str) {
        if (str == null) return "";
        try {
            return String.valueOf(str);
        } catch (Exception ex) {
            return "";
        }
    }

    public static int toInt(String number) {
        try {
            return Integer.parseInt(number);
        } catch (Exception ex) {
            return 0;
        }
    }

    public static int toInt(Object number) {
        try {
            return Integer.parseInt(number.toString());
        } catch (Exception ex) {
            return 0;
        }
    }

    public static long toLong(Object number) {
        try {
            return Long.parseLong(number + "");
        } catch (Exception ex) {
            return 0;
        }
    }

    public static long toLong(String number) {
        try {
            return Long.parseLong(number);
        } catch (Exception ex) {
            return 0;
        }
    }

    public static boolean toBoolean(Object val) {
        try {
            if (val.equals(1) || val.equals(true)) return true;
            else return (boolean) val;
        } catch (Exception ex) {
            return false;
        }
    }

    public static double toDouble(Object number) {
        try {
            return Double.parseDouble(number.toString());
        } catch (Exception ex) {
            return 0.0f;
        }
    }

    public static float toFloat(Object number) {
        try {
            return Float.parseFloat(number.toString());
        } catch (Exception ex) {
            return 0.0f;
        }
    }

    public static Date toStartDate(Date date) {
        if(date == null) return null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    public static Date toEndDate(Date date) {
        if(date == null) return null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    public static Date toDate(String date, String format, String timezone) {
        SimpleDateFormat sdf = new SimpleDateFormat(!noData(format) ? format : "yyyy-MM-dd");
        if (!noData(timezone)) sdf.setTimeZone(TimeZone.getTimeZone(!noData(timezone) ? timezone : "UTC"));
        try {
            return sdf.parse(date);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new Date();
        }
    }
    public static Date toDate(Date date, String format, String timezone){
        return toDate(date.toString(), format, timezone);
    }
    public static Date toDate(String date, String format){
        return toDate(date, format, null);
    }
    public static Date toDate(Date date, String format){
        return toDate(date.toString(), format, null);
    }

    public static Date getDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            return sdf.parse(sdf.format(new Date()).toString());
        } catch (Exception ex) {
            return new Date();
        }
    }

    public static Date getCurrentDateWithoutTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(sdf.format(new Date()).toString());
        } catch (Exception ex) {
            return new Date();
        }
    }

    public static String getDateString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            return sdf.format(new Date());
        } catch (Exception ex) {
            return null;
        }
    }

    public static String getDateString(String timeZone) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
        try {
            return sdf.format(new Date());
        } catch (Exception ex) {
            return null;
        }
    }


    public static String getDateTimeString(String timeZone) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
        try {
            return sdf.format(new Date());
        } catch (Exception ex) {
            return null;
        }
    }

    public static String dateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.format(date);
        } catch (Exception ex) {
            return "";
        }
    }

    public static String dateToString(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.format(date);
        } catch (Exception ex) {
            return "";
        }
    }

    public static String get24HourTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            return sdf.format(new Date());
        } catch (Exception ex) {
            return null;
        }
    }

    public static String get24HourTime(String timeZone) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
        try {
            return sdf.format(new Date());
        } catch (Exception ex) {
            return null;
        }
    }


    public static int getRandomNumber(int min, int max) {
        if (min >= max) throw new IllegalArgumentException("max must be greater than min");
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public static String generatePassword(int length) {
        String charStr = "!@#$%^&*()ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz01234567890_<>{}[].,+";

        String passStr = "";
        for (int i = 0; i < length; i++) {
            int _rn = AppUtil.getRandomNumber(0, 82);
            passStr += charStr.charAt(_rn);
        }

        return passStr;
    }

    public static List<String> generateDateRange(String startDate, String endDate) {
        Date begin = toDate(startDate, "yyyy-MM-dd");
        Date end = toDate(endDate, "yyyy-MM-dd");
        List<String> list = new ArrayList();

        Date tmpDate = new Date(begin.getTime());
        list.add(dateToString(tmpDate));

        while (begin.compareTo(end) < 0) {
            begin = new Date(begin.getTime() + 86400000);
            tmpDate = new Date(begin.getTime());
            list.add(dateToString(tmpDate));
        }
        return list;
    }

    public static String getDuration(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) return null;
        Period p = Period.between(startDate, endDate);
        return p.getYears() + " Years " + p.getMonths() + " Months " + p.getDays() + " Days ";
    }

    public static String getDuration(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) return null;
        LocalDate begin = Instant.ofEpochMilli(startDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate end = Instant.ofEpochMilli(endDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        return getDuration(begin, end);
    }

    public static String getDuration(Date startDate, Date endDate, boolean isIncludingEndDate) {
        if (startDate == null || endDate == null) return null;
        LocalDate begin = Instant.ofEpochMilli(startDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate end = Instant.ofEpochMilli(endDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        return getDuration(begin, noData(isIncludingEndDate) ? end : end.plusDays(1));
    }

    public static long getDurationInDays(Date startDate, Date endDate,boolean isIncludingEndDate) {
        if (startDate == null || endDate == null) return -1;
        LocalDate begin = Instant.ofEpochMilli(startDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate end = Instant.ofEpochMilli(endDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        return ChronoUnit.DAYS.between(begin, noData(isIncludingEndDate) ? end : end.plusDays(1));
    }

    public static String getYearFromDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        try {
            return sdf.format(date);
        } catch (Exception ex) {
            return null;
        }
    }


    public static String findDuration(Date startDate, Date endDate) {
        Date begin = toDate(startDate, "yyyy-MM-dd");
        Date end = toDate(endDate, "yyyy-MM-dd");

        try {
            // Calculate time difference
            // in milliseconds
            long difference_In_Time
                    = end.getTime() - begin.getTime();

            // Calculate time difference in
            // seconds, minutes, hours, years,
            // and days
            long difference_In_Seconds
                    = (difference_In_Time
                    / 1000)
                    % 60;

            long difference_In_Minutes
                    = (difference_In_Time
                    / (1000 * 60))
                    % 60;

            long difference_In_Hours
                    = (difference_In_Time
                    / (1000 * 60 * 60))
                    % 24;

            long difference_In_Years
                    = (difference_In_Time
                    / (1000l * 60 * 60 * 24 * 365));

            long difference_In_Days
                    = (difference_In_Time
                    / (1000 * 60 * 60 * 24))
                    % 365;

            return difference_In_Years
                    + " years "
                    + difference_In_Days
                    + " days "
                    + difference_In_Hours
                    + " hours ";
        } catch (Exception ex) {

            return "";
        }

    }

    public static String getYears(Date startDate, Date endDate) {

        LocalDate begin = Instant.ofEpochMilli(startDate.getTime())
                .atZone(ZoneId.systemDefault()).toLocalDate();

        LocalDate end = Instant.ofEpochMilli(endDate.getTime())
                .atZone(ZoneId.systemDefault()).toLocalDate();

        Period p = Period.between(begin, end);
        return "" + p.getYears();
    }

    public static String toDateFunction(String date) {
        if (date != null && !date.trim().isEmpty()) return " TO_DATE('" + date.trim() + "','DD/MM/RRRR') ";
        else return " TO_DATE(null,'DD/MM/RRRR')";
    }

    public static String getCurrentDate() {
        return DD_MMM_YYYY_HH_MM_SS.format(Calendar.getInstance().getTime());
    }

    public static boolean isDateTime(String str) {
        if (str != null && str.length() == 10 && str.matches("^\\d{4}-\\d{2}-\\d{2}$"))
            return true;
        if (str != null && str.length() == 28
                && str.matches("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.\\d{3}\\+\\d{4}$")) {
            return true;
        }
        return false;
    }

    public static Date toSqlDate(String str) {
        if (str != null && str.length() == 10)
            return toSqlDateFromYYYYMMDD(str);
        if (str != null && str.length() == 28) {
            return toSqlDateFromYYYYMMDDTHHMMSSSSSZZZZ(str);
        }
        return null;
    }

    public static java.sql.Date toSqlDateFromYYYYMMDD(String str) {
        try {
            return java.sql.Date.valueOf(str);
        } catch (Exception ex) {
            return null;
        }
    }

    public static synchronized java.sql.Date toSqlDateFromYYYYMMDDTHHMMSSSSSZZZZ(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZZZ");
        try {
            return new java.sql.Date(simpleDateFormat.parse(str).getTime());
        } catch (Exception ex) {
            return null;
        }
    }

    public static boolean isValidFromAndToDate(Date fromDate, Date toDate) {
        if (null == fromDate || null == toDate) return false;
        return fromDate.before(toDate);
    }

    public String[] uploadFile(String dirPath, MultipartFile[] files) {
        String[] arr = new String[files.length];

        int idx = 0;
        for (MultipartFile file : files) {
            arr[idx++] = uploadFile(dirPath, file);
        }
        return arr;
    }

    public String uploadFile(String dirPath, MultipartFile file) {
        String[] realNames = file.getOriginalFilename().split("\\.");
        int len = realNames.length;
        String fileName = UUID.randomUUID().toString() + "." + realNames[len - 1];

        try {
            try {
                File dir = new File(dirPath);
                if (!dir.exists()) {
                    if (dir.mkdirs()) {
                    } else {
                        System.out.println("Failed to create directory!");
                    }
                }
            } catch (Exception e) {
            }
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            if (!file.getOriginalFilename().equals("foo.empty_file")) {
                Path path = Paths.get(dirPath + fileName);
                Files.write(path, bytes);
                return fileName;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void unZipFile(String srcPath, String destPath) throws IOException {

        File destDir = new File(destPath);
        byte[] buffer = new byte[1024];
        ZipInputStream zis = new ZipInputStream(new FileInputStream(srcPath));
        ZipEntry zipEntry = zis.getNextEntry();
        while (zipEntry != null) {
            File newFile = newZipEntryFile(destDir, zipEntry);
            if (zipEntry.isDirectory()) {
                if (!newFile.isDirectory() && !newFile.mkdirs())
                    throw new IOException("Failed to create directory " + newFile);
            } else {
                // fix for Windows-created archives
                File parent = newFile.getParentFile();
                if (!parent.isDirectory() && !parent.mkdirs())
                    throw new IOException("Failed to create directory " + parent);

                // write file content
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
            }
            zipEntry = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();
    }

    public static File newZipEntryFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName());

        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }
        return destFile;
    }


    public static String getTokenValue(HttpServletRequest request) {
        //printHeaderElements(request);
        try {
            return request.getHeader("authorization").split(" ")[1];
        } catch (Exception ex) {
            log.error("!!! Token not available on header. 1");
        }

        try {
            return request.getHeader("Authorization").split(" ")[1];
        } catch (Exception ex) {
            log.error("!!! Token not available on header. 2");
        }
        return "";
    }

    private static void printHeaderElements(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            System.out.println("Header Name - " + headerName + ", Value - " + request.getHeader(headerName));
        }
    }

    public static String getHeaderInfo(HttpServletRequest request, String keyName) {
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            if (key.equals(keyName)) return request.getHeader(key);
        }
        return "";
    }

    public static String getHeader(HttpServletRequest request, String key) {
        try {
            return request.getHeader(key.toLowerCase());
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return "";
        }
    }

    public static String getIpProxyIp(HttpServletRequest servletRequest) {
        String remoteAddr = servletRequest.getHeader("X-FORWARDED-FOR");
        if (remoteAddr == null || "".equals(remoteAddr)) remoteAddr = servletRequest.getRemoteAddr();
        return remoteAddr;
    }

    public static String getClientInfo(HttpServletRequest servletRequest) {
        return servletRequest.getHeader("User-Agent") == null ? "" : servletRequest.getHeader("User-Agent");
    }

    /*public static String getCookie(HttpServletRequest req, String key) {
        if (noData(req.getCookies())) return null;
        for (Cookie cookie : req.getCookies()) {
            if (cookie.getName().equals(key)) return cookie.getValue();
        }
        return null;
    }*/

    /**
     * regenerate and validate BD mobile number.
     *
     * @param number
     * @return mobile number with BD country code
     */
    public static String regenerateBdMobileNumber(String number) {
        // check mobile number validity
        if (null == number || number.isEmpty()) return null;
        number = number.trim();
        if (number.isEmpty() || number.equals("null") || number.equalsIgnoreCase("null")) return null;

        // if mobile number is incomplete means not starts with +880 (country-code)
        if (!number.startsWith("+880")) {
            if (number.startsWith("0")) number = "+88".concat(number);
            else if (number.startsWith("1")) number = "+880".concat(number);
            else if (number.startsWith("880")) number = "+".concat(number);
        }
        return number;
    }

    public static String regenerateAndValidateMobileNumber(String number) {
        if (null == number || number.isEmpty()) return null;
        // regenerate and check length
        number = regenerateBdMobileNumber(number);
        //if (number.length() != EnvUtil.getInt(Env.BD_PHONE_NUMBER_LENGTH, 14))
            //throw new GemsException.InvalidDataException("Invalid mobile number.");
        return number;
    }

    public static boolean isValidMobileNumber(String number) {
        if (null == number || number.isEmpty()) return false;
        // regenerate and check length
        number = regenerateBdMobileNumber(number);
        //if (number.length() != EnvUtil.getInt(Env.BD_PHONE_NUMBER_LENGTH, 14)) return false;
        // check number contains only number
        return number.replace("+", "").matches("[0-9]+");
    }

    public static void validateNID(String nid) {

        if (null == nid || nid.isEmpty())
            throw new AgentBankingException.InvalidDataException("nid number is null or missing.");

        // check length
        //if ((nid.length() != (EnvUtil.getInt(Env.BD_SMART_NID_LENGTH, 10))
                //&& nid.length() != (EnvUtil.getInt(Env.BD_OLD_NID_LENGTH, 17))))
            //throw new GemsException.InvalidDataException("nid number should be 10 0r 17 digit");
    }


    public static long getNumberFromString(String number) {
        if (null == number) return -1L;
        try {
            return Long.parseLong(number);
        } catch (NumberFormatException nfe) {
            return -1L;
        }
    }


    // VALIDITY ---------------------------------------------------------------------------------------------------------------------------------------

    public static boolean noData(String txt) {
        if (null == txt || txt.trim().isEmpty() || txt.equals("null") || txt.equalsIgnoreCase("null")) return true;
        return false;
    }

    public static boolean noData(Boolean b) {
        return b == null || !b;
    }

    public static boolean noData(Object obj) {
        return obj == null;
    }

    public static boolean noData(Map map) {
        return map == null || map.isEmpty();
    }

    public static boolean noData(Collection<?> c) {
        return c == null || c.isEmpty();
    }

    /*public static boolean isTransient(Class<?> clz, String fieldName) {
        if (new ArrayList<>(Arrays.asList(FieldUtils.getFieldsWithAnnotation(clz, Transient.class))).stream().anyMatch(x -> x.getName().equalsIgnoreCase(fieldName)))
            return true;
        return false;
    }*/


    public static boolean isNumeric(String strNum) {
        if (strNum == null) return false;
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isNumericBangla(String input) {
        // Define a regex pattern to match Bangla numbers (০-৯)
        String regex = "^[০-৯]+$";
        // Compile the regex pattern
        Pattern pattern = Pattern.compile(regex);
        // Match the input against the pattern
        Matcher matcher = pattern.matcher(input);
        // Return true if the input matches the pattern, indicating a valid Bangla number
        return matcher.matches();
    }

    /**
     * @param number
     * @return true if number is grater than equal 0
     */
    public static boolean isPositiveNumber(Integer number) {
        if (!noData(number) && number >= 0) return true;
        return false;
    }

    public static boolean isPositiveNumber(Long number) {
        if (!noData(number) && number >= 0) return true;
        return false;
    }

    public static boolean isPositiveNumber(Float number) {
        if (!noData(number) && number >= 0) return true;
        return false;
    }

    public static boolean isPositiveNumber(Double number) {
        if (!noData(number) && number >= 0) return true;
        return false;
    }

    /**
     * @param number
     * @return true if number is grater than 0
     */
    public static boolean isNonZeroPositiveNumber(Integer number) {
        if (!noData(number) && number > 0) return true;
        return false;
    }

    public static boolean isNonZeroPositiveNumber(Long number) {
        if (!noData(number) && number > 0) return true;
        return false;
    }

    public static boolean isNonZeroPositiveNumber(Float number) {
        if (!noData(number) && number > 0) return true;
        return false;
    }

    public static boolean isNonZeroPositiveNumber(Double number) {
        if (!noData(number) && number > 0) return true;
        return false;
    }

    public static boolean isValidEmail(String email) {
        //return EmailValidator.getInstance().isValid(email);
        return false;
    }

    /*public static boolean isValidMeta(MetaModel meta) {
        return !noData(meta) && !noData(meta.getLimit()) && !noData(meta.getPage());
    }*/

    public static String toSqlField(String text) {
        return !noData(text) ? text.replaceAll("([^_A-Z])([A-Z])", "$1_$2").toLowerCase() : "";
    }

    public static String toSortQuery(Pageable pageable) {
        return pageable.getSort().stream().map(s -> {
            return (toSqlField(s.getProperty()).concat(" ").concat(s.getDirection().name())).toLowerCase();
        }).collect(Collectors.joining(","));
    }

    /*public static FileDTO validateFileDTO(FileDTO dto) {
        return (!noData(dto) && !noData(dto.getPreviewUrl())) ? dto : null;
    }*/

    public static String convertInchToHeight(Integer height) {
        if (null == height || height < 0) return "";
        int feet = height / 12;
        int inch = height % 12;
        StringBuilder stringBuilder = new StringBuilder();
        if (feet > 0) {
            stringBuilder.append(feet);
            stringBuilder.append(" feet ");
        }
        if (inch > 0) {
            stringBuilder.append(inch);
            stringBuilder.append(" inch");
        }
        return stringBuilder.toString();
    }

    public static Set<String> convertCommaSeparatedStringToStringSet(String commaSeparatedString) {
        if (noData(commaSeparatedString)) return new HashSet<>();
        return Arrays.stream(commaSeparatedString.split(","))
                .map(String::trim) // Remove any leading/trailing whitespace
                .collect(Collectors.toSet());
    }

    public static String convertNumberBnToEn(String banglaNumber) {
        // Define a mapping of Bangla digits to English digits
        Map<Character, Character> banglaToEnglishMap = new HashMap<>();
        banglaToEnglishMap.put('০', '0');
        banglaToEnglishMap.put('১', '1');
        banglaToEnglishMap.put('২', '2');
        banglaToEnglishMap.put('৩', '3');
        banglaToEnglishMap.put('৪', '4');
        banglaToEnglishMap.put('৫', '5');
        banglaToEnglishMap.put('৬', '6');
        banglaToEnglishMap.put('৭', '7');
        banglaToEnglishMap.put('৮', '8');
        banglaToEnglishMap.put('৯', '9');

        // Initialize a StringBuilder to store the English number
        StringBuilder englishNumber = new StringBuilder();

        // Convert each character of the Bangla number to English
        for (char banglaDigit : banglaNumber.toCharArray()) {
            if (banglaToEnglishMap.containsKey(banglaDigit)) englishNumber.append(banglaToEnglishMap.get(banglaDigit));
            else englishNumber.append(banglaDigit);
        }
        return englishNumber.toString();
    }

    public String getUuidUtil(){
        return UUID.randomUUID().toString();
    }

}


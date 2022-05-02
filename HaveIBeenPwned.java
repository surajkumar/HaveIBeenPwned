import javax.xml.bind.DatatypeConverter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

public class HaveIBeenPwned {
    private static final Logger L = Logger.getLogger(HaveIBeenPwned.class.getName());
    private static final String API_URL = "https://api.pwnedpasswords.com/range/";

    public static boolean pwned(String password) {
        var hashed = sha1(password.getBytes());
        if (hashed != null) {
            try {
                var sb = new StringBuilder();
                var url = new URL(API_URL + hashed.substring(0, 5));
                var http = (HttpURLConnection) url.openConnection();
                http.setRequestMethod("GET");
                http.setDoOutput(true);
                if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    try (var reader = new BufferedReader(new InputStreamReader(http.getInputStream(), StandardCharsets.UTF_8))) {
                        var read = 0;
                        while ((read = reader.read()) >= 0) {
                            sb.append((char) read);
                        }
                    }
                    return sb.toString().contains(hashed.substring(5));
                }
            } catch (Exception e) {
                L.severe(e.getMessage());
            }
        }
        return false;
    }

    public static boolean accountPwned(String username) {
        try {
            var url = new URL("https://haveibeenpwned.com/api/v3/breachedaccount/" + username);
            var http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("GET");
            http.setDoOutput(true);
            return http.getResponseCode() == HttpURLConnection.HTTP_OK;
        } catch (Exception e) {
            L.severe(e.getMessage());
        }
        return false;
    }

    public static String sha1(byte[] input) {
        try {
            return DatatypeConverter.printHexBinary(MessageDigest.getInstance("SHA-1").digest(input));
        } catch (NoSuchAlgorithmException e) {
            L.severe(e.getMessage());
        }
        return null;
    }
}

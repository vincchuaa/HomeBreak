package COM2006.project;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


public class PasswordManager {

    /* Resorted to finding an online resource for generating hashed passwords due to unfamiliarity
     * of implementing such an algorithm.
     * Based on A. Sinha's work on StackOverflow (https://stackoverflow.com/a/33085670)
     * Published on October 12 2015.
     * Accessed on November 24 2021.
     * @return - A password that is hashed with the salt provided with the SHA-512 algorithm.
     */
    public static String hashPassword(String passwordToHash, String salt){
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
    /* Based on the community wiki of StackOverflow (https://stackoverflow.com/a/157202)
     * Published on October 1 2008, last edited on November 12 2020.
     * Accessed on November 24 2021.
     * @return - An alphanumeric salt of the specified length.
     */
    public static String generateSalt(int len){
        final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(len);
        for(int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

}

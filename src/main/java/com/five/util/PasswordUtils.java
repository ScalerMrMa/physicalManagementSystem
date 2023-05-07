package com.five.util;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author MrMa
 * @version 1.0
 * @description   密码加密算法 SHA-256 算法
 */

public class PasswordUtils {

   public static String hashPassword(String password) {
      try {
         MessageDigest md = MessageDigest.getInstance("SHA-256");
         byte[] hashInBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));

         // 将 byte 数组转换为十六进制字符串
         StringBuilder sb = new StringBuilder();
         for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
         }
         return sb.toString();

      } catch (NoSuchAlgorithmException e) {
         throw new RuntimeException("SHA-256 algorithm is not available.", e);
      }
   }
}
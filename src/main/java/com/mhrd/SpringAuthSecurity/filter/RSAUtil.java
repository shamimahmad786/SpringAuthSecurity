package com.mhrd.SpringAuthSecurity.filter;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSAUtil {
//	 private static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDT1iw17TlN0is7XozHv0poYiJkSKxVgpK2ZrzXmbwF7CAlWIqegV3GNtjsbb4DDVIiTsQdwb0ZMUzM/Ha9EMjBbvsTwR3FZN5FWKrlGdIheGtOFCh8raN8ic2zuJ8ljtERhv4crk31KQdcexDwd/qSb4k73HfWtFUR4HtAhd9EMwIDAQAB";
	//    private static String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANPWLDXtOU3SKztejMe/SmhiImRIrFWCkrZmvNeZvAXsICVYip6BXcY22OxtvgMNUiJOxB3BvRkxTMz8dr0QyMFu+xPBHcVk3kVYquUZ0iF4a04UKHyto3yJzbO4nyWO0RGG/hyuTfUpB1x7EPB3+pJviTvcd9a0VRHge0CF30QzAgMBAAECgYEAhMYnvcEreqhxamvPx18Rjy17KuoWAh6uQF9Sm7wDCp8+YsoFUGX7VcKI4l/Cif0ubsx5xcDp+kFZRt4yujwr51Xs68HtO/JNO4xxA4jhl3LzkdyzCOio3c+GEBP8L4flOaPeIZkVIZACEep9WFjYuruIHhFYKuDLBAmQOPmju+ECQQDrRpCTUPAQqSliZUGT9j/3CcVDXxTEbr000hmaUUrccfXbWecpo0de2I87c3tNLlaeniFnuflsCOdWRaTBPtkxAkEA5n8PkpTAJHFZwALROWhiO/qni3w/XCXbEa0F0RK1H1Fuai+jnWqdNHzasbAGQGXqglsxIdIfti8uaA4q8owaowJADpbxoDEEsgPLbS6aQnKixM72TJc40nWLhhsBO3CPE9x9QnzwuMRHSLplJ2qh2sdk17E2oRgHP4vNzKvE67baAQJARE7XcJtArgwhivPKyXaT1i6cRIwXwtk9KOnb1W/z2UoqrLFdjaMw34M41HvT/nW1n9gioWFCIJ2u5Qt90s+OfQJACrG9N9zikcHQ6UgFbqlk9Pyxx7mTbfC90qrNpUgXcrQyagFCpm4BViMMJRilfQG4nyXyDCag9uFaHTkpaXUV2A==";

	    public static PublicKey getPublicKey(String base64PublicKey){
	        PublicKey publicKey = null;
	        try{
	            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicKey.getBytes()));
	            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	            publicKey = keyFactory.generatePublic(keySpec);
	            return publicKey;
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        } catch (InvalidKeySpecException e) {
	            e.printStackTrace();
	        }
	        return publicKey;
	    }

	    public static PrivateKey getPrivateKey(String base64PrivateKey){
	        PrivateKey privateKey = null;
	        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64PrivateKey.getBytes()));
	        KeyFactory keyFactory = null;
	        try {
	            keyFactory = KeyFactory.getInstance("RSA");
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        }
	        try {
	            privateKey = keyFactory.generatePrivate(keySpec);
	        } catch (InvalidKeySpecException e) {
	            e.printStackTrace();
	        }
	        return privateKey;
	    }

	    public static byte[] encrypt(String data, String publicKey) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
	        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
	        cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey));
	        return cipher.doFinal(data.getBytes());
	    }

	    public static String decrypt(byte[] data, PrivateKey privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
	        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
	        cipher.init(Cipher.DECRYPT_MODE, privateKey);
	        return new String(cipher.doFinal(data));
	    }

	    public static String decrypt(String data, String base64PrivateKey) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
	      
	    	// System.out.println("data---->"+data);
	    	return decrypt(Base64.getDecoder().decode(data.getBytes()), getPrivateKey(base64PrivateKey));
	    }

//	    public static void main(String[] args) throws IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, BadPaddingException {
//	        try {
//	            String encryptedString = Base64.getEncoder().encodeToString(encrypt("Dhiraj is the author", publicKey));
//	            // System.out.println(encryptedString);
//	            String decryptedString = RSAUtil.decrypt(encryptedString, privateKey);
//	            // System.out.println(decryptedString);
//	        } catch (NoSuchAlgorithmException e) {
//	            System.err.println(e.getMessage());
//	        }

//	    }
}

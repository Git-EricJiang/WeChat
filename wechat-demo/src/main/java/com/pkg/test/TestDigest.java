package com.pkg.test;

import java.math.BigInteger;
import java.security.MessageDigest;

public class TestDigest {
	public static final String KEY_MD5 = "MD5";

	public static final String KEY_SHA = "SHA";
	
	/*
	MD5(Message Digest algorithm 5，信息摘要算法) 
	通常我们不直接使用上述MD5加密。通常将MD5产生的字节数组交给BASE64再加密一把，得到相应的字符串
	Digest:汇编
	*/
	public static String getResultMD5(String inputStr) {
		System.out.println("=======MD5加密前的数据:" + inputStr);
		BigInteger bigInteger = null;

		try {
			MessageDigest md = MessageDigest.getInstance(KEY_MD5);
			byte[] inputData = inputStr.getBytes();
//			md.update(inputData);
			bigInteger = new BigInteger(md.digest(inputData));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("MD5加密后:" + bigInteger.toString(16));
		return bigInteger.toString(16);
	}

	/*
	SHA(Secure Hash Algorithm，安全散列算法），数字签名等密码学应用中重要的工具，
	被广泛地应用于电子商务等信息安全领域。虽然，SHA与MD5通过碰撞法都被破解了， 
	但是SHA仍然是公认的安全加密算法，较之MD5更为安全*/
	public static String getResultSHA(String inputStr) {
		System.out.println("=======SHA加密前的数据:" + inputStr);
		BigInteger bigInteger = null;

		try {
			MessageDigest md = MessageDigest.getInstance(KEY_SHA);
			byte[] inputData = inputStr.getBytes();
//			md.update(inputData);
			bigInteger = new BigInteger(md.digest(inputData));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("SHA加密后:" + bigInteger.toString(32));
		return bigInteger.toString(16);
	}

	public static void main(String args[]) {
		try {
			String inputStr = "你好";
			getResultMD5(inputStr);
			getResultSHA(inputStr);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

/**
 * 有错.
 */
class BASE64 {
    /**  
     * BASE64解密  
     *   
     * @param key  
     * @return  
     * @throws Exception  
     */  
    public static byte[] decryptBASE64(String key) throws Exception {   
        return  BASE64.decryptBASE64(key);   
    }   
      
    /**  
     * BASE64加密  
     *   
     * @param key  
     * @return  
     * @throws Exception  
     */  
    public static String encryptBASE64(byte[] key) throws Exception {   
        return BASE64.encryptBASE64(key);   
    }  

    public static void main(String[] args) {
        
     String  str="12345678";

        try {
        String  result1= BASE64.encryptBASE64(str.getBytes());
         System.out.println("result1=====加密数据=========="+result1);
         
         
         byte  result2[]= BASE64.decryptBASE64(result1);
         String  str2=new String(result2);
         System.out.println("str2========解密数据========"+str2);
    } catch (Exception e) {
        e.printStackTrace();
    }
        
        
        
    }

}
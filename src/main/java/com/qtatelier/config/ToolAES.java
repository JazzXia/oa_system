package com.qtatelier.config;

import org.apache.commons.codec.binary.Base64;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;

/**
 * AES的加密和解密
 */
public class ToolAES
{
	//约定的固定密钥
	private static final String KEY = "1098765432100000";

	//约定的固定密钥
	private static final String KEY3 = "1100293786088000";
	//算法
	private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";
	
	public static final String public_key="abcdefgabcdefg12";

	/**
	 * aes解密
	 * @param encrypt 内容
	 * @return
	 * @throws Exception
	 */
	public static String aesDecrypt( String encrypt )
	{
		try
		{
			return aesDecrypt( encrypt, KEY );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * aes加密
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public static String aesEncrypt( String content )
	{
		try
		{
			return aesEncrypt( content, KEY );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 将byte[]转为各种进制的字符串
	 * @param bytes byte[]
	 * @param radix 可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制
	 * @return 转换后的字符串
	 */
	public static String binary( byte[] bytes, int radix )
	{
		return new BigInteger( 1, bytes ).toString( radix );// 这里的1代表正数  
	}

	/**
	 * base 64 encode
	 * @param bytes 待编码的byte[]
	 * @return 编码后的base 64 code
	 */
	public static String base64Encode( byte[] bytes )
	{
		return Base64.encodeBase64String( bytes );
	}

	/**
	 * base 64 decode
	 * @param base64Code 待解码的base 64 code
	 * @return 解码后的byte[]
	 * @throws Exception
	 */
	public static byte[] base64Decode( String base64Code ) throws Exception
	{
		return StringUtils.isEmpty( base64Code ) ? null : new BASE64Decoder().decodeBuffer( base64Code );
	}

	/**
	 * AES加密
	 * @param content 待加密的内容
	 * @param encryptKey 加密密钥
	 * @return 加密后的byte[]
	 * @throws Exception
	 */
	public static byte[] aesEncryptToBytes( String content, String encryptKey ) throws Exception
	{
		KeyGenerator kgen = KeyGenerator.getInstance( "AES" );
		kgen.init( 128 );
		Cipher cipher = Cipher.getInstance( ALGORITHMSTR );
		cipher.init( Cipher.ENCRYPT_MODE, new SecretKeySpec( encryptKey.getBytes(), "AES" ) );

		return cipher.doFinal( content.getBytes( "utf-8" ) );
	}

	/**
	 * AES加密为base 64 code
	 * @param content 待加密的内容
	 * @param encryptKey 加密密钥
	 * @return 加密后的base 64 code
	 * @throws Exception
	 */
	public static String aesEncrypt( String content, String encryptKey ) throws Exception
	{
		return base64Encode( aesEncryptToBytes( content, encryptKey ) );
	}

	/**
	 * AES解密
	 * @param encryptBytes 待解密的byte[]
	 * @param decryptKey 解密密钥
	 * @return 解密后的String
	 * @throws Exception
	 */
	public static String aesDecryptByBytes( byte[] encryptBytes, String decryptKey ) throws Exception
	{
		KeyGenerator kgen = KeyGenerator.getInstance( "AES" );
		kgen.init( 128 );

		Cipher cipher = Cipher.getInstance( ALGORITHMSTR );
		cipher.init( Cipher.DECRYPT_MODE, new SecretKeySpec( decryptKey.getBytes(), "AES" ) );
		byte[] decryptBytes = cipher.doFinal( encryptBytes );
		return new String( decryptBytes );
	}

	/**
	 * 将base 64 code AES解密
	 * @param encryptStr 待解密的base 64 code
	 * @param decryptKey 解密密钥
	 * @return 解密后的string
	 * @throws Exception
	 */
	public static String aesDecrypt( String encryptStr, String decryptKey ) throws Exception
	{
		return StringUtils.isEmpty( encryptStr ) ? null : aesDecryptByBytes( base64Decode( encryptStr ), decryptKey );
	}

	/**
	 * 测试
	 */
//	public static void main( String[] args ) throws Exception
//	{
//
//		String str="aaaaa";
//		String encrypt = aesEncrypt( str, ToolAES.public_key );
//		System.out.println( "加密后密文：" + encrypt );
//
//		
//		String s = ToolUnicode.stringToUnicode(encrypt);
//	    System.out.println(new StringBuilder().append("编码：").append(s).toString());
//	    String s3=URLEncoder.encode(s,"utf-8");
//	    System.out.println(s3);
//	    String s1 = ToolUnicode.unicodeToString(s);
//	    System.out.println(new StringBuilder().append("解码：").append(s1).toString());
//	    
//	    String s2=aesDecrypt(s1,ToolAES.public_key);
//	    System.out.println("解密后密文："+s2);
//	}

}
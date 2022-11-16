package com.jd.survey.com.util;

import java.text.DecimalFormat;
import org.springframework.context.i18n.LocaleContextHolder;


public class StringUtils extends org.springframework.util.StringUtils {

	/**
	 * 낙타표기법 형식의 문자열을 언더바 형식으로 변환
	 */
	public static String replaceCamelToUnderscoer(String str) {
		return str.replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();
	}
	
	/**
	 * 텍스트입력시 줄바꿈태그 변환
	 */
	public static String replaceCr2Br(String str) {
		if(!StringUtils.hasText(str)) return str;
		return str.replace("\r\n", "<br>")
				.replace("\r", "<br>")
				.replace("\n", "<br>");
	}
	
	/**
	 * 국제화코드에 맞는 화폐단위 반환
	 */
	public static String monetaryUnit(String locale) {
		String monetaryUnit = "";
		if(locale == null) locale = LocaleContextHolder.getLocale().getLanguage();
		monetaryUnit="억원";
		/*switch() {
			case "ko":monetaryUnit="억원";break;
			case "en":monetaryUnit="만달러";break;
			case "ja":monetaryUnit="억엔";break;
			case "zh":monetaryUnit="억위안";break;
			case "de":monetaryUnit="만유로";break;
			case "fr":monetaryUnit="만유로";break;
		}*/
		return monetaryUnit;
	}
	
	/**
	 * 임시비밀번호로 사용하기위한 랜덤 문자열을 반환
	 * @param passwordLength 반환될 문자열 길이
	 * @return 랜덤하게 생성된 문자열
	 */
	public static String getRandomString(int returnLength){
        String patch = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        char[] temp = new char[returnLength];
        java.util.Random ran = new java.util.Random();
        int index = 0;
        while(index < returnLength){
            temp[index] = patch.charAt(ran.nextInt(patch.length()));
            index++;
        }
        return String.copyValueOf(temp);
	}
	
    
    /**
     * 문자열 PrimaryKey값의 다음 순번의 PrimaryKey 값 만들기
     * @param key 현재 PrimaryKey 값
     * @param length prefix, suffix 포함 문자열 길이
     * @param prefix
     * @param suffix
     * @return 다음 순번의 PrimaryKey 값
     */
    public static String generatePrimarySequence(String key, int length, String prefix, String suffix){
        
        DecimalFormat df = null;
        long nextNumber = 1;
        if(prefix != null && prefix.length() > 0 && suffix != null && suffix.length() > 0){
            df = new DecimalFormat(StringUtils.repeatString("0", length - prefix.length() - suffix.length()));
            df.setPositivePrefix(prefix);
            df.setPositiveSuffix(suffix);

            if(key == null || "".equals(key)){
                nextNumber = 1;
            }else{
                try{
                    nextNumber = Long.parseLong(key.substring(prefix.length(), key.length() - suffix.length())) + 1;
                }catch(java.lang.NumberFormatException e){
                    nextNumber = 1;
                }
            }
            
        }else if(prefix != null && prefix.length() > 0){
            df = new DecimalFormat(StringUtils.repeatString("0", length - prefix.length()));
            df.setPositivePrefix(prefix);

            if(key == null || "".equals(key)){
                nextNumber = 1;
            }else{
                try{
                    nextNumber = Long.parseLong(key.substring(prefix.length())) + 1;
                }catch(java.lang.NumberFormatException e){
                    nextNumber = 1;
                }
            }
            
        }else if(suffix != null && suffix.length() > 0){
            df = new DecimalFormat(StringUtils.repeatString("0", length - suffix.length()));
            df.setPositiveSuffix(suffix);

            if(key == null || "".equals(key)){
                nextNumber = 1;
            }else{
                try{
                    nextNumber = Long.parseLong(key.substring(0, key.length() - suffix.length())) + 1;
                }catch(java.lang.NumberFormatException e){
                    nextNumber = 1;
                }
            }
            
        }else{
            df = new DecimalFormat(StringUtils.repeatString("0", length));

            if(key == null || "".equals(key)){
                nextNumber = 1;
            }else{
                try{
                    nextNumber = Long.parseLong(key) + 1;
                }catch(java.lang.NumberFormatException e){
                    nextNumber = 1;
                }
            }
        } 
        
        return df.format(nextNumber);
    }
    
    
    /**
     * 지정된 문자 또는 문자열을 지정 횟수만큼 이어붙이기
     * @param chr
     * @param count
     * @return
     */
    public static String repeatString(String chr, int count){
        StringBuffer sb = new StringBuffer();
        for(int i=0; i < count; i++){
            sb.append(chr);
        }
        
        return sb.toString();
    }
    	
	public static String strCutByte(String str, int byteSize, String surfix) {
		int rSize = 0;
		int len = 0;
		
		if(str.getBytes().length > byteSize) {
			for( ; rSize < str.length(); rSize++ ) {
				if(str.charAt(rSize) > 0x007F) len += 2;
				else len++;
				if(len > byteSize) break;
			}
			str = str.substring( 0, rSize ) + (surfix == null ? "" : surfix);
		}
		return str;
	}
	
}

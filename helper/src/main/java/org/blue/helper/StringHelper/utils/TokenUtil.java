package org.blue.helper.StringHelper.utils;

import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

public class TokenUtil {
    private static final String nos[] = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9",};
    private static final String Letters[] = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w",
            "x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S",
            "T","U","V","W","X","Y","Z"};

    public static String getToken(){
        Date date=new Date();
        String randomId=UUID.randomUUID().toString().replace("-", "");
        return MD5Util.sign(randomId,date.toString());
    }

    /**
     * 获取五个字母+五个数字的随机字符串
     * @return
     */
    public static String getRandomStr(){
        String randomStr="";
        StringBuilder sbNo=new StringBuilder();
        StringBuilder sbLetter=new StringBuilder();
        for (int i=0;i<=5;i++){
            int noSub= (int)(Math.random()*10);
            int letterSub= (int)(Math.random()*26);
            sbNo.append(nos[noSub]);
            sbLetter.append(Letters[letterSub]);
        }
        randomStr=sbLetter.toString()+sbNo.toString();
        return randomStr;
    }



    public static void main(String[] args) {
//        int stop=0;
//        int j=0;
//        while (true){
//            j++;
//            int i= (int)(Math.random()*51);
//            System.out.println(i);
//            if(i==stop || j==1000){
//                break;
//            }
//        }
        System.out.println(getRandomStr());
    }
}

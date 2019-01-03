package org.blue.helper.StringHelper.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipException;

public class BruteForce {
    private static final Logger logger = LoggerFactory.getLogger(BruteForce.class);
    private static String prod = "";//执行循环操作找出来的与真实密码相等的字符串
    private static final String[] elements = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
            "@", "#", "$", "%", "&", "*", ".", "_", "-"};
    public static Boolean b;


    public static void setB(boolean flage) {
        synchronized (b) {
            b = flage;
        }
    }

    private static void p(String pwd) throws Exception {
        ZipUtil.unzip("F:\\Download\\temp\\t.zip", "C:\\Users\\My\\Desktop\\tt", pwd);
        logger.info("###############密码就是：" + pwd);
    }

    public static void bruteForce() throws InterruptedException {
        int length = elements.length;
        //循环遍历出数组中的元素，列出数组中的字符可以组成的所有一位字符串，共62^1种可能
        boolean b = true;
        do {
            Thread two = new Thread(new Runnable() {
                @Override
                public void run() {
                    if (b) {
                        logger.info("===============开始尝试2位数密码");
                        for (int i = 0; i < length; i++) {
                            for (int j = 0; j < length; j++) {
                                prod = elements[i] + elements[j];
                                try {
                                    p(prod);
                                    BruteForce.setB(false);
                                    break;
                                } catch (Exception e) {
                                    if (e.getClass().equals(ZipException.class)) {
                                        continue;
                                    }
                                }
                            }
                        }
                        logger.info("===============2位数密码尝试完毕");
                    }
                }
            });
            Thread three = new Thread(new Runnable() {
                @Override
                public void run() {
                    if (b) {
                        logger.info("===============开始尝试3位数密码");
                        for (int i = 0; i < length; i++) {
                            for (int j = 0; j < length; j++) {
                                for (int k = 0; k < length; k++) {
                                    prod = elements[i] + elements[j] + elements[k];
                                    try {
                                        p(prod);
                                        BruteForce.setB(false);
                                        break;
                                    } catch (Exception e) {
                                        if (e.getClass().equals(ZipException.class)) {
                                            continue;
                                        }
                                    }
                                }
                            }
                        }
                        logger.info("===============3位数密码尝试完毕");
                    }
                }
            });
            Thread four = new Thread(new Runnable() {
                @Override
                public void run() {
                    if (b) {
                        logger.info("===============开始尝试4位数密码");
                        for (int i = 0; i < length; i++) {
                            for (int j = 0; j < length; j++) {
                                for (int k = 0; k < length; k++) {
                                    for (int l = 0; l < length; l++) {
                                        prod = elements[i] + elements[j] + elements[k] + elements[l];
                                        try {
                                            p(prod);
                                            BruteForce.setB(false);
                                            System.out.println("=================4");
                                            break;
                                        } catch (Exception e) {
                                            if (e.getClass().equals(ZipException.class)) {
                                                continue;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        logger.info("===============4位数密码尝试完毕");
                    }
                }
            });
            Thread five = new Thread(new Runnable() {
                @Override
                public void run() {
                    if (b) {
                        logger.info("===============开始尝试5位数密码");
                        for (int i = 0; i < length; i++) {
                            for (int j = 0; j < length; j++) {
                                for (int k = 0; k < length; k++) {
                                    for (int l = 0; l < length; l++) {
                                        for (int m = 0; m < length; m++) {
                                            prod = elements[i] + elements[j] + elements[k] + elements[l] + elements[m];
                                            try {
                                                p(prod);
                                                BruteForce.setB(false);
                                                break;
                                            } catch (Exception e) {
                                                if (e.getClass().equals(ZipException.class)) {
                                                    continue;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        logger.info("===============5位数密码尝试完毕");
                    }
                }
            });
            Thread six = new Thread(new Runnable() {
                @Override
                public void run() {
                    if (b) {
                        logger.info("===============开始尝试6位数密码");
                        for (int i = 0; i < length; i++) {
                            for (int j = 0; j < length; j++) {
                                for (int k = 0; k < length; k++) {
                                    for (int l = 0; l < length; l++) {
                                        for (int m = 0; m < length; m++) {
                                            for (int n = 0; n < length; n++) {
                                                prod = elements[i] + elements[j] + elements[k] + elements[l] + elements[m] + elements[n];
                                                try {
                                                    p(prod);
                                                    BruteForce.setB(false);
                                                    break;
                                                } catch (Exception e) {
                                                    if (e.getClass().equals(ZipException.class)) {
                                                        continue;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        logger.info("===============6位数密码尝试完毕");
                    }
                }
            });
            Thread seven = new Thread(new Runnable() {
                @Override
                public void run() {
                    if (b) {
                        logger.info("===============开始尝试7位数密码");
                        for (int i = 0; i < length; i++) {
                            for (int j = 0; j < length; j++) {
                                for (int k = 0; k < length; k++) {
                                    for (int l = 0; l < length; l++) {
                                        for (int m = 0; m < length; m++) {
                                            for (int n = 0; n < length; n++) {
                                                for (int o = 0; o < length; o++) {
                                                    prod = elements[i] + elements[j] + elements[k] + elements[l] + elements[m] + elements[n] + elements[o];
                                                    try {
                                                        p(prod);
                                                        BruteForce.setB(false);
                                                        break;
                                                    } catch (Exception e) {
                                                        if (e.getClass().equals(ZipException.class)) {
                                                            continue;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        logger.info("===============7位数密码尝试完毕");
                    }
                }
            });
            Thread eight = new Thread(new Runnable() {
                @Override
                public void run() {
                    if (b) {
                        logger.info("===============开始尝试8位数密码");
                        for (int i = 0; i < length; i++) {
                            for (int j = 0; j < length; j++) {
                                for (int k = 0; k < length; k++) {
                                    for (int l = 0; l < length; l++) {
                                        for (int m = 0; m < length; m++) {
                                            for (int n = 0; n < length; n++) {
                                                for (int o = 0; o < length; o++) {
                                                    for (int p = 0; p < length; p++) {
                                                        prod = elements[i] + elements[j] + elements[k] + elements[l] + elements[m] + elements[n] + elements[o] + elements[p];
                                                        try {
                                                            p(prod);
                                                            BruteForce.setB(false);
                                                            break;
                                                        } catch (Exception e) {
                                                            if (e.getClass().equals(ZipException.class)) {
                                                                continue;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        logger.info("===============8位数密码尝试完毕");
                    }
                }
            });
            Thread nine = new Thread(new Runnable() {
                @Override
                public void run() {
                    if (b) {
                        logger.info("===============开始尝试9位数密码");
                        for (int i = 0; i < length; i++) {
                            for (int j = 0; j < length; j++) {
                                for (int k = 0; k < length; k++) {
                                    for (int l = 0; l < length; l++) {
                                        for (int m = 0; m < length; m++) {
                                            for (int n = 0; n < length; n++) {
                                                for (int o = 0; o < length; o++) {
                                                    for (int p = 0; p < length; p++) {
                                                        for (int q = 0; q < length; q++) {
                                                            prod = elements[i] + elements[j] + elements[k] + elements[l] + elements[m] + elements[n] + elements[o] + elements[p] + elements[q];
                                                            try {
                                                                p(prod);
                                                                BruteForce.setB(false);
                                                                break;
                                                            } catch (Exception e) {
                                                                if (e.getClass().equals(ZipException.class)) {
                                                                    continue;
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        logger.info("===============9位数密码尝试完毕");
                    }
                }
            });
            Thread ten = new Thread(new Runnable() {
                @Override
                public void run() {
                    if (b) {
                        logger.info("===============开始尝试10位数密码");
                        for (int i = 0; i < length; i++) {
                            for (int j = 0; j < length; j++) {
                                for (int k = 0; k < length; k++) {
                                    for (int l = 0; l < length; l++) {
                                        for (int m = 0; m < length; m++) {
                                            for (int n = 0; n < length; n++) {
                                                for (int o = 0; o < length; o++) {
                                                    for (int p = 0; p < length; p++) {
                                                        for (int q = 0; q < length; q++) {
                                                            for (int r = 0; r < length; r++) {
                                                                prod = elements[i] + elements[j] + elements[k] + elements[l] + elements[m] + elements[n] + elements[o] + elements[p] + elements[q] + elements[r];
                                                                try {
                                                                    p(prod);
                                                                    BruteForce.setB(false);
                                                                    break;
                                                                } catch (Exception e) {
                                                                    if (e.getClass().equals(ZipException.class)) {
                                                                        continue;
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        logger.info("===============10位数密码尝试完毕");
                    }
                }
            });
            Thread eleven = new Thread(new Runnable() {
                @Override
                public void run() {
                    if (b) {
                        logger.info("===============开始尝试11位数密码");
                        for (int i = 0; i < length; i++) {
                            for (int j = 0; j < length; j++) {
                                for (int k = 0; k < length; k++) {
                                    for (int l = 0; l < length; l++) {
                                        for (int m = 0; m < length; m++) {
                                            for (int n = 0; n < length; n++) {
                                                for (int o = 0; o < length; o++) {
                                                    for (int p = 0; p < length; p++) {
                                                        for (int q = 0; q < length; q++) {
                                                            for (int r = 0; r < length; r++) {
                                                                for (int t = 0; t < length; t++) {
                                                                    prod = elements[i] + elements[j] + elements[k] + elements[l] + elements[m] + elements[n] + elements[o] + elements[p] + elements[q] + elements[r] + elements[t];
                                                                    try {
                                                                        p(prod);
                                                                        BruteForce.setB(false);
                                                                        break;
                                                                    } catch (Exception e) {
                                                                        if (e.getClass().equals(ZipException.class)) {
                                                                            continue;
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        logger.info("===============11位数密码尝试完毕");
                    }
                }
            });
            Thread twelve = new Thread(new Runnable() {
                @Override
                public void run() {
                    if (b) {
                        logger.info("===============开始尝试12位数密码");
                        for (int i = 0; i < length; i++) {
                            for (int j = 0; j < length; j++) {
                                for (int k = 0; k < length; k++) {
                                    for (int l = 0; l < length; l++) {
                                        for (int m = 0; m < length; m++) {
                                            for (int n = 0; n < length; n++) {
                                                for (int o = 0; o < length; o++) {
                                                    for (int p = 0; p < length; p++) {
                                                        for (int q = 0; q < length; q++) {
                                                            for (int r = 0; r < length; r++) {
                                                                for (int t = 0; t < length; t++) {
                                                                    for (int u = 0; u < length; u++) {
                                                                        prod = elements[i] + elements[j] + elements[k] + elements[l] + elements[m] + elements[n] + elements[o] + elements[p] + elements[q] + elements[r] + elements[t] + elements[u];
                                                                        try {
                                                                            p(prod);
                                                                            BruteForce.setB(false);
                                                                            break;
                                                                        } catch (Exception e) {
                                                                            if (e.getClass().equals(ZipException.class)) {
                                                                                continue;
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        logger.info("===============12位数密码尝试完毕");
                    }
                }
            });
            two.start();
            three.start();
            four.start();
            five.start();
            six.start();
            seven.start();
            eight.start();
            nine.start();
            ten.start();
            eleven.start();
            twelve.start();
        } while (false);
    }

    public static List<String> bruteForceNew(String initial) throws InterruptedException {
        int length = elements.length;
        List<String> buff = new ArrayList<String>();
        StringBuffer sb = new StringBuffer();
        for (int a = 0; a < length; a++) {
            for (int b = 0; b < length; b++) {
                for (int c = 0; c < length; c++) {
                    for (int d = 0; d < length; d++) {
                        sb.append(initial).append(elements[a]).append(elements[b]).append(elements[c]).append(elements[d]);
                        buff.add(sb.toString());
                        sb.setLength(0);//clean up
                    }
                }
            }
        }
        return buff;
    }

    /**
     * @param initial 首字母
     * @param digit   密码位数
     * @param prefixs 缓存列表   传null就行
     * @return
     * @throws InterruptedException
     */
    public static List<String> bruteForce(String initial, int digit, List<String> prefixs) throws InterruptedException {
        logger.info("=====================digit:" + digit);
        int length = elements.length;
        StringBuffer sb = new StringBuffer();
        List<String> buff = new ArrayList<String>();
        for (int a = 0; a < length; a++) {
            if (prefixs == null || prefixs.isEmpty()) {//2位
                sb.append(initial).append(elements[a]);
                buff.add(sb.toString());
                sb.setLength(0);//clean up
            } else {
                for (String prefix : prefixs) {
                    sb.append(prefix).append(elements[a]);
                    buff.add(sb.toString());
                    sb.setLength(0);//clean up
                }
            }

        }
        logger.info(""+buff.size());
        if (0 < digit) {
            return bruteForce("", digit - 1, buff);
        } else {
            return buff;
        }
    }


    public static List<String> bruteForceIO(String initial) throws InterruptedException, IOException {
        int length = elements.length;
        List<String> buff = new ArrayList<String>();
        File file=new File("D:\\XR@bl\\pwdDirct\\"+initial+"4.txt");
        if(!file.exists()){
            file.createNewFile();
        }
        StringBuffer sb = new StringBuffer();
        for (int a = 0; a < length; a++) {
            for (int b = 0; b < length; b++) {
                for (int c = 0; c < length; c++) {
                    for (int d = 0; d < length; d++) {
                        sb.append(initial).append(elements[a]).append(elements[b]).append(elements[c]).append(elements[d]).append("|");
                        try (BufferedWriter bw=new BufferedWriter(new FileWriter(file))){
                            bw.write(sb.toString());
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        sb.setLength(0);//clean up
                    }
                }
            }
        }
        return buff;
    }

    /**
     * @param initial 首字母
     * @param digit   密码位数
     * @param prefixs 缓存列表   传null就行
     * @return
     * @throws InterruptedException
     */
    public static void bruteForce(String initial,int num,File file) throws InterruptedException, IOException {
        StringBuffer sbin=new StringBuffer();
        try (BufferedReader br=new BufferedReader(new FileReader(file))){
            sbin.append(br.readLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] prefixs=sbin.toString().split("|");
        int length = elements.length;
        StringBuffer sb = new StringBuffer();

        File outFile=new File("D:\\XR@bl\\pwdDirct\\"+initial+num+".txt");
        if(!file.exists()){
            outFile.createNewFile();
        }
        for (int a = 0; a < length; a++) {
            for (String prefix : prefixs) {
                sb.append(prefix).append(elements[a]).append("|");
                try (BufferedWriter bw=new BufferedWriter(new FileWriter(file))){
                    bw.write(sb.toString());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                sb.setLength(0);//clean up
            }
        }
        return;

    }

    public static void main(String[] args) throws InterruptedException, IOException {
//        bruteForceIO("a");
        String pwd="a0z*";
        List<String> list=bruteForce("a", 3, new ArrayList<String>());
        System.out.println(list);
        for (String s:list) {
            if (pwd.equals(s)){
                System.out.println("密码匹配成功"+list.size());
                break;
            }
        }
    }
}

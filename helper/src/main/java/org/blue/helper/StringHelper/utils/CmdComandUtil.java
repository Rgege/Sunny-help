package org.blue.helper.StringHelper.utils;

import org.apache.commons.lang3.StringUtils;
import org.blue.helper.StringHelper.common.exception.HelperException;
import org.blue.helper.test.util.CallBackAfterBatFinish;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

public class CmdComandUtil {
    private static final Logger log = LoggerFactory.getLogger(CmdComandUtil.class);

    /**
     * @desc 启动进程
     * @author zp
     * @date 2018-3-29
     */
    public static void startProc(String processName) {
        log.info("启动应用程序：" + processName);
        if (StringUtils.isNotBlank(processName)) {
            try {
                Desktop.getDesktop().open(new File(processName));
            } catch (Exception e) {
                e.printStackTrace();
                log.error("应用程序：" + processName + "不存在！");
            }
        }
    }

    /**
     * @throws IOException
     * @desc 杀死进程
     * @author zp
     * @date 2018-3-29
     */
    public static void killProc(String processName) throws IOException {
        log.info("关闭应用程序：" + processName);
        if (StringUtils.isNotBlank(processName)) {
            executeCmd("taskkill /F /IM " + processName);
        }
    }

    /**
     * @desc 执行cmd命令
     * @author zp
     * @date 2018-3-29
     */
    public static String executeCmd(String command) throws IOException {
        log.info("Execute command : " + command);
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec("cmd /c " + command);
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
        String line = null;
        StringBuilder build = new StringBuilder();
        while ((line = br.readLine()) != null) {
            log.info(line);
            build.append(line);
        }
        return build.toString();
    }

    public static void main(String[] args) {
        mvnBuild("D:\\XR@bl\\IdeaWorkspace\\gateway-tools", new CallBackAfterBatFinish() {
            @Override
            public void callBack() {
                System.out.println("111111111111111111111111");
            }
        });
    }

    public static void mvnBuild(String projectUrl) {

        File batFile = new File("D:\\XR@bl\\mvnInstall.bat");
        try {
            if (!batFile.exists()) batFile.createNewFile();
            else batFile.delete();
            batFile.createNewFile();
        } catch (IOException e) {
            throw new HelperException(e);
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(batFile))) {
            bw.write("@echo off\n" +
                    "cd " + projectUrl + "\n" +
                    "D:\n" +
                    "set JAVA_HOME=D:\\XR@bl\\devsoft\\java\\jdk1.8\n" +
                    "mvn clean install");
            bw.flush();
        } catch (IOException e) {
            throw new HelperException(e);
        }
        if (batFile.exists()) {
            excutBat("D:\\XR@bl\\mvnInstall.bat");
        }
    }

    public static void mvnBuild(String projectUrl, CallBackAfterBatFinish callBack) {
        File file = new File(projectUrl + File.separator + "target");
        boolean b=false;
        if (file.exists()){
            b=file.delete();
            System.out.println(b);
        }else {
            b=true;
        }
        if (b) {
            File batFile = new File("D:\\XR@bl\\mvnInstall.bat");
            try {
                if (!batFile.exists()) batFile.createNewFile();
                else batFile.delete();
                batFile.createNewFile();
            } catch (IOException e) {
                throw new HelperException(e);
            }
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(batFile))) {
                bw.write("@echo off\n" +
                        "cd " + projectUrl + "\n" +
                        "D:\n" +
                        "set JAVA_HOME=D:\\XR@bl\\devsoft\\java\\jdk1.8\n" +
                        "mvn clean install");
                bw.flush();
            } catch (IOException e) {
                throw new HelperException(e);
            }
            if (batFile.exists()) {
                excutBat("D:\\XR@bl\\mvnInstall.bat");
            }

            while (!file.exists()) {
                System.out.println("========build ing");
            }
            boolean flag = false;
            for (int i = 0; i < 10; i++) {
                File[] underFile = file.listFiles();
                for (File f : underFile) {
                    if (f.isDirectory()) continue;
                    if ("jar".equals(f.getName().substring(f.getName().lastIndexOf(".") + 1)) ||
                            "war".equals(f.getName().substring(f.getName().lastIndexOf(".") + 1))) {
                        flag = true;
                        break;
                    }
                }
                if (flag) break;
            }
            if (flag && callBack != null) {//设有回调函数
                callBack.callBack();
            }
        }
    }

    private static void execCmdCommand(String command) {
        Runtime runtime = Runtime.getRuntime();
        try {
            log.info("execCmdCommand:" + command);
            runtime.exec(command);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void execCmdCommand(String[] commands) {
        Runtime runtime = Runtime.getRuntime();
        try {
            log.info("execCmdCommand:" + commands);
            runtime.exec(commands);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * @desc 判断进程是否开启
     * @author zp
     * @date 2018-3-29
     */
    public static boolean findProcess(String processName) {
        BufferedReader bufferedReader = null;
        try {
            Process proc = Runtime.getRuntime().exec("tasklist -fi " + '"' + "imagename eq " + processName + '"');
            bufferedReader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains(processName)) {
                    return true;
                }
            }
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception ex) {
                }
            }
        }
    }

    public static void excutBat(String batUrl) {
        //执行批处理文件
        String strcmd = "cmd /c start  " + batUrl;
        Runtime rt = Runtime.getRuntime();
        Process ps = null;
        try {
            ps = rt.exec(strcmd);
        } catch (IOException e1) {
            throw new HelperException(e1);
        }
        try {
            ps.waitFor();
        } catch (InterruptedException e) {
            throw new HelperException(e);
        }
        int i = ps.exitValue();
        if (i == 0) {
            //success
        } else {
            throw new HelperException("Execution BAT failed");
        }
        ps.destroy();
        ps = null;
        //批处理执行完后，根据cmd.exe进程名称 kill掉cmd窗口(这个方法是好不容易才找到了，网上很多介绍的都无效)
//        killProcess();
//        callBack.callBack();
    }

    public static void killProcess() {
        Runtime rt = Runtime.getRuntime();
        Process p = null;
        try {
            rt.exec("cmd.exe /C start wmic process where name='cmd.exe' call terminate");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package org.blue.helper.StringHelper.controller;

import org.blue.helper.StringHelper.aop.annotation.NeedLogin;
import org.blue.helper.StringHelper.controller.support.FileReqDTO;
import org.blue.helper.StringHelper.utils.DateUtil;
import org.blue.helper.StringHelper.utils.FileUtils;
import org.blue.helper.StringHelper.utils.ResultUtil;
import org.blue.helper.core.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.NumberFormat;
import java.util.*;

@Controller
@RequestMapping("/file/")
public class FileUpDownController {
    private static final Logger logger = LoggerFactory.getLogger(FileUpDownController.class);

    @Value("${filePath}")
    private String filePath;
    @Autowired
    private RedisUtil redisUtil;

    // 单文件上传
//    @NeedLogin
    @PostMapping(value = "/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return "文件为空";
            }
            // 获取文件名
            String fileName = file.getOriginalFilename();
            logger.info("上传的文件名为：" + fileName);
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            logger.info("文件的后缀名为：" + suffixName);

//            // 设置文件存储路径
//            String filePath = "C://testFile//";
            String path = filePath + fileName;

            File dest = new File(path);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();// 新建文件夹
            }
            file.transferTo(dest);// 文件写入
            return "上传成功";
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }


    /**
     * 批量上传
     *
     * @param request
     * @return
     */
    @NeedLogin
    @PostMapping(value = "/uploadBatch")
    @ResponseBody
    public String handleFileUpload(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(
                            new File(filePath + file.getOriginalFilename())));//设置文件路径及名字
                    stream.write(bytes);// 写入
                    stream.close();
                } catch (Exception e) {
                    stream = null;
                    return "第 " + i + " 个文件上传失败  ==> "
                            + e.getMessage();
                }
            } else {
                return "第 " + i
                        + " 个文件上传失败因为文件为空";
            }
        }
        return "上传成功";
    }

    //文件下载相关代码
//    @NeedLogin
    @RequestMapping("/download")
    public String downloadFile(String fileName, HttpServletRequest request, HttpServletResponse response) {
        if (fileName != null) {
            //设置文件路径
            File file = new File(filePath, fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }

    @PostMapping("showFileList")
    @ResponseBody
    public Map<String, Object> showFileList(HttpServletRequest request) {
        File file = new File(filePath);
        List<FileReqDTO> list = new ArrayList<FileReqDTO>();
        if (file.exists()) {
            String[] files = file.list();
            for (String fileName : files) {
                FileReqDTO fileReqDTO = new FileReqDTO();
                if (FileUtils.isFolder(fileName)) {
                    fileReqDTO.setDir(true);
                    fileReqDTO.setFileName(fileName);
                } else {
                    fileReqDTO.setDir(false);
                    fileReqDTO.setFileName(fileName);
                    fileReqDTO.setSuffix(FileUtils.getSuffix(fileName));
                }
                list.add(fileReqDTO);
            }
        }
        return ResultUtil.objSuccess(list);
    }

    @GetMapping("doGet")
    public void doGet(String fileName, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //得到要下载的文件名
        fileName = new String(fileName.getBytes("iso8859-1"), "UTF-8");

        String path = filePath;
        String separator = File.separator;
        //得到要下载的文件
        File file = new File(path + separator + fileName);
        //如果文件不存在
        if (!file.exists()) {
            request.setAttribute("message", "您要下载的资源已被删除！！");
            request.getRequestDispatcher("/message.jsp").forward(request, response);
            return;
        }
        //处理文件名
        String realname = fileName.substring(fileName.indexOf("_") + 1);
        //设置响应头，控制浏览器下载该文件
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
        //读取要下载的文件，保存到文件输入流
        FileInputStream in = new FileInputStream(path + separator + fileName);
        //创建输出流
        OutputStream out = response.getOutputStream();
        //创建缓冲区
        byte buffer[] = new byte[1024];
        int len = 0;
        //循环将输入流中的内容读取到缓冲区当中
        while ((len = in.read(buffer)) > 0) {
            //输出缓冲区的内容到浏览器，实现文件下载
            out.write(buffer, 0, len);
        }
        //关闭文件输入流
        in.close();
        //关闭输出流
        out.close();
    }

    @PostMapping("doPost")
    public void doPost(@RequestBody FileReqDTO fileReqDTO, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(fileReqDTO.getFileName(), request, response);
    }

    @GetMapping("getIcon")
    public String getIcon(String suffix) {
        return "DOC.png";
    }

    //================================================================================
    //                                                                              ==
    //================================================================================
    private String percent(long p1, long p2) {
        String str;
        float f = p1 / (float) p2;
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMinimumFractionDigits(2);
        str = nf.format(f);

        return str;
    }


    @PostMapping(value = "/uploadNew")
    @ResponseBody
    public Map<String, Object> uploadNew(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        Date startTime = new Date();
        if (file.isEmpty()) {
            return ResultUtil.msgError("文件为空");
        }
        String fileName = file.getOriginalFilename();
        String path = filePath + fileName;
        File newfile = new File(path);
        if (!newfile.getParentFile().exists()) {
            newfile.getParentFile().mkdirs();
        }

        long total = file.getSize();
        long doLenth = 0L;

        BufferedInputStream in = null;
        BufferedOutputStream out = null;
        try {
            in = new BufferedInputStream(file.getInputStream());
            if (newfile != null) {
                newfile.createNewFile();
            }
            // 指定要写入文件的缓冲输出字节流
            out = new BufferedOutputStream(new FileOutputStream(newfile));
            byte[] byteBuf = new byte[3 * 1000];
            byte[] base64ByteBuf;
            byte[] copy;
            int count1; //每次从文件中读取到的有效字节数
            while ((count1 = in.read(byteBuf)) != -1) {
                if (count1 != byteBuf.length) //如果有效字节数不为3*1000，则说明文件已经读到尾了，不够填充满byteBuf了
                {
                    copy = Arrays.copyOf(byteBuf, count1); //从byteBuf中截取包含有效字节数的字节段
                } else {
                    copy = byteBuf;
                }
                out.write(copy, 0, copy.length);
                out.flush();
                doLenth = doLenth + copy.length;
                String rsp = "============>文件【" + fileName + "】 上传 已完成 : " + percent(doLenth, total);
                logger.info(rsp);
                response.getWriter().write(rsp);
            }
            Date finishTime = new Date();
            logger.info("============>文件【" + fileName + "】 上传 已完成 , 总耗时: " + (DateUtil.dateDiff(startTime, finishTime)) / 10 + " s");
            return ResultUtil.msgError("上传成功");
        } catch (IOException e) {
            e.printStackTrace();
            return ResultUtil.msgError("上传失败");
        } finally {
            try {
                if (in != null) in.close();
                if (out != null) out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @PostMapping("langLink")
    @ResponseBody
    public String langLink(HttpServletResponse response) {
        logger.info("======================into langLink()");
        try {
            for (int i = 0; i < 10; i++) {

                Thread.sleep(10000);
                response.getWriter().write(i);
                response.getWriter().flush();
                logger.info("======================write");
            }
            response.getWriter().write("over");
            logger.info("======================over");
            response.getWriter().close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("test")
    public String test(){
        return "test";
    }
}

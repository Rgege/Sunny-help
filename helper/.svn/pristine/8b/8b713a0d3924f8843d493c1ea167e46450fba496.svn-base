package org.blue.helper.StringHelper.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
@RequestMapping("/word/")
public class WordController {


    @Value("${filePath}")
    private String filePath;

    @GetMapping("wordView")
    public String wordView(){
        return "bookshelf/book";
    }
    //@RequestBody String fileName,
    @GetMapping("openWord")
    public void openWord( HttpServletResponse response){
        String fileName="PatternInJava.docx";
        File wordFile=new File(filePath,fileName);
        FileInputStream fin= null;
        OutputStream output=null;
        try {
            fin = new FileInputStream(wordFile);
            output =response.getOutputStream();
            byte[] buf=new byte[1024];
            int r=0;
            response.setContentType("application/msword;charset=GB2312");
            while((r=fin.read(buf,0,buf.length))!=-1)
            {
                output.write(buf,0,r);//response.getOutputStream()
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(fin !=null ){
                    fin.close();
                }
                if(output !=null ){
                    output.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}

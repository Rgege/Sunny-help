package org.blue.helper.StringHelper.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Description <P></P>
 * @Author allen
 * @Date 2019/1/4
 * @Version 1.0.0
 **/
public interface Tess4JService {

    String extractCharFromImg(MultipartFile file);

    String extractCharFromPdf(MultipartFile file);

}

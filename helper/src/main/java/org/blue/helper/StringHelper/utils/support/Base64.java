package org.blue.helper.StringHelper.utils.support;

import org.blue.helper.StringHelper.common.exception.HelperException;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


/**
 * @Description <P></P>
 * @Author allen
 * @Date 2019/1/4
 * @Version 1.0.0
 **/
public enum Base64 {
    ENCODER,
    DECODER;

    private  BASE64Encoder encoder ;
    private  BASE64Decoder decoder ;

    Base64() {

        initDecoder();
        initEncoder();
    }
    public  BASE64Encoder getEncoder(){
        if(!this.equals(ENCODER)){
            throw new HelperException("Unallowed Call");
        }
        return this.encoder;
    }
    public  BASE64Decoder getDecoder(){
        if(!this.equals(DECODER)){
            throw new HelperException("Unallowed Call");
        }
        return this.decoder;
    }
    private void initEncoder(){
        this.encoder = new sun.misc.BASE64Encoder();
    }
    private void initDecoder(){
        this.decoder = new sun.misc.BASE64Decoder();
    }

    public static void main(String[] args) {
        Base64.ENCODER.getEncoder();
        Base64.DECODER.getEncoder();

    }

}

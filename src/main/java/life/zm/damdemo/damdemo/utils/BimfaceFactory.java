package life.zm.damdemo.damdemo.utils;


import com.bimface.sdk.BimfaceClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

/**
 * @description
 * @date 2019/7/8
 * @author chenb-f
 */
public class BimfaceFactory implements Serializable {

    private volatile static BimfaceClient BIM_FACE_CLIENT;
    private static String APP_KEY;
    private static String APP_SECRET;
    private static Log logger = LogFactory.getLog(BimfaceFactory.class);

    /*
      @Description 读取配置文件，赋值APP_KEY，APP_SECRET
     * @Date 17:58
     */
    static {
        Properties properties = new Properties();
        InputStream resourceAsStream = BimfaceFactory.class.getClassLoader().getResourceAsStream("bimfaceUser.properties");
        try {
            properties.load(resourceAsStream);
            APP_KEY = properties.getProperty("APP_KEY");
            APP_SECRET = properties.getProperty("APP_SECRET");
            resourceAsStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("读取bimfaceUser配置文件失败");
        }
    }

    private void creatBimfaceClient() {
        logger.debug("APP_KEY: "+ APP_KEY + " APP_SECRET: " + APP_SECRET);
        BIM_FACE_CLIENT = new BimfaceClient(APP_KEY, APP_SECRET);
    }

    /**
     * @Description 单例模式：双重检查锁
     * @Params []
     * @Return
     * @Date 17:37
     */
    public BimfaceClient getBimfaceClient() {
        if (null == BIM_FACE_CLIENT) {
            synchronized (BimfaceFactory.class) {
                if(null == BIM_FACE_CLIENT) {
                    //调用BimfaceFactory构造函数，生成BIM_FACE_CLIENT对象
                    creatBimfaceClient();
                }
            }
        }
        logger.debug("BIM_FACE_CLIENT: " + BIM_FACE_CLIENT);
        return BIM_FACE_CLIENT;
    }
}

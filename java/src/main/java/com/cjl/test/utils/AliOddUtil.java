package com.cjl.test.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;

public class AliOddUtil {

    private static final String ENDOPOINT = "oss-cn-beijing.aliyuncs.com";
    private static final String ACCESS_KEY_ID = "";
    private static final String ACCESS_KEY_SECRET = "";
    private static final String BUCKET_Name = "cjl-bishe";

    public static String uploadFile(String objectName, InputStream in) throws Exception {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(ENDOPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        String url ="";
        try {
            // 填写字符串。
            String content = "Hello OSS，你好世界";

            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_Name, objectName,in);

            // 如果需要上传时设置存储类型和访问权限，请参考以下示例代码。
            // ObjectMetadata metadata = new ObjectMetadata();
            // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
            // metadata.setObjectAcl(CannedAccessControlList.Private);
            // putObjectRequest.setMetadata(metadata);

            // 上传字符串。
            PutObjectResult result = ossClient.putObject(putObjectRequest);
            url="https://cjl-bishe.oss-cn-beijing.aliyuncs.com/"+objectName;
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return url;
    }
    public static String getportrait (String id) throws Exception{
        OSS ossClient = new OSSClientBuilder().build(ENDOPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        String url ="";
        try {
            Date expiration = new Date(new Date().getTime() + 1000 * 1000);
            String filename = "src/"+id + ".png";
            url=ossClient.generatePresignedUrl(BUCKET_Name,filename,expiration).toString();
            url=url.substring(0,url.lastIndexOf("?"));
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return url;
    }
    public static String getradar (String id) throws Exception{
        OSS ossClient = new OSSClientBuilder().build(ENDOPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        String url ="";
        try {
            Date expiration = new Date(new Date().getTime() + 1000 * 1000);
            String filename = "radar/"+id + ".png";
            url=ossClient.generatePresignedUrl(BUCKET_Name,filename,expiration).toString();
            url=url.substring(0,url.lastIndexOf("?"));
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return url;
    }
    public static String getwarn (String id) throws Exception{
        OSS ossClient = new OSSClientBuilder().build(ENDOPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        String url ="";
        try {
            Date expiration = new Date(new Date().getTime() + 1000 * 1000);
            String filename = "warn/"+id + ".png";
            url=ossClient.generatePresignedUrl(BUCKET_Name,filename,expiration).toString();
            url=url.substring(0,url.lastIndexOf("?"));
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return url;
    }
    public static String getportrait_life (String id) throws Exception{
        OSS ossClient = new OSSClientBuilder().build(ENDOPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        String url ="";
        try {
            Date expiration = new Date(new Date().getTime() + 1000 * 1000);
            String filename = "life/"+id + ".png";
            url=ossClient.generatePresignedUrl(BUCKET_Name,filename,expiration).toString();
            url=url.substring(0,url.lastIndexOf("?"));
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return url;
    }
    public static String getradar_life (String id) throws Exception{
        OSS ossClient = new OSSClientBuilder().build(ENDOPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        String url ="";
        try {
            Date expiration = new Date(new Date().getTime() + 1000 * 1000);
            String filename = "r_life/"+id + ".png";
            url=ossClient.generatePresignedUrl(BUCKET_Name,filename,expiration).toString();
            url=url.substring(0,url.lastIndexOf("?"));
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return url;
    }
    public static String getwarn_life (String id) throws Exception{
        OSS ossClient = new OSSClientBuilder().build(ENDOPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        String url ="";
        try {
            Date expiration = new Date(new Date().getTime() + 1000 * 1000);
            String filename = "w_life/"+id + ".png";
            url=ossClient.generatePresignedUrl(BUCKET_Name,filename,expiration).toString();
            url=url.substring(0,url.lastIndexOf("?"));
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return url;
    }
}


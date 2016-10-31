package fj.linyu.qiniu;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

import java.io.File;
import java.io.IOException;

/**
 * @des
 * @auther linyu
 * @create 2016-09-14:14:46
 */
public class QiniuUtil {
    //设置好账号的ACCESS_KEY和SECRET_KEY
    String ACCESS_KEY = "SCcsKl_7aYLJIHhHFqtxIkMG4ad0caOt0JtdOoV8";
    String SECRET_KEY = "rDjxY7Afl234wHYUntzvzoGYGV3_JAnWXfyekKeS";
    //要上传的空间
    private final String bucketname = "xmmall";
    //上传到七牛后保存的文件名
    String key = "Jellyfish.jpg";
    //上传文件的路径
    String FilePath = "F:\\";

    public void upload() throws IOException{
        try {
            //密钥配置
            Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
            //创建上传对象
            UploadManager uploadManager = new UploadManager();
            String token = auth.uploadToken(bucketname,key);
            System.out.println("-----------hiwetop-----------token值=" + token + "," + "当前类=QiniuUtil.upload()");
            //调用put方法上传
            Response res =  uploadManager.put(FilePath,key,token);
//            Response res = uploadManager.put(FilePath, key, getUpToken());
            //打印返回的信息
            System.out.println(res.bodyString());
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(new Gson().toJson(e));
            System.out.println(r.toString());
            try {
                //响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
        }
    }

    public static void main(String args[]) throws IOException{
        new QiniuUtil().upload();
    }

}

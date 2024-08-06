package wrq.rotation.media.utils;
import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wrq.rotation.media.config.MinioConfig;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
@Component
public class MinioUtil {
    @Autowired
    private MinioConfig minioConfig;
    @Autowired
    private MinioClient minioClient;

    public String upload(String fileName, InputStream file) {//网络文件上传
        String objectName=fileName.substring(fileName.lastIndexOf("\\")+1);
        try {
            PutObjectArgs putObjectArgs=PutObjectArgs.builder().bucket(minioConfig.getBucketName())
                            .stream(file, file.available(), -1).object(objectName).build();
            minioClient.putObject(putObjectArgs);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return fileName;
    }

    public String LocalUpload(String fileName) {//本地文件上传
        String objectName=fileName.substring(fileName.lastIndexOf("\\")+1);
        try {
            UploadObjectArgs uploadObjectArgs = UploadObjectArgs.builder().bucket(minioConfig.getBucketName())
                    .filename(fileName).object(objectName).build();
            minioClient.uploadObject(uploadObjectArgs);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return fileName;
    }

    public void download(String objectName,String localPath){//文件下载
        GetObjectArgs getObjectArgs = GetObjectArgs.builder().bucket(minioConfig.getBucketName()).object(objectName).build();
        FilterInputStream inputStream = null;
        String fileName=localPath+"\\"+objectName;
        try {
            inputStream = minioClient.getObject(getObjectArgs);
            FileOutputStream outputStream = new FileOutputStream(new File(fileName));
            IOUtils.copy(inputStream,outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String prePicture(String objectName){//预览图片
        // 查看文件地址
        GetPresignedObjectUrlArgs build = GetPresignedObjectUrlArgs.builder().bucket(minioConfig.getBucketName()).object(objectName).method(Method.GET).build();
        try {
            String url = minioClient.getPresignedObjectUrl(build);
            return url;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Item> fileList() {//查看当前bucket的所有文件
        Iterable<Result<Item>> results = minioClient.listObjects(ListObjectsArgs.builder().bucket(minioConfig.getBucketName()).build());
        List<Item> items = new ArrayList<>();
        try {
            for (Result<Item> result:results)
                items.add(result.get());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return items;
    }

    public boolean removeFile(String objectName) {//删除文件对象
        try {
            minioClient.removeObject( RemoveObjectArgs.builder().bucket(minioConfig.getBucketName()).object(objectName).build());
        }catch (Exception e){
            return false;
        }
        return true;
    }


    public Boolean bucketExists(String bucketName) {//查看bucket是否存在
        Boolean isExists;
        try {
            isExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return isExists;
    }
    public Boolean createBucket(String bucketName) {//创建bucket
        try {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public Boolean removeBucket(String bucketName) {//删除bucket
        try {
            minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public List<Bucket> getAllBuckets() {//获取全部bucket
        try {
            List<Bucket> buckets = minioClient.listBuckets();
            return buckets;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

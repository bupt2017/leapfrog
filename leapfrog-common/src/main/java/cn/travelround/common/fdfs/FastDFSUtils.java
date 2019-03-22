package cn.travelround.common.fdfs;

import org.apache.commons.io.FilenameUtils;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.core.io.ClassPathResource;

/**
 * @author Created by Robin 2019/3/13 15:49
 */
public class FastDFSUtils {

    //upload file, return 网络存储路径
    public static String uploadPic(byte[] pic, String name, long size){

        String path = null;

        //自动搜索配置文件的位置
        ClassPathResource resource = new ClassPathResource("fdfs_client.conf");
        try {
            ClientGlobal.init(resource.getClassLoader().getResource("fdfs_client.conf").getPath());
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();  //连接老大

            //创建小弟-根据老大
            StorageClient1 storageClient1 = new StorageClient1(trackerServer, null);

            //获取文件扩展名
            String ext = FilenameUtils.getExtension(name);

            //选择csource包下的
            NameValuePair[] meta_list = new NameValuePair[3];
            meta_list[0] = new NameValuePair("fileName", name);  //依次为： 文件名、扩展名、文件大小
            meta_list[1] = new NameValuePair("fileExt", ext);
            meta_list[2] = new NameValuePair("fileSize", String.valueOf(size));

            //功能：上传文件，并返回文件路径
            //arg1：文件内容
            //arg2：文件扩展名
            //arg3：附加信息
            path = storageClient1.upload_file1(pic, ext, meta_list);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return path;
    }
}

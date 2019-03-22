package cn.travelround.core.service.product;

/**
 * @author Created by Robin 2019/3/13 16:39
 */
public interface UploadService {

    String uploadPic(byte[] pic, String originalFilename, long size);
}

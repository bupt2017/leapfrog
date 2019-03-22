package cn.travelround.core.service.product;

import cn.travelround.common.fdfs.FastDFSUtils;
import org.springframework.stereotype.Service;

/**
 * @author Created by Robin 2019/3/13 16:41
 */
@Service(value = "uploadService")
public class UploadServiceImpl implements UploadService {
    @Override
    public String uploadPic(byte[] pic, String name, long size) {

        return FastDFSUtils.uploadPic(pic, name, size);
    }
}

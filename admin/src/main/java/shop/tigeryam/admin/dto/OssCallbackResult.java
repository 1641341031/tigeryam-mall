package shop.tigeryam.admin.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * OSS上传文件的回调结果
 */
@Data
@EqualsAndHashCode
public class OssCallbackResult {
    /// 文件名称
    private String filename;
    // 文件大小
    private String size;
    // 文件的mimeType
    private String mimeType;
    // 图片文件的宽
    private String width;
    // 图片文件的高
    private String height;
}

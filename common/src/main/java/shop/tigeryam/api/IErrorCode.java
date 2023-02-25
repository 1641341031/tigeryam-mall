package shop.tigeryam.api;

/**
 * 常用API返回对象接口
 * Create by tiger on 2022/11/25
 */
public interface IErrorCode {
    /**
     * 返回码
     */
    long getCode();

    /**
     * 返回信息
     */
    String getMessage();
}

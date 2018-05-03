package net.sycu.lxp.common;

/**
 * 常量类,定义一系列常量
 *
 * @author lxp
 * @create 2018-03-06 11:20
 **/
public class Constant {

    public static final String ZERO = "0";
    public static final String ONE = "1";
    public static final Integer ZERO_NUM = 0;
    
    public static final String LOGIN_PERSON = "loginPerson"; 
    
    /**
     * 当前用户
     */
    public static final String CURRENT_USER = "user";
    
    /**
     * 系统获取字段
     */
    public interface SystemField{
    	String NAME_ATTR = "name";
    	String DESCRIPTION_ATTR = "description";
    	String CATEGORY_ATTR = "categoryId";
    	String IMAGES_SRC = "images_src";
    	String ID_ATTR = "id";
    	String USER_ID_ATTR = "userId";
    	String GOODS_ID_ATTR = "goodsId";
    	String ACCOUNT_ATTR = "account";
    }
    
    /**
     * 编码解决
     */
    public interface CharacterEncoding {
    	//前端编码
    	String CODING_ISO8859 = "iso8859-1";
    	//后台解码
    	String CODING_UTF8 = "utf-8";
    }
    
    /**
     * 上传文件常量管理
     * @author lxp
     *
     */
    public interface UploadDownFileConstant {
    	String RELATIVE_FILE_PATH = "/change-web/uploadfiles";
    	String REALLY_FILE_PATH = "D:\\change-web\\change-web\\src\\main\\webapp\\uploadfiles";
    }
    /**
     * 请求状态定义
     * @author lxp
     */
    public interface AjaxStatus {
         String AJAX_SUCCESS = "1";//请求成功
         String AJAX_FAIL = "0";//请求失败
    }
    
    /**
     * 删除状态
     */
    public interface DeleteStatus {
    	Integer IS_DELETE = -1; //删除状态
    	Integer IS_NOT_DELETE = 1; //未删除状态
    }
    
    /**
     *  数据请求状态码
     *  @author lxp
     **/
    public interface DataCode {
        String FAIL = "0";//失败
        String SUCCESS = "1";//成功
    }

    public interface PageSize {
        Integer NUM_FIVE = 5;
        Integer NUM_SIX = 6;
        Integer NUM_SEVEN = 7;
        Integer NUM_EIGHT = 8;
        Integer NUM_NINE = 9;
        Integer NUM_TEN = 10;
    }

    /**
     *  @Description: 用户管理常量接口
     *  @author lxp
     **/
    public interface UserConstants {
        /**
         * 用户类型
         */
        String SYSTEM_ADMIN = "0"; //系统管理员
        String SYSTEM_USER = "1"; //系统用户
        String GENERAL_USER = "2"; //普通用户
        String GOODS_PAGE = "3";//商品页面
        String OPERATE_PAGE_LOGIN = "4"; //登陆页面
        String OPERATE_PAGE_CHANGE = "5";//修改密码
        
    }
    
    /**
     *  @Description: 商品管理常量接口
     *  @author lxp
     **/
    public interface GoodsConstants {
    	/**
    	 * 商品类型
    	 */
    	String BUYER_GOODS = "1";//买家商品
    	String SELLER_GOODS = "2";//卖家商品
    	
    	/**
    	 * 商品默认图片路径
    	 */
    	String GOODS_IMAGE_SRC = "/change-web/images/no-image.jpg";
    	
    }
    
    /**
     * @Description: 订单管理常量接口
     * @author lxp
     */
    public interface OrdersConstants {
    	/**
    	 * 订单状态
    	 */
    	String WAIT_STATUS = "1";//等待
    	String REFUSE_STATUS = "2";//拒绝
    	String SUCCESS_STATUS = "3";//成功
    	
    }
    
    /**
     * 收藏常量处理
     * @author lxp
     *
     */
    public interface FavoritesConstants {
    	
    	/**
    	 * 收藏状态
    	 */
    	String IS_COLLECT = "0";//已收藏
    	String IS_NOT_COLLECT = "1";//未收藏
    	
    }
    
    /**
     * 操作日志常量
     * @author lxp
     *
     */
    public interface OperateConstants {
    	
    	/**
    	 * 操作类型
    	 */
    	String OPERATE_LOGIN = "1";//登陆
    	String OPERATE_CHANGE_PSW = "2";//修改密码
    	
    	
    }

}


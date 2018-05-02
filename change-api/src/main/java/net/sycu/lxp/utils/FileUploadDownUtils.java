package net.sycu.lxp.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MultipartFile;

import net.sycu.lxp.common.Constant;
import net.sycu.lxp.pojo.User;


/**
 * 文件上传下载工具类
 * @author lxp
 *
 */
public class FileUploadDownUtils {
	
    /**
     * @throws IOException 
     * @throws IllegalStateException 
     *  @Description: 文件上传
     *  @author lxp
     *  @method uploadFile
     *  @params file 文件流对象
     *  @params request 请求对象
     *  @return 文件路径
     *  @exception
     **/
	public String uploadFile(MultipartFile[] files, HttpServletRequest request) throws Exception {
		//文件保存路径
		String fileTotalPath = "";
		if (files != null && files.length >0) {
			for (MultipartFile file: files) {
				User user = (User) request.getSession().getAttribute(Constant.CURRENT_USER);
				String filePath = request.getSession().getServletContext().getRealPath(
						Constant.UploadDownFileConstant.RELATIVE_FILE_PATH+"/"+user.getAccount()+"/"+
						StringUtils.getUUID());
				String fileName = file.getOriginalFilename(); 
				File dir = new File(filePath, fileName);
		        if(!dir.exists()){  
		            dir.mkdirs();  
		        }
		        file.transferTo(dir);
		        fileTotalPath = fileTotalPath + filePath;
			}
		}
        return fileTotalPath;
	}
	
    /**
     *  @Description: 文件上传
     *  @author lxp
     *  @method uploadFiles
     *  @params request 请求对象
     *  @return 上传的对应数据映射
     *  @exception
     **/
	public Map<String, String> uploadFiles(HttpServletRequest request) {
		Map<String, String> msgMap = new HashMap<String, String>();
		String path = "";		
		User user = (User) request.getSession().getAttribute(Constant.CURRENT_USER);
        DiskFileItemFactory dfi = new DiskFileItemFactory();
        //设置文件大小超过1024*1024就写到disk上
        dfi.setSizeThreshold(1024*1024);
        ServletFileUpload sfu = new ServletFileUpload(dfi);
        try {
            List<FileItem> list = sfu.parseRequest(request);
            //遍历得到每个FileItem
            for(FileItem item : list){
                //取得表单文本框的名字
                String name = item.getFieldName();
                //如果上传的这个文件只是一个表单字段，而不是一个文件
                if(item.isFormField()){
                    //取得文本框输入的内容
                    String value = new String(item.getString().getBytes(Constant.CharacterEncoding.CODING_ISO8859),
                    		Constant.CharacterEncoding.CODING_UTF8);
                    msgMap.put(name, value);
                }else{
                	//如果上传的是一个文件
                	//文件存储路径
                    String fileName = item.getName();
                    if(StringUtils.isNotBlank(fileName)) {
                    	//相对路径设置
                    	String UUID = StringUtils.getUUID();
                    	String relativePath = Constant.UploadDownFileConstant.RELATIVE_FILE_PATH+"/"+user.getAccount()+"/"+UUID;
                    	String fileDictory = Constant.UploadDownFileConstant.REALLY_FILE_PATH + "\\"
                    			+ user.getAccount() + "\\" 
                    			+ UUID;
/*                		String fileDictory = request.getSession().getServletContext().getRealPath("/")
                				+Constant.UploadDownFileConstant.FILE_PATH+"/"
                				+user.getAccount()+"/"+
                				StringUtils.getUUID(); */
                		String filePath = fileDictory + "/" + fileName;
                        //创建目录
                		File dictory = new File(fileDictory);
                		File file = new File(filePath);
                		if (!dictory.exists()) {
                			dictory.mkdirs();
            			}
                		if(!file.exists()) {
                			file.createNewFile();
                		}	
                        //读取文件的内容
                        item.write(file);
                        
                        if(StringUtils.isNotBlank(path)) {
                        	 path = path + "," +relativePath + "/" + fileName;
                        } else {
                        	 path = relativePath + "/" + fileName;
                        }
                    }
                }   
            }        
        } catch (Exception e) {
        	e.printStackTrace();
        } 
        msgMap.put(Constant.SystemField.USER_ID_ATTR, user.getId());
        msgMap.put(Constant.SystemField.IMAGES_SRC, path);        
		return msgMap;
	}
	
	
}

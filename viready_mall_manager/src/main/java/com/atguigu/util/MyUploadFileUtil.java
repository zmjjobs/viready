package com.atguigu.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * @description 上传文件工具类
 * @author 朱梦君
 * @datatime 2017年8月14日 下午3:28:41 
 * @version v1
 */
public class MyUploadFileUtil {
	
	/**
	 * 上传所有图片
	 * @param files
	 * @return 新的图片名称的集合
	 */
	public static List<String> upload_image(MultipartFile[] files) { 
		List<String> list_image = new ArrayList<>();
		
		//根据字段获取上传到的路径
		String uploadPath = MyProperty.getMyProperty("windows_path","uploadFilePath.properties");
		
		for (int i=0;i<files.length;i++) {
			MultipartFile file = files[i];
			if(file.getSize() == 0){
				continue;
			}
			//系统时间戳+文件原始名称=此上传文件保存到服务器安置位置的新名字
			String newFileName = System.currentTimeMillis() + file.getOriginalFilename();
			try {
				//将文件另存为新的路径下
				file.transferTo(new File(uploadPath + "/" + newFileName));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			list_image.add(newFileName);
		}
		
		return list_image;
	}

}

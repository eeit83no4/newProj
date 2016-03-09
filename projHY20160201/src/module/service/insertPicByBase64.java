package module.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.commons.codec.binary.Base64;

public class insertPicByBase64 {
	
	
	//picBase64->圖片base64編碼
	
	
	public String picInsert(String picBase64){
		System.out.println("into");
		System.out.println(picBase64);
		String imgFilename=picBase64.split(";")[0].split("\\/")[1];//取得副檔名
//		System.out.println(imgFilename);
		// 判斷圖片格式
		String[] pictureSubs = { "jpeg", "jpg", "gif", "png", "jpe","bmp" };
		boolean isPicture = false;
		for (int j = 0; j < pictureSubs.length; j++) {
			if (imgFilename.toLowerCase().equals(pictureSubs[j])) {					
				isPicture = true;
				break;
			} else if (j == pictureSubs.length - 1) {
				System.out.println("mem_pic圖片格式錯誤");
			}
		}
		if(isPicture){
			// 如果是圖片，則寫入
			String imgBase64=picBase64;//取得圖片base64編碼
			String encodingPrefix = "base64,";
			int contentStartIndex = imgBase64.indexOf(encodingPrefix) + encodingPrefix.length();
			//擷取"data:image/jpeg;base64,"之後的字串轉成byte
//			System.out.println("imgBase64.substring(contentStartIndex)="+imgBase64.substring(contentStartIndex));
			byte[] imageData = Base64.decodeBase64(imgBase64.substring(contentStartIndex));

		
			byte[] bFile=new byte[1024];//緩衝區大小			
			String newPicPath=null;//即將儲存圖片的路徑
						
			try {
				int picname=(int)(Math.random()*10000000)+1;//存入的圖片名稱
				newPicPath="e:/proj/detailPic/detail"+picname+"RRRRRRTodayTOday."+imgFilename;//設定目的地
				InputStream inp=new ByteArrayInputStream(imageData);
				FileOutputStream fileOutputStream= new FileOutputStream(newPicPath);
				//使用緩衝區
				BufferedInputStream bufferedInputStream=new BufferedInputStream(inp);
				BufferedOutputStream bufferedOutputStream=new BufferedOutputStream(fileOutputStream);
				while(bufferedInputStream.read(bFile)!=-1){
					bufferedOutputStream.write(bFile);
				}
				// 將緩衝區中的資料全部寫出
				bufferedOutputStream.flush();
				// 關閉串流
				bufferedInputStream.close();
				bufferedOutputStream.close();
				//傳回圖片的儲存位置
				return newPicPath;
			} catch (Exception e) {
				System.out.println("圖片出錯啦"+e.toString());
			}
		}
		
		return null;
		
		
	}

}

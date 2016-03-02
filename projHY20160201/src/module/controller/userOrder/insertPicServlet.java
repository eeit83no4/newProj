package module.controller.userOrder;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;
import sun.misc.BASE64Decoder;


@WebServlet("/userOrder/insertPicServlet.controller")
@MultipartConfig(
		location="",
		fileSizeThreshold=1024*1024,
		maxFileSize=1024*1024*500,
		maxRequestSize=1024*1024*500*5
		)
public class insertPicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final String[] pictureSubs = { "jpeg", "jpg", "gif", "png", "jpe" };
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String imgsString=request.getParameter("jsonString");
//		System.out.println(imgsString);
		//------------------------------------------------
		JSONSerializer jSONSerializer=new JSONSerializer();
		Object b=null;
		//將imgsString轉換Object
		try {
			b=jSONSerializer.toJSON(imgsString);
		} catch (Exception e) {			
			System.out.println("jSONSerializer error="+e.toString());
		}
//		將Object轉換成JsonArray格式
		JSONArray jSONArray=JSONArray.fromObject(b);		
//		System.out.println(jSONArray);

		for(int i=0;i<jSONArray.size();i++){
						
			String imgFilename=jSONArray.getString(i).split(";")[0].split("\\/")[1];//取得副檔名
//			System.out.println(imgFilename);
			// 判斷圖片格式
			boolean isPicture = false;
			for (int j = 0; j < pictureSubs.length; j++) {
				if (imgFilename.toLowerCase().equals(pictureSubs[j])) {					
					isPicture = true;
					break;
				} else if (i == pictureSubs.length - 1) {
					System.out.println("mem_pic圖片格式錯誤");
				}
			}
			// 如果是圖片，則寫入
			String imgBase64=jSONArray.getString(i).split("\\,")[1];//取得圖片base64編碼
//			BASE64Decoder decoder = new BASE64Decoder();			
//			byte[] imgByte=decoder.decodeBuffer(imgBase64);//將圖片base64編碼轉成byte
			File file=new File(imgBase64);
		
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doGet(request, response);
	}
	
	private static String getFileName(final Part part) {
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim()
						.replace("\"", "");
			}
		}
		return null;
	}
}

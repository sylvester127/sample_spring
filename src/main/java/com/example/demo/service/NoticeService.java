package com.example.demo.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.tomcat.jni.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.FileVO;
import com.example.demo.domain.NoticeVO;
import com.example.demo.repository.NoticeMapper;

@Service
public class NoticeService {

    @Autowired
	NoticeMapper mapper;

    public List<NoticeVO> getList() {
        return mapper.getNoticeList();
    }
    
    @Transactional
    public Map<String, Object> getNotice(int noticeNum) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	NoticeVO notice = mapper.getNotice(noticeNum);
    	List<FileVO> files = mapper.getFileList(noticeNum);
    	
    	map.put("notice", notice);
    	map.put("files", files);
    	
        return map;
    }
    
    @Transactional
    public int regNotice(ServletContext context, NoticeVO notice, MultipartFile[] files) {
    	int result = mapper.insertNotice(notice);
    	
		for(int i = 0; i < files.length; i++) {
		    try {
                String originalFileName = files[i].getOriginalFilename();
                File destinationFile = new File(context.getRealPath("/WEB-INF/upload") + File.separator + originalFileName);
                files[i].transferTo(destinationFile);
                
                FileVO file = new FileVO(notice.getBoardNum(), destinationFile.getPath());
                
    			result *= mapper.insertFile(file);    			
            } catch (Exception e) {
                e.printStackTrace();
            }
		}
    	
        return result;
    }
    
    public int deleteNotice(int noticeNum) {
        return mapper.deleteNotice(noticeNum);
    }
    
//    //파일 다운로드
//    public FileVO fileDetailService(int boardNum, HttpServletRequest request, HttpServletResponse response) throws Exception{
//    	request.setCharacterEncoding("UTF-8");
//        FileVO fileVO = mapper.getFile(boardNum);
//    	
//    	//파일 업로드된 경로 
//        try{
//            String fileUrl = fileVO.getFileUrl();
//            fileUrl += "/";
//            String savePath = fileUrl;
//            String fileName = fileVO.getSaveFileName();
//            
//            //실제 내보낼 파일명 
//            String realFileName = fileVO.getRealFileName();
//            InputStream in = null;
//            OutputStream os = null;
//            File file = null;
//            boolean skip = false;
//            String client = "";
//            
//            //파일을 읽어 스트림에 담기  
//            try{
//                file = new File(savePath, fileName);
//                in = new FileInputStream(file);
//            } catch (FileNotFoundException fe) {
//                skip = true;
//            }
//            client = request.getHeader("User-Agent");
//            //파일 다운로드 헤더 지정 
//            response.reset();
//            response.setContentType("application/octet-stream");
//            response.setHeader("Content-Description", "JSP Generated Data");
//            
//            if (!skip) {
//                // IE
//                if (client.indexOf("MSIE") != -1) {
//                    response.setHeader("Content-Disposition", "attachment; filename=\""
//                            + java.net.URLEncoder.encode(realFileName, "UTF-8").replaceAll("\\+", "\\ ") + "\"");
//                    // IE 11 이상.
//                } else if (client.indexOf("Trident") != -1) {
//                    response.setHeader("Content-Disposition", "attachment; filename=\""
//                            + java.net.URLEncoder.encode(realFileName, "UTF-8").replaceAll("\\+", "\\ ") + "\"");
//                } else {
//                    // 한글 파일명 처리
//                    response.setHeader("Content-Disposition",
//                            "attachment; filename=\"" + new String(realFileName.getBytes("UTF-8"), "ISO8859_1") + "\"");
//                    response.setHeader("Content-Type", "application/octet-stream; charset=utf-8");
//                }
//                response.setHeader("Content-Length", "" + file.length());
//                os = response.getOutputStream();
//                byte b[] = new byte[(int) file.length()];
//                int leng = 0;
//                while ((leng = in.read(b)) > 0) {
//                    os.write(b, 0, leng);
//                }
//            } else {
//                response.setContentType("text/html;charset=UTF-8");
//                System.out.println("8");
//                System.out.println("<script language='javascript'>alert('파일을 찾을 수 없습니다');history.back();</script>");
//            }
//            in.close();
//            os.close();
//        } catch (Exception e) {
////            System.out.println("ERROR : " + e.getMessage());
//        }
//    	
//        return mapper.fileDetail(boardNum);
//    }
}

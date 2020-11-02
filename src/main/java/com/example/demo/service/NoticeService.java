package com.example.demo.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.FileVO;
import com.example.demo.domain.NoticeVO;
import com.example.demo.repository.NoticeMapper;

@Service
@Transactional
public class NoticeService {

    @Autowired
	NoticeMapper mapper;

    public List<NoticeVO> getList() throws Exception{
        return mapper.boardList();
    }
    
    public NoticeVO getNotice(int noticeNum) throws Exception{
        return mapper.boardDetail(noticeNum);
    }
    
    public int addNotice(NoticeVO notice, HttpServletRequest request) throws Exception{
    	int result = mapper.boardInsert(notice);
    	
        notice.setWriter(request.getParameter("writer"));
        notice.setTitle(request.getParameter("title"));
        notice.setContent(request.getParameter("content"));
        notice.setBoard_pass(request.getParameter("board_pass"));
        notice.setB_REMV_FLAG(request.getParameter("B_RemvFlag"));
    	
        return result;
    }
    
    public int regNotice(NoticeVO board, HttpServletRequest request) throws Exception{
    	board.setTitle(request.getParameter("title"));
        board.setContent(request.getParameter("content"));
        board.setBoardNum(Integer.parseInt(request.getParameter("boardNum")));
        return mapper.boardUpdate(board);
    }
    
    public int updateViewCntService(int boardNum) throws Exception{
    	return mapper.updateReadCnt(boardNum);
    }
    
    public int boardDeleteService(int boardNum) throws Exception{
        return mapper.boardDelete(boardNum);
    }
    

    //파일 업로드
    public int fileInsertService(NoticeVO board, FileVO file, @RequestPart MultipartFile realfile) throws Exception{
    	String fileName = realfile.getOriginalFilename(); 
	    String fileNameExtension = FilenameUtils.getExtension(fileName).toLowerCase(); 
	    File destinationFile; 
	    String destinationFileName;
	    String fileUrl = "C:/mkm/git/boardproject/board/src/main/webapp/WEB-INF/uploadFiles/";	//uploadFiles 폴더 위치
        
	    do{ 
	        destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + fileNameExtension; 
	        destinationFile = new File(fileUrl + destinationFileName); 
	    }while (destinationFile.exists()); 
	        
	    destinationFile.getParentFile().mkdirs(); 
	    realfile.transferTo(destinationFile);
	        
	    file.setBoardNum(board.getBoardNum());
        file.setSaveFileName(destinationFileName);
        file.setRealFileName(fileName);
        file.setFileUrl(fileUrl);

        return mapper.fileInsert(file);
    }
    
    //파일 다운로드
    public FileVO fileDetailService(int boardNum, HttpServletRequest request, HttpServletResponse response) throws Exception{
    	request.setCharacterEncoding("UTF-8");
        FileVO fileVO = mapper.fileDetail(boardNum);
    	
    	//파일 업로드된 경로 
        try{
            String fileUrl = fileVO.getFileUrl();
            fileUrl += "/";
            String savePath = fileUrl;
            String fileName = fileVO.getSaveFileName();
            
            //실제 내보낼 파일명 
            String realFileName = fileVO.getRealFileName();
            InputStream in = null;
            OutputStream os = null;
            File file = null;
            boolean skip = false;
            String client = "";
            
            //파일을 읽어 스트림에 담기  
            try{
                file = new File(savePath, fileName);
                in = new FileInputStream(file);
            } catch (FileNotFoundException fe) {
                skip = true;
            }
            client = request.getHeader("User-Agent");
            //파일 다운로드 헤더 지정 
            response.reset();
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Description", "JSP Generated Data");
            
            if (!skip) {
                // IE
                if (client.indexOf("MSIE") != -1) {
                    response.setHeader("Content-Disposition", "attachment; filename=\""
                            + java.net.URLEncoder.encode(realFileName, "UTF-8").replaceAll("\\+", "\\ ") + "\"");
                    // IE 11 이상.
                } else if (client.indexOf("Trident") != -1) {
                    response.setHeader("Content-Disposition", "attachment; filename=\""
                            + java.net.URLEncoder.encode(realFileName, "UTF-8").replaceAll("\\+", "\\ ") + "\"");
                } else {
                    // 한글 파일명 처리
                    response.setHeader("Content-Disposition",
                            "attachment; filename=\"" + new String(realFileName.getBytes("UTF-8"), "ISO8859_1") + "\"");
                    response.setHeader("Content-Type", "application/octet-stream; charset=utf-8");
                }
                response.setHeader("Content-Length", "" + file.length());
                os = response.getOutputStream();
                byte b[] = new byte[(int) file.length()];
                int leng = 0;
                while ((leng = in.read(b)) > 0) {
                    os.write(b, 0, leng);
                }
            } else {
                response.setContentType("text/html;charset=UTF-8");
                System.out.println("8");
                System.out.println("<script language='javascript'>alert('파일을 찾을 수 없습니다');history.back();</script>");
            }
            in.close();
            os.close();
        } catch (Exception e) {
//            System.out.println("ERROR : " + e.getMessage());
        }
    	
        return mapper.fileDetail(boardNum);
    }
}

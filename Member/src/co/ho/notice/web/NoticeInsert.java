package co.ho.notice.web;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import co.ho.notice.dao.NoticeDao;
import co.ho.notice.vo.NoticeVo;

/**
 * Servlet implementation class NoticeInsert
 */
@WebServlet("/NoticeInsert.do")
@MultipartConfig(fileSizeThreshold=1024*1024*10, 	// 10 MB 
					maxFileSize=1024*1024*200,      // 200 MB
					maxRequestSize=1024*1024*200)   // 200 MB
public class NoticeInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIR = "uploadFile";   //파일 업로드 경로
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         // TODO Auto-generated method stub
         request.setCharacterEncoding("utf-8");
         NoticeDao dao = new NoticeDao();
         NoticeVo vo = new NoticeVo();

         HttpSession session = request.getSession();
         
         // 서버의 실제 경로
         String applicationPath = request.getServletContext().getRealPath("/");
         String uploadFilePath = applicationPath + UPLOAD_DIR;// "uploadFile"

         String fileName = null;
         
         for (Part part : request.getParts()) {
            String contentDisp = part.getHeader("content-disposition");
            String[] tokens = contentDisp.split(";");
            for (String str : tokens) {
               if (str.trim().startsWith("filename")) {
               fileName = str.substring(str.indexOf("=")+2, str.length()-1);
               part.write(uploadFilePath + File.separator + fileName);
               } 
            }
         }
         vo.setNoticeWriter((String) session.getAttribute("name"));
         vo.setNoticeTitle(request.getParameter("title"));
         vo.setNoticeContent(request.getParameter("content"));
         vo.setNoticeAttech(fileName);
         int n = dao.insert(vo);//이곳에 돌려줄 페이지 작성
         if( n != 0) {
            response.sendRedirect("/Member/NoticeList.do");
         } else {
            
         }
      }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
}

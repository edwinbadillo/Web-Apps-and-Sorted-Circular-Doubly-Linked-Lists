package edu.uprm.ece.icom4035.servlet;
 
import java.io.IOException;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
 
import edu.uprm.ece.icom4035.student.MasterListStudents;
import edu.uprm.ece.icom4035.student.Student;
 
@WebServlet("/Search")
public class Search extends HttpServlet{
 
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Search() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String studentID = request.getParameter("studentId");
		boolean status = false;
		
		for(Student student : MasterListStudents.getMasterListStudents()){
			
			if(student.getStudentId().equals(studentID)){
				request.getSession().setAttribute("student", student);
				getServletContext().getRequestDispatcher("/studentInfo.jsp").forward(request,response);
				status = true;				
			}
			if(!status){
				getServletContext().getRequestDispatcher("/studentNotFound.jsp").forward(request,response);
			}			
		}
	}
}
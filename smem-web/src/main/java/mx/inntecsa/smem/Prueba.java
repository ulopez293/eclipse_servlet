package mx.inntecsa.smem;

import mx.inntecsa.smem.Auto;
import mx.inntecsa.smem.Empleado;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;

import com.google.gson.Gson;

@MultipartConfig
@WebServlet("/prueba")
public class Prueba extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("user");
		String pass = request.getParameter("password");
		
		
		if("json".equals(user)) {
			Empleado empleado = new Empleado(1, "Karen", "TECNOLOGIAS DE LA INFORMACION", 5000);
			String empleadoJsonString = this.gson.toJson(empleado);
	        PrintWriter out = response.getWriter();
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        out.print(empleadoJsonString);
	        out.flush();
	        return;
		}
		
		
		if ("windows".equals(user) && "4321".equals(pass)) {
			Auto.correcto(response, user, pass);
		} else {
			Auto.error(response);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
		response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
		response.addHeader("Access-Control-Max-Age", "1728000");
		
		Auto.metodoClase(request, response);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.print("doPut");
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.print("doDelete");
	}

	@Override
	protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.print("doHead");
	}
/*	
	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.print("doOptions");
	}

	@Override
	protected void doTrace(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.print("doTrace");
	}
*/
}

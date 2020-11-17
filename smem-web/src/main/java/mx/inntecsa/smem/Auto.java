package mx.inntecsa.smem;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Auto {
	public Auto() {}
	
	public static void correcto(HttpServletResponse resp, String user, String pass) throws IOException {
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<t1>Hola "+user+", </t1>");
		out.println("<t1>Tu password es: "+pass+"</t1>");
		out.println("</body>");
		out.println("</html>");
	}
	
	public static void error(HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<t1>Esto es una Classe Coche</t1>");
		out.println("<t1>Y tus datos de Login son incorrectos</t1>");
		out.println("</body>");
		out.println("</html>");
	}
	
	public static void metodoClase(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String user = request.getParameter("user");
		String pass = request.getParameter("password");
	    String respuesta = "Respuesta desde JAVA Hola "+user+". Tu contrasenia es: "+pass;
	    
	    response.setContentType("text/plain");  
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(respuesta);
	}
}

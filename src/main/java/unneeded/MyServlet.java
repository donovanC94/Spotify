package unneeded;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("http://localhost:8080/")
public class MyServlet extends HttpServlet
{

    private static final long serialVersionUID = 1L;

    public MyServlet()
    {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //get the authorization code
        String code = request.getParameter("code");
        System.out.println("Code = " + code);
        //get tokens and use them as per your requirement using code
        //redirect user to the final destination
        response.sendRedirect("url");
    }
}


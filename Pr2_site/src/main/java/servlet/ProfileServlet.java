/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.UserDao;
import entity.User;
import html.HtmlFormer;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProfileServlet extends HttpServlet {

User u;
boolean l=false;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         u = (User) request.getSession().getAttribute("user");
         try (PrintWriter out = response.getWriter()) {
           
            out.println(html.HtmlFormer.contentProfile(html.HtmlFormer.top("profile "+u.getLogin(), u), html.HtmlFormer.end(), l));
         }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      String pass1 = request.getParameter("pass1");
      String pass2 = request.getParameter("pass2");
      String pass3 = request.getParameter("pass3");
       if(pass1.equals(u.getPass()) && pass2.equals(pass3)){
           User x= new User(u.getId(), u.getLogin(), pass2, u.getBasket());
           UserDao.update(x);
          l=false;
           request.getSession().setAttribute("user", x);
             response.sendRedirect("/Pr2_site/main");
       } else{
           l=true;
       response.sendRedirect("/Pr2_site/profile");
       }
         
    }

}

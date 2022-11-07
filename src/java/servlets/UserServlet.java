/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Role;
import models.User;
import services.RoleService;
import services.UserService;


/**
 *
 * @author puppi
 */
public class UserServlet extends HttpServlet {

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
      
         UserService us = new UserService();
        RoleService rs = new RoleService();
        String action = request.getParameter("action");
        
        try {
            ArrayList<User> users = us.getAllUser();
            ArrayList<Role> roles = rs.getAllRoles();
            request.setAttribute("users", users);
            request.setAttribute("roles", roles);
            
            //if edit is true, edit interface pops up,
            //else, add interface pops up
            
             if (action.equals("Add")){
                request.setAttribute("isEdit", false);
            }
        else if (action.equals("Edit")){
             request.setAttribute("isEdit", true);
        }
        } 
        catch (SQLException ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        UserService us = new UserService();
        String action = request.getParameter("action");

        String email = request.getParameter("email");
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String password = request.getParameter("password");
        String userRole = request.getParameter("roles");
        
         try {
        if (action.equals("add")){
                request.setAttribute(lname, us);
                us.newUser(email, fname, lname, password, 1);
            }
        else if (action.equals("update")){
            us.updateUser(email, fname, lname, password, 2);
        }
        }
         catch (SQLException ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

  

}

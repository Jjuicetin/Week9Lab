/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Role;
import models.User;

/**
 *
 * @author puppi
 */
public class RoleDB {
   
    //connect 2 database
    ConnectionPool pool = ConnectionPool.getInstance();
    Connection connection = pool.getConnection();
    
    //setup statement and result stuff
    PreparedStatement preStatement = null;
    ResultSet resultSet = null;
    
    public ArrayList<Role> getAllRoles() throws SQLException{
        String selectAll = "Select * from role;";
        //arraylist to store user 
        ArrayList<Role> roles = new ArrayList<>();
     
        
        try{
            preStatement = connection.prepareStatement(selectAll);
           resultSet = preStatement.executeQuery();
           
           while(resultSet.next()){
               int roleID = resultSet.getInt(1);
               String roleName = resultSet.getString(2);
               
               Role role = new Role(roleID, roleName);
               roles.add(role);
           }
        }
        finally {
            //close all the things that you've opened up
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preStatement);
            pool.freeConnection(connection);
        }
        return roles;
    }
    
    public String getRoleNames(int roleID) throws SQLException{
        
        String string = "SELECT role_name from role WHERE role_id = ?;";
        String result;
        
        try{
            preStatement = connection.prepareStatement(string);
            preStatement.setInt(1, roleID);
           resultSet = preStatement.executeQuery();
           
           //only giving one answer so its best to not loop this.
            resultSet.next();
            result = resultSet.getString(1);
           
        }
        
        finally{
            close();
            
        }
         return result;

        }
     private void close() {
        DBUtil.closePreparedStatement(preStatement);
        pool.freeConnection(connection);
        if (resultSet != null) {
            DBUtil.closeResultSet(resultSet);
        }
    }
    }


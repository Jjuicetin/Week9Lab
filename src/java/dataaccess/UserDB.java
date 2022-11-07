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
public class UserDB {
     //getAllUser() returns an array
    //getUser(email) returns a user object
    
    //connect 2 database
    ConnectionPool pool = ConnectionPool.getInstance();
    Connection connection = pool.getConnection();
    
    //setup statement and result stuff
    PreparedStatement preStatement = null;
    ResultSet resultSet = null;
    
    
    public ArrayList<User> getAllUser() throws SQLException{
        String selectAll = "Select * from user;";
        
        ArrayList<User> users = new ArrayList<>();
     
        
        try{
            preStatement = connection.prepareStatement(selectAll);
           resultSet = preStatement.executeQuery();
           
           while(resultSet.next()){
               String email = resultSet.getString(1);
               String firstName = resultSet.getString(2);
               String lastName = resultSet.getString(3);
               String password = resultSet.getString(4);
               Role role = new Role(resultSet.getInt(5));
               
               //make object and inject table values into user
               User user = new User(email, firstName, lastName, password, role);
               users.add(user);
           }
        }
        finally {
            //close all the things that you've opened up
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preStatement);
            pool.freeConnection(connection);
        }
           return users;
    }
    
    
    //get user based on email input
    public User getUser(String email) throws SQLException {
        User user = null;
        String searchUser = "SELECT * FROM user WHERE email = ?;";
        
        try{
        preStatement = connection.prepareStatement(searchUser);
        //The ? becomes email as input
        preStatement.setString(1, email);
        //run select sql
        resultSet = preStatement.executeQuery();
        
        if(resultSet.next()){
            String firstName = resultSet.getString(2);
            String lastName = resultSet.getString(3);
            String password = resultSet.getString(4);
            Role role = new Role(resultSet.getInt(5));
            user = new User(email, firstName, lastName, password, role);
            }
        }
        finally{
            close();
        }
        return user;
    }
    
    public void insertUser(String email, String firstName, String LastName, String password, int roleId) throws SQLException {
        String newUser = "INSERT INTO user VALUES (?, ?, ?, ?, ?);";
        
         try{
        preStatement = connection.prepareStatement(newUser);
       
        //The ? becomes email as input
        preStatement.setString(1, email);
        preStatement.setString(2, firstName);
        preStatement.setString(3, LastName);
        preStatement.setString(4, password);
        preStatement.setInt(5, roleId);
        
        //run insert on sql.
        preStatement.executeUpdate();
        
         }
         finally{
             close();
         }
    }
    public void updateUser(String email, String firstName, String LastName, String password, int roleId) throws SQLException{
        String update = "UPDATE user SET first_name = ?, last_name = ?, password = ?, role = ? WHERE email = ?;";
        
        try{
             preStatement = connection.prepareStatement(update);
             
             //fill in the ? with our little variables
             preStatement.setString(1, firstName);
             preStatement.setString(2, LastName);
             preStatement.setString(3, password);
             preStatement.setInt(4, roleId);
             preStatement.setString(5, email);
             
               //run update on sql.
        preStatement.executeUpdate();
        }
        finally{
            close();
        }
    }
    
    public void deleteUser(String email) throws SQLException{
        String delete = "DELETE FROM user WHERE email = ?;";
        
         try{
        preStatement = connection.prepareStatement(delete);
        preStatement.setString(1, email);
        
         //run insert on sql.
        preStatement.executeUpdate();
         }
         finally{
             close();
         }
    }
    
        //close everything down. connection, resultset, prepared statments
        private void close() {
        DBUtil.closePreparedStatement(preStatement);
        pool.freeConnection(connection);
        if (resultSet != null) {
            DBUtil.closeResultSet(resultSet);
        }
    }
    }



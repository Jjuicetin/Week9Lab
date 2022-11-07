/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.UserDB;
import java.sql.SQLException;
import java.util.ArrayList;
import models.User;

/**
 *
 * @author puppi
 */
public class UserService {
    
    public UserService(){
        //default constructor
    }
    //get all user
    public ArrayList<User> getAllUser() throws SQLException{
        
        UserDB userdb = new UserDB();
        return userdb.getAllUser();
    }
    
    //get user
    public User getUser(String email) throws SQLException{
        
        User user;
        user = new UserDB().getUser(email);
        
        return user;
    }
    
    //insert new user
    public void newUser(String email, String firstName, String LastName, String password, int roleId) throws SQLException{
        
        UserDB userdb = new UserDB();
        userdb.insertUser(email, firstName, LastName, password, roleId);
        
        }
    
    //update user info
    public void updateUser(String email, String firstName, String LastName, String password, int roleId) throws SQLException{
         UserDB userdb = new UserDB();
          userdb.updateUser(email, firstName, LastName, password, roleId);
    }
    
    //delete user
    public void deleteUser(String email) throws SQLException{
        UserDB userdb = new UserDB();
        userdb.deleteUser(email);
    }
}

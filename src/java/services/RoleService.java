/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.RoleDB;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Role;

/**
 *
 * @author puppi
 */
public class RoleService {
    
    public RoleService(){
        
    }
    
    public ArrayList<Role> getAllRoles() throws SQLException{
        RoleDB roledb = new RoleDB();
        
        return roledb.getAllRoles();
}
    public String getRoleNames(int roleID) throws SQLException{
        RoleDB roledb = new RoleDB();
        
        return roledb.getRoleNames(roleID);
    }
}
  
package com.revature.banking.daos;

import com.revature.banking.exceptions.ResourcePersistenceException;
import com.revature.banking.models.User;
import com.revature.banking.util.ConnectionFactory;
import com.revature.banking.util.logging.Logger;


import java.io.*;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDao implements Crudable<User>{

    private Logger logger = Logger.getLogger();

    @Override
    public User create(User newUser) {
        System.out.println("Here is the newTrainer as it enters our DAO layer: "+ newUser); // What happens here? Java knows to invoke the toString() method when printing the object to the terminal

        try(Connection conn = ConnectionFactory.getInstance().getConnection();) {

            // NEVER EVER EVER EVER EVER concatenate or directly use these strings inside of the sql statement
            // String sql = "insert into trainer value (" + newTrainer.getFname() + "," + newTrainer.getLname();

            // The commented out sql String is using default for auto-generating the ID ifyou used serial
            // String sql = "insert into trainer values (default, ?, ?, ?, ?, ?)"; // incomplete sql statement, with default if not specifiying columns
            String sql = "insert into user_info values (?, ?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            System.out.println(newUser.getFirstName());
            System.out.println(newUser.getLastName());

            // 1-indexed, so first ? starts are 1
            ps.setString(1, newUser.getFirstName());
            ps.setString(2, newUser.getLastName());
            ps.setString(3, newUser.getUsername());
            ps.setString(4, newUser.getPassword());
            ps.setString(5, newUser.getDob());

            int checkInsert = ps.executeUpdate();

            if(checkInsert == 0){
                throw new ResourcePersistenceException("User was not entered into database due to some issue.");
            }

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return newUser;
    }

    @Override
    public List<User> findAll() throws IOException {

        List<User> users = new LinkedList<>();

        // TODO: we trying something here and passing an argumetn???
        try (Connection conn = ConnectionFactory.getInstance().getConnection();) { // try with resoruces, because Connection extends the interface Auto-Closeable

            String sql = "select * from user_info";
            Statement s = conn.createStatement();

            // conn.createStatement().executeQuery("select * from trainer"); fine but generally not used
            // TODO: Hey why are we using the sql variable string here?
            ResultSet rs =s.executeQuery(sql);

            while (rs.next()) { // the last line of the file is null
                User user = new User();

                user.setFirstName(rs.getString("first_name")); // this column lable MUST MATCH THE DB
                user.setLastName(rs.getString("last_name"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setDob(rs.getString("dob"));

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }



        System.out.println("Returning user's information to user.");
        return users;
    }

    @Override
    public User findById(String id) {

        try(Connection conn = ConnectionFactory.getInstance().getConnection();){

            String sql = "select * from user_info where username = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(id)); // Wrapper class example

            ResultSet rs = ps.executeQuery(); // remember dql, bc selects are the keywords

            if(!rs.next()){
                throw new ResourcePersistenceException("User was not found in the database, please check ID entered was correct.");
            }

            User user = new User();

            user.setFirstName(rs.getString("first_name")); // this column lable MUST MATCH THE DB
            user.setLastName(rs.getString("last_name"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setDob(rs.getString("dob"));

            return user;

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }

    }



    @Override
    public boolean update(User updatedUser) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }




    public User authenticateUser(String username, String password){

        try (Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "select * from user_info where username = ? and password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if(!rs.next()){
                return null;
            }

            User user = new User();

            user.setFirstName(rs.getString("first_name")); // this column lable MUST MATCH THE DB
            user.setLastName(rs.getString("last_name"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setDob(rs.getString("dob"));

            return user;

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }


    }
    public boolean checkUsername(String username) {

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "select username from user_info where username = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if(!rs.isBeforeFirst()){
                return false;
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
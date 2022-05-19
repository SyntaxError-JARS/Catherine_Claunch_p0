package com.revature.banking.services;

import com.revature.banking.daos.UserDao;
import com.revature.banking.exceptions.AuthenticationException;
import com.revature.banking.exceptions.InvalidRequestException;
import com.revature.banking.exceptions.ResourcePersistenceException;
import com.revature.banking.models.User;
import com.revature.banking.util.logging.Logger;

import java.io.IOException;
import java.util.List;

public class UserServices implements Serviceable<User>{

    private UserDao userDao;
    private Logger logger = Logger.getLogger();

    public UserServices(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> readAll(){
        logger.info("Begin reading Users in our file database.");


        try {
            // TODO: What trainerDao intellisense telling me?
            List<User> users = userDao.findAll();
            logger.info("All users have been found here are the results: \n");
            return users;

        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User readById(String id) throws ResourcePersistenceException{

        User user = userDao.findById(id);
        return user;
    }

    @Override
    public User update(User updatedObject) {
        return null;
    }

    //@Override
    public boolean delete(String id) {
        return false;
    }

    public boolean validateUsernameNotUsed(String username){
        return userDao.checkUsername(username);
    }

    public User create(User newUser){
        logger.info("User trying to be registered: " + newUser);
        if(!validateInput(newUser)){ // checking if false
            // TODO: throw - what's this keyword?
            throw new InvalidRequestException("User input was not validated, either empty String or null values");
        }

        // TODO: Will implement with JDBC (connecting to our database)
        if(validateUsernameNotUsed(newUser.getUsername())){
            throw new InvalidRequestException("Username is already in use. Please try again with another email or login into previous made account.");
        }

        User persistedUser = userDao.create(newUser);

        if(persistedUser == null){
            throw new ResourcePersistenceException("User was not persisted to the database upon registration");
        }
        logger.info("User has been persisted: " + newUser);
        return persistedUser;
    }

    @Override
    public boolean validateInput(User newUser) {
        logger.debug("Validating Trainer: " + newUser);
        if(newUser == null) return false;
        if(newUser.getFirstName() == null || newUser.getFirstName().trim().equals("")) return false;
        if(newUser.getLastName() == null || newUser.getLastName().trim().equals("")) return false;
        if(newUser.getUsername() == null || newUser.getUsername().trim().equals("")) return false;
        if(newUser.getPassword() == null || newUser.getPassword().trim().equals("")) return false;
        return newUser.getDob() != null || !newUser.getDob().trim().equals("");
    }

    public User authenticateUser(String username, String password){

        if(password == null || password.trim().equals("") || password == null || password.trim().equals("")) {
            throw new InvalidRequestException("Either username or password is an invalid entry. Please try logging in again");
        }

        User authenticatedUser = userDao.authenticateUser(username, password);

        if (authenticatedUser == null){
            throw new AuthenticationException("Unauthenticated user, information provided was not consistent with our database.");
        }

        return authenticatedUser;

    }
}
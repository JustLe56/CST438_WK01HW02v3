package com.example.wk01hw02v3;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void validation_validUserCheck(){
        //Generate list of stored users
        ArrayList<User> userList = new ArrayList<User>();
        userList.add(new User(1,"test1","password"));
        userList.add(new User(2,"test2","password"));
        userList.add(new User(3,"test3","password"));

        Random r = new Random();
        int index = r.nextInt(3);
        User testValidUser = userList.get(index);

        //check given valid user
        assertEquals(userList.get(index),LoginActivity2.validatePassword(userList,testValidUser.getUsername(),testValidUser.getPassword()));

    }

    @Test
    public void validation_invalidUserCheck(){
        //Generate list of stored users
        ArrayList<User> userList = new ArrayList<User>();
        userList.add(new User(1,"test1","password"));
        userList.add(new User(2,"test2","password"));
        userList.add(new User(3,"test3","password"));

        //Generate list of invalid users to check against the list of stored users
        ArrayList<User> badUserList = new ArrayList<User>();
        badUserList.add(new User(1,"badUser1","badPassword"));
        badUserList.add(new User(2,"badUser2","badPassword"));
        badUserList.add(new User(3,"badUser3","badPassword"));

        Random r = new Random();
        int index = r.nextInt(3);
        User testBadUser = badUserList.get(index);

        //check null returned given invalid username
        assertNull(LoginActivity2.validatePassword(userList, testBadUser.getUsername(), testBadUser.getPassword()));

        //check returned does not match any user given invalid username
        assertNotEquals(userList.get(index),LoginActivity2.validatePassword(userList,testBadUser.getUsername(),testBadUser.getPassword()));
    }

    public void json_Test(){

    }
}
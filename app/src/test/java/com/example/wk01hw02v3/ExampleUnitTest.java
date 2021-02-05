package com.example.wk01hw02v3;

import android.content.Context;
import android.content.Intent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Random;


import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {

    private ArrayList<User> generateUserList(){
        ArrayList<User> userList = new ArrayList<User>();
        userList.add(new User(1,"test1","password"));
        userList.add(new User(2,"test2","password"));
        userList.add(new User(3,"test3","password"));

        return userList;
    }

    private User generateUser(ArrayList<User> userList){
        Random r = new Random();
        int index = r.nextInt(3);
        User testValidUser = userList.get(index);
        return testValidUser;
    }

    @Test
    public void validation_validUserCheck(){
        //Generate list of stored users
        ArrayList<User> userList = generateUserList();
        User testValidUser = generateUser(userList);

        //check function is returning a valid user when given a valid username
        assertEquals(testValidUser,LoginActivity2.validateUser(userList,testValidUser.getUsername()));

    }

    @Test
    public void validation_invalidUserCheck(){
        //Generate list of stored users
        ArrayList<User> userList = generateUserList();

        //Generate list of invalid users to check against the list of stored users
        ArrayList<User> badUserList = new ArrayList<User>();
        badUserList.add(new User(1,"badUser1","badPassword"));
        badUserList.add(new User(2,"badUser2","badPassword"));
        badUserList.add(new User(3,"badUser3","badPassword"));

        Random r = new Random();
        int index = r.nextInt(3);
        User testBadUser = badUserList.get(index);

        //check function is returning a null when given a invalid username
        assertNull(LoginActivity2.validateUser(userList,testBadUser.getUsername()));

        //check returned does not match any user given invalid username
        assertNotEquals(userList.get(index),LoginActivity2.validateUser(userList,testBadUser.getUsername()));

    }

    @Test
    public void validation_validPasswordCheck(){
        //Generate list of stored users
        ArrayList<User> userList = generateUserList();

        User testValidUser = generateUser(userList);

        //check function is returning true when given a valid user and the password associated with the user
        assertTrue(LoginActivity2.validatePassword(testValidUser,testValidUser.getPassword()));
    }

    @Test
    public void validation_invalidPasswordCheck() {
        ArrayList<User> userList = generateUserList();

        User testValidUser = generateUser(userList);

        assertFalse(LoginActivity2.validatePassword(testValidUser,""));
    }

    @Mock
    Context mockContext;

    @Test
    public void intent_TestIntent(){
        assertNotNull(LandingActivity.intentFactory(mockContext));
    }
}
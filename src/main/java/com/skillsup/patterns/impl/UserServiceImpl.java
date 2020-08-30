package com.skillsup.patterns.impl;

import com.skillsup.patterns.UserRole;
import com.skillsup.patterns.UserService;
import com.skillsup.patterns.database.DataBase;
import com.skillsup.patterns.dto.Credentials;
import com.skillsup.patterns.dto.User;
import com.skillsup.patterns.exeption.UnauthorizedAccessException;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserServiceImpl implements UserService {

    private static DataBase db=DataBase.getInstanceInit(null);

    private DataBase dataBase=DataBase.getInstanceInit(null);

    @Override
    public User createUser(Credentials credentials) {
        UserRole[] userRole ={UserRole.COMMON};
        User login=User.builder().id(findNextIdFromNewUser()).login(credentials.getLogin()).password(credentials.getPassword()).userRole(userRole).build();
        db.addUser(credentials,login);
        return db.findUser(credentials);
    }

    @Override
    public User createAdmin(Credentials credentials) {
        UserRole[] adminRole ={UserRole.ADMIN,UserRole.COMMON};
        User login=User.builder().id(findNextIdFromNewUser()).login(credentials.getLogin()).password(credentials.getPassword()).userRole(adminRole).build();
        db.addUser(credentials,login);
        return db.findUser(credentials);
    }

    @Override
    public User createBanned(Credentials credentials) {
        UserRole[] bannedRole ={UserRole.BANNED,UserRole.COMMON};
        User login=User.builder().id(findNextIdFromNewUser()).login(credentials.getLogin()).password(credentials.getPassword()).userRole(bannedRole).build();
        db.addUser(credentials,login);
        return db.findUser(credentials);
    }

    @Override
    public boolean deleteUser(long id) {
        return db.removeUser(id);
    }

    @Override
    public List<User> findAllUsers(Credentials credentials,Map<UserRole, Boolean> access) throws UnauthorizedAccessException {

        if(access.get(UserRole.ADMIN)==false){
            throw new UnauthorizedAccessException("Access denied, "+credentials.getLogin());
        }
        return db.findAll();
    }

    @Override
    public Long findNextIdFromNewUser() {
        return db.nextIdUser();
    }

    @Override
    public User findUser(Credentials credentials) {
        return DataBase.findUser(credentials);
    }

    public void addNewUser(Credentials credentials, Map<UserRole, Boolean> access) throws UnauthorizedAccessException {

        Scanner in =new Scanner(System.in);
        if(access.get(UserRole.ADMIN)==false){
            throw new UnauthorizedAccessException("Access denied, "+credentials.getLogin());
        }
        System.out.println("enter new login");
        String user=in.nextLine();
        System.out.println("enter password");
        String password=in.nextLine();

        Credentials credentialsNewUser=Credentials.bilder().login(user).password(password).build();
        if(createUser(credentialsNewUser)!=null){
            System.out.println("User add successful");
        }else{
            System.out.println("User no add ");
        }

    }

    public  void removeUser(Credentials credentials, Map<UserRole, Boolean> access) throws UnauthorizedAccessException {

        Scanner in =new Scanner(System.in);
        if(access.get(UserRole.ADMIN)==false){
            throw new UnauthorizedAccessException("Access denied, "+credentials.getLogin());
        }

        System.out.println("enter id remove user");
        Long idUser=in.nextLong();

        if(db.removeUser(idUser)){
            System.out.println("User removing successful");
        }else{
            System.out.println("User no remove ");
        }

    }


}

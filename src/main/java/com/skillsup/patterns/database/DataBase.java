package com.skillsup.patterns.database;

import com.skillsup.patterns.dto.Credentials;
import com.skillsup.patterns.dto.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBase {

    private static  Map<Credentials, User> db;
    private static DataBase instance;

    private DataBase(Map<Credentials, User> db) {
        this.db = db;
    }

    public static DataBase getInstanceInit(Map<Credentials, User> bd) {
        if (instance == null) {
            instance = new DataBase(new HashMap<Credentials, User>());
        }
        return instance;
    }

    public static Map<Credentials, User> getDataBase() {
        return db;
    }

    public static User findUser(Credentials credentials){

        for(Map.Entry<Credentials,User> users: db.entrySet()){
            Credentials key=users.getKey();
            if(key.getLogin().equals(credentials.getLogin())&&key.getPassword().equals(credentials.getPassword())){
                return users.getValue();
            }
        }
        return null;
    }

    public boolean addUser(Credentials credentials,User user){

        try{
            db.put(credentials,user);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean removeUser(long id){
        for(Map.Entry<Credentials,User> users: db.entrySet()){
            User user=  users.getValue();
            Credentials credentials=  users.getKey();

            if(user.getId()==id){
                try{
                    db.remove(credentials);
                    return true;
                }catch (Exception ex){
                    return false;
                }
            }
        }
        return false;
    }

    public static long nextIdUser(){
        long id=1l;

        for(Map.Entry<Credentials,User> users: db.entrySet()){
            User user=  users.getValue();
            if(user.getId()>=id){
                id=user.getId()+1;
            }
        }
        return id;
    }

    public List<User> findAll() {

        List<User> list= new ArrayList<>();
        for(Map.Entry<Credentials,User> users: db.entrySet()){
            list.add(users.getValue());
        }

        return list;
    }
}

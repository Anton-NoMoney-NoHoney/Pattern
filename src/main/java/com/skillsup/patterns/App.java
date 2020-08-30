package com.skillsup.patterns;

import com.skillsup.patterns.checUser.*;
import com.skillsup.patterns.dto.Credentials;
import com.skillsup.patterns.dto.User;
import com.skillsup.patterns.exeption.UnauthorizedAccessException;
import com.skillsup.patterns.impl.UserAuthenticatorImpl;
import com.skillsup.patterns.impl.UserServiceImpl;

import java.util.*;


public class App {

    private static UserAuthenticator userAuthenticator;
    private static UserService userService=new UserServiceImpl();

    public static void main( String[] args )  {

        Scanner in =new Scanner(System.in);
        init();
        initDefaultUser();

        String repaid;
        do{
            Map<UserRole, Boolean> access=accessInit();
            System.out.println("enter your login");
            String user=in.nextLine();
            System.out.println("enter your password");
            String password=in.nextLine();

            Credentials credentials=Credentials.bilder().login(user).password(password).build();
            userAuthenticator.logIn(credentials,access);
            accessContent(credentials,access);

            System.out.println("Want to continue Y -yes");
            repaid=in.nextLine();
        }while (repaid.equals("Y"));
    }

    private static void accessContent(Credentials credentials, Map<UserRole, Boolean> access) {
        if(access.get(UserRole.COMMON)==true||access.get(UserRole.ADMIN)==true) {
            Scanner in = new Scanner(System.in);
            System.out.println("functional: \n  0- write forum \n  1- add user\n  2- remove user\n  3- find all user");
            String functional = in.nextLine();
            try {
                switch (functional) {
                    case "0":
                        writeForum(credentials, access);
                        break;
                    case "1":
                        userService.addNewUser(credentials, access);
                        break;
                    case "2":
                        userService.removeUser(credentials, access);
                        break;
                    case "3":
                        listingUser(userService.findAllUsers(credentials, access));
                        break;
                    default:
                        System.out.println("unknown function");
                }
            } catch (UnauthorizedAccessException exception) {
                exception.printStackTrace();
            }
        }else{
            System.out.println("There is no content for you");
        }

    }

    private static void listingUser(List<User> allUsers) {

        for (User user:allUsers){
            System.out.printf("id: %s, login: %s, password: %s, role: %s\n",user.getId(),user.getLogin(),user.getPassword(), Arrays.toString(user.getUserRole()));
        }

    }


    private static void init() {
        userAuthenticator = new UserAuthenticatorImpl();
        ChainOfResponsibilityEntryPoint middleware = new UserExistChaioOfResponsibility(userAuthenticator);
        middleware.linkWith(
                    new ChecRoleBannedChaioOfResponsibilit())
                    .linkWith(new ChecRoleCommonChaioOfResponsibilit())
                    .linkWith(new ChecRoleAdminChaioOfResponsibilit());

        userAuthenticator.setMiddleware(middleware);
    }

    private static void initDefaultUser() {
        Credentials credentialsAdmin=Credentials.bilder().login("admin").password("admin").build();
        userService.createAdmin(credentialsAdmin);

        Credentials credentialsUser=Credentials.bilder().login("user").password("user").build();
        userService.createUser(credentialsUser);

        Credentials credentialsBanned=Credentials.bilder().login("banned").password("banned").build();
        userService.createBanned(credentialsBanned);

        System.out.println("Hello Work!");
        System.out.println("Login:admin Password: admin");
        System.out.println("Login:user Password: user");
        System.out.println("Login:banned Password: banned");
    }

    private static void writeForum(Credentials credentials,Map<UserRole, Boolean> access) {
        if(access.get(UserRole.COMMON)==true||access.get(UserRole.ADMIN)==true){
            System.out.printf("Hi %s You access write forum\n", credentials.getLogin());
        }
    }

    private static Map<UserRole, Boolean> accessInit() {
        Map<UserRole, Boolean> access=new HashMap<>();
        access.put(UserRole.BANNED,false);
        access.put(UserRole.COMMON,false);
        access.put(UserRole.ADMIN,false);
        return access;
    }

}
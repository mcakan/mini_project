package mini_project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserService {


    List<String> usuernameList = new ArrayList<>();
    List<String> emailList = new ArrayList<>();
    List<String> passwordList = new ArrayList<>();

    public void showMenu(){
        System.out.println("---MONSTER---");
        System.out.println("1- Create an account ");
        System.out.println("2- Log in ");
        System.out.println("3- Log out ");
        System.out.println("Your choice :");


    }
    public void register (){

        Scanner input = new Scanner(System.in);
        System.out.println("Enter name and surname:");
        String name = input.nextLine();

        String username;
        boolean existUsername;

        do {
            System.out.println("Enter username:");
            username = input.nextLine();
            existUsername = usuernameList.contains(username);
            if (existUsername){

                System.out.println("This username has been used before. Try a new username..");
            }
        }while (existUsername);



        String email;
        boolean isValid;
        boolean existEmail;
        do {
            System.out.println("Enter email:");
            email = input.nextLine().trim();
            isValid =validateEmail(email);

            existEmail = emailList.contains(email);

            if (existEmail){
                isValid = false;
                System.out.println("This email has been used before. Try a new email..");
            }

        }while (!isValid);



        String password;
        boolean isValidPassword;
        do {
            System.out.println("Enter your password:");
            password = input.nextLine();
            isValidPassword = validatePassword(password);

        }while (!isValidPassword);


        mini_project.User user = new mini_project.User(name,username,email,password);
        usuernameList.add(username);
        emailList.add(email);
        passwordList.add(password);

        System.out.println(user);
        System.out.println("Congratulations, your registration has been completed.");
        System.out.println("You can login to the system with your username or email and password.");

    }

    public void login (){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter username or email:");
        String usernameOrEmail = input.nextLine();

        boolean isEmail = emailList.contains(usernameOrEmail);
        boolean isUsername = usuernameList.contains(usernameOrEmail);

        if (isEmail || isUsername){


            while (true){
                System.out.println("Enter your password:");
                String password = input.nextLine();

                int idx;
                if (isUsername){
                    idx = usuernameList.indexOf(usernameOrEmail);

                }else {
                    idx = emailList.indexOf(usernameOrEmail);
                }

                if (passwordList.get(idx).equals(password)){
                    System.out.println("You have logged into the system.");
                    break;

                }else {
                    System.out.println("Your password is wrong. Try again..");
                }

            }

        }else {
            System.out.println("No registered user found..");
            System.out.println("Check your information or sign up..");
        }

    }


    public static boolean validateEmail (String email){
        boolean isValid;

        boolean space = email.contains(" ");
        boolean isContainAt = email.contains("@");

        if (space){
            System.out.println("Email cannot contain spaces.");
            isValid = false;
        }else if (!isContainAt){
            System.out.println("Email must contain '@' ");
            isValid = false;
        }else {
            String firstPart = email.split("@")[0];
            String secondPart = email.split("@")[1];

            boolean checkFirstPart = firstPart.replaceAll("[a-zA-Z0-9_.-]","").length()==0;

            boolean checkSecondPart = secondPart.equals("gmail.com") ||
                    secondPart.equals("hotmail.com");


            if (!checkFirstPart){
                System.out.println("Email lowercase letters, uppercase letters, numbers and '- . _' cannot contain any characters other than ");
            } else if (!checkSecondPart) {
                System.out.println("Email must end with gmail.com or hotmail.com");
            }
            isValid = checkFirstPart && checkSecondPart;

        }

        return isValid;
    }

    public static boolean validatePassword (String password){

        boolean isValid;

        boolean space = password.contains(" ");
        boolean lengthGt6 = password.length()>=6;
        boolean existUpper = password.replaceAll("[^A-Z]","").length()>0;
        boolean existLower = password.replaceAll("[^a-z]","").length()>0;
        boolean existDigit = password.replaceAll("[^0-9]","").length()>0;
        boolean existSymbol = password.replaceAll("[\\P{Punct}]","").length()>0;


        if (space){
            System.out.println("Password cannot contain spaces..");
        }else if (!lengthGt6){
            System.out.println("Password must contain at least 6 characters..");
        }else if (!existUpper){
            System.out.println("Password should contain at least 1 uppercase character..");
        }else if (!existLower){
            System.out.println("Password should contain at least 1 lowercase character..");
        }else if (!existDigit) {
            System.out.println("Password should contain at least 1 digit..");
        }else if (!existSymbol){
            System.out.println("Password should contain at least 1 symbol..");
        }


        isValid = !space && lengthGt6 && existUpper && existLower && existDigit && existSymbol;


        if (!isValid){
            System.out.println("Try again..");
        }


        return isValid;
    }



}

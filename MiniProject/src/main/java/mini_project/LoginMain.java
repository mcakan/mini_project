package mini_project;

import java.util.Scanner;


public class LoginMain {
    public static void main(String[] args) {

        start();

    }
    public static void start(){
        Scanner input = new Scanner(System.in);

        mini_project.UserService userService = new mini_project.UserService();
        int select;


        do {
            userService.showMenu();
            select = input.nextInt();
            switch (select){
                case 1:
                    userService.register();
                    break;
                case 2:
                    userService.login();
                    break;
                case 3:
                    System.out.println("Have a nice day...");
                    break;
                default:
                    System.out.println("You made an incorrect entry. Try again..");

            }




        }while (select!=3);


    }






}


package bank;

import java.util.Scanner;

import javax.security.auth.login.LoginException;

public class Menu {
  private Scanner scanner;

public static void main(String[] args) {
    System.out.println("Welcome to Globe Bank International!");

    Menu menu = new Menu();
    menu.scanner = new Scanner(System.in);

    Customer customer =menu.authentificateUser();

    if(customer != null){
      Account account = DataSourse.getAccount(customer.getAccountId());
      menu.showMenu(customer, account);
    }

    menu.scanner.close();
  }

  private Customer authentificateUser(){
    System.out.println("Please enter your username");
    String username = scanner.next();

    System.out.println("Please enter your password");
    String password = scanner.next();

    Customer customer = null;
    try{
      customer = Authentificator.login(username, password);
    } catch(LoginException e) {
      System.out.println("There was an error: " + e.getMessage());
    }

    return customer;
  
  }

  private void showMenu(Customer customer, Account account){
    int selection = 0;

    while(selection != 4 && customer.isAuthentificated()){
      System.out.println("================================================");
      System.out.println("Please select one of the following options");
      System.out.println("1: Deposit");
      System.out.println("2: Withdraw");
      System.out.println("3: Check Balance");
      System.out.println("3: Exit");
      System.out.println("================================================");

      selection = scanner.nextInt();
      double amount = 0;

      switch (selection) {
        case 1:
          System.out.println("How much would you like to deposit?");
         amount = scanner.nextDouble();
         account.deposit(amount);
      }
    }
  }
}

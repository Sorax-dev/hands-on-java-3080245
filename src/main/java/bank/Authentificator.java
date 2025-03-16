package bank;

import javax.security.auth.login.LoginException;

public class Authentificator {

  public static Customer login(String username, String password) throws LoginException {
    Customer customer = DataSourse.getCustomer(username);
    if(customer == null){
      throw new LoginException("Username not found");
    }

    if(password.equals(customer.getPassword())){
      customer.setAuthentificated(true);
      return customer;
    }

    else throw new LoginException("incorrent password");
  }

  public static void logout(Customer customer){
    customer.setAuthentificated(false);
  }
}

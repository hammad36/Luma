package utils;

import org.testng.annotations.DataProvider;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDataProvider {


    //Sign up data

    private String generateUniqueEmail() {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        return "hammad" + timestamp + "@gmail.com";
    }

    @DataProvider(name = "validSignupData")
    public Object[][] validSignupData() {
        return new Object[][]{
                {"Mohamed", "Hammad", generateUniqueEmail(), "StrongPass123!", "StrongPass123!", "Thank you for registering with Main Website Store."}
        };
    }

    @DataProvider(name = "weakPasswordData")
    public Object[][] weakPasswordData() {
        return new Object[][]{
                {"Laila", "Adel", "laila.test@gmail.com", "1234", "1234", "Minimum length of this field must be equal or greater than 8 symbols. Leading and trailing spaces will be ignored."}
        };
    }

    @DataProvider(name = "passwordComplexityInvalidData")
    public Object[][] passwordComplexityInvalidData() {
        return new Object[][]{
                {"Laila", "Adel", "laila.test@gmail.com", "A1234678", "A1234678", "Minimum of different classes of characters in password is 3. Classes of characters: Lower Case, Upper Case, Digits, Special Characters."}
        };
    }

    @DataProvider(name = "invalidEmailData")
    public Object[][] invalidEmailData() {
        return new Object[][]{
                {"Omar", "Ali", "omar.gmail.com", "Password@123", "Password@123", "Please enter a valid email address (Ex: johndoe@domain.com)."}
        };
    }

    @DataProvider(name = "emptyFieldsData")
    public Object[][] emptyFieldsData() {
        return new Object[][]{
                // Case: only email is missing
                {"Ali", "Sayed", "", "Password@123", "Password@123", "This is a required field."},

                // Case: only password confirmation is missing
                {"Ali", "Sayed", "ali@example.com", "Password@123", "", "This is a required field."},

                // Case: all fields are empty
                {"", "", "", "", "",
                        "This is a required field.\nThis is a required field.\nThis is a required field.\nThis is a required field.\nThis is a required field."}
        };
    }

    @DataProvider(name = "passwordMismatchData")
    public Object[][] passwordMismatchData() {
        return new Object[][]{
                {"Tarek", "Ibrahim", "tarek.qa@gmail.com", "Password123", "Password124", "Please enter the same value again."}
        };
    }

    @DataProvider(name = "alreadyRegisteredEmailData")
    public Object[][] alreadyRegisteredEmailData() {
        return new Object[][]{
                {"Ahmed", "Youssef", "ahmed.youssef01@gmail.com", "Pass1234!", "Pass1234!",
                        "There is already an account with this email address. If you are sure that it is your email address, click here to get your password and access your account."}
        };
    }

    @DataProvider(name = "longInputValuesData")
    public Object[][] longInputValuesData() {
        return new Object[][]{
                {
                        "MohamedMohamedMohamedMohamedMohamedMohamedMohamedMohamedMohamedMohamedMohamedMohamedMohamedMohamedMohamedMohamed",
                        "HammadHammadHammadHammadHammadHammadHammadHammadHammadHammadHammadHammadHammadHammadHammadHammadHammadHammadHammad",
                        "hammadhammadhammadhammadhammadhammadhammadhammadhammadhammadhammadhammadhammadhammadhammad@gmail.com",
                        "Hammmadhammadhammadhammadhammadhammadhammad@123",
                        "Hammmadhammadhammadhammadhammadhammadhammad@123",
                        "\"Email\" uses too many characters."
                }
        };
    }

    //Log in Data
    @DataProvider(name = "ValidLoginData")
    public Object[][] ValidLoginData() {
        return new Object[][]{
                {
                        "hammad32@gmail.com", "Hammad@2533" ,"Welcome, Mohammed Hammad!"
                }
        };
    }

    @DataProvider(name = "inValidEmailData")
    public Object[][] inValidEmailData() {
        return new Object[][]{
                {
                    "hammad20232@gmail.com", "Hammad@2533" ,
                        "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later."
                }
        };
    }

    @DataProvider(name = "inValidPasswordData")
    public Object[][] inValidPasswordData (){
        return new Object[][]{
                {
                        "hammad32@gmail.com","Hammad@5542533" ,
                        "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later."
                }
        };
    }

    @DataProvider(name = "emptyLoginFieldsData")
    public Object[][] emptyLoginFieldsData () {
        return new Object[][]{
                // Case: only email is missing
                {"", "Hammad@2533", "This is a required field."},

                // Case: only password  is missing
                {"hammad32@gmail.com", "", "This is a required field."},

                // Case: all fields are empty
                {"", "", "This is a required field.\nThis is a required field."}
        };
    }

    @DataProvider(name = "forgetPasswordData")
    public Object[][] forgetPasswordData () {
        return new Object[][]{
                {"Please enter your email address below to receive a password reset link."}
        };
    }
}

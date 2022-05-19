package com.revature.banking.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {

    // Encapsulated these variables/attributes to the class or instance thereof
    // Another pillar of OOP Encapsulation
    private String firstName;
    private String lastName;
    private String username;
    @JsonIgnore
    private String password;
    private String dob;


//    public Trainer(){}

    // public is allowing any instance of class to leverage this command
    // This is a constructor because it's using the class name
    // This requires all atttributes defined above to be passed
    // This assigns each argument to their respective parameter variable being fname, lname, email, etc
    // We assign this instance of the objects the passed argumented.
    // So now, "this" is refering to the instance and we are setting this.fname to equal the passed arugment that was assign fname

    // Overloading constructors another subset of polymorphism
    public User(String firstName, String lastName, String username, String password, String dob) {
        super(); // just always there, by default of EVERY CLASS is Object
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.dob = dob;
    }

    public User(String password){
        this.password = password;
    }

    // public is allowing any instance of class to leverage this command
    // This is a constructor because it's using the class name
    // this constructor requires no Arguments to be passed.
    public User() {

    }

    // Getters & Setters
    public String getFirstName() {
        return firstName;
    }

    // public is letting every instance of this class use the .setFname anywhere.
    // void - this means it will return nothing to me
    // setFname() - to reassign fname attribute
    // method takes in paramerters of String that will be assigned fname
    //this - is the keyword to indicate it's refering to the particular instance of that class
    // this.fname is refering to the attribute in that class
    // we are setting this objects fname attribute to equal the fname that has been provided as an argument
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // public is letting every instance of this class use the .getLName anywhere.
    // returning a String value when called
    // getLname() is a method to retrieve this instances lname attribute
    // no arguments required
    // return a string which happens to be our attribute lname
    public String getLastName() {
        return lastName;
    }

    // trainer.lname = "Jester" is bad, because you could reassign on accident if it were say and int and you did
    // trainer.age = 10;
    // This allows us to explicitly state we are setting the lname variablem, or reassigning it
    // Trainer trainer = new Trainer();
    // trainer.setLname("Jester")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String toFileString() {
        // StringBuilder, there is also a StringBuffer (it's thread-safe)
        // Is another class for Strings that allows them to be mutated
        StringBuilder mutableString = new StringBuilder();
        mutableString
                .append(firstName).append(",")
                .append(lastName).append(",")
                .append(username).append(",")
                .append(password).append(",")
                .append(dob);

        // Without changing the mutableString class from StringBuilder we wont' have an appropriate return type
        return mutableString.toString(); // We need the toString to return it to it's appropriate type
    }

    @Override // What this is?? Annotation - basically metadata
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", dob='" + dob + '\'' +
                '}';
    }


}
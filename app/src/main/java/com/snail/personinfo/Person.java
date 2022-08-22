package com.snail.personinfo;

/** Class for save persons
 *
 */
public class Person {

    /**
     * < TAG for class name debug
     */
    private final String TAG = this.getClass().getSimpleName();
    /**
     * < user's email
     */
    protected String email;
    /**
     * < user's name
     */
    protected String name;
    /**
     * < user's surname
     */
    protected String surname;
    /**
     * < user's age
     */
    protected int age;
    /**
     * < user's gender
     */
    protected byte gender;   /**< user's gender*/

    /**
     * Default constructor of person
     */
    public Person() {

    }

    /**
     * Constructor for class Person
     *
     * @param email   user's email
     * @param name    user's name
     * @param surname user's surname
     * @param age     user's age
     * @param gender  user's gender
     */
    public Person(String email, String name, String surname, int age, byte gender) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.gender = gender;
    }

    /**
     * Getter user's email
     *
     * @return user's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Getter user's name
     *
     * @return user's name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter user's surname
     *
     * @return user's surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Getter user's age
     *
     * @return user's age
     */
    public int getAge() {
        return age;
    }

    /**
     * Getter user's gender
     *
     * @return user's gender
     */
    public byte getGender() {
        return gender;
    }

    /**
     * Setter user's email
     *
     * @param email user's email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Setter user's name
     *
     * @param name user's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter user's surname
     *
     * @param surname user's surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Setter user's age
     *
     * @param age user's age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Setter user's gender
     *
     * @param gender user's gender
     */
    public void setGender(byte gender) {
        this.gender = gender;
    }
}
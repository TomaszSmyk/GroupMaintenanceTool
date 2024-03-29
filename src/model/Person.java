package model;

/**
 * Interface used to create level of abstraction
 */
public interface Person {
    String getID();
    String getFirstName();
    String getLastName();
    int getGroupNumber();
    int getIndex();
    String getEmail();
    boolean getPresence();
    void setPresence(boolean isPresent);
    Object[] getData();
}

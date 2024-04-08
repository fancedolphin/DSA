package CHC5223;

/**
 *
 * DO NOT CHANGE THIS INTERFACE
 * You must create a class that implements this interface
 *
 * objects of a class implementing this interface holds information
 * about a Member
 */

public interface IMember {

    /**
     * Retrieves the name of the member
     * @pre true
     * @return the name of the member
     */
    public String getName();

    /**
     * Retrieves the member's affiliation
     * @pre true
     * @return the member's affiliation
     */
    public String getAffiliation();

    @Override
    /**
     * @return the member's name and affiliation as string
     */
    public String toString();
}

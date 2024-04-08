package CHC5223;


public class Member implements IMember{
    String fullName;
    String affiliation;

    public Member(String name, String affiliation){
        this.fullName = name;
        this.affiliation = affiliation;
    }
    public String getName() {
        return fullName;
    }
    public String getAffiliation() {
        return affiliation;
    }


}

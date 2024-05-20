package CHC5223;

import java.util.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
/**
 * @author 
 */

public class CHC5223 {
static Scanner kb;
static IMemberDB db = new MemberBST();


static String fileName = "sampleMembersUK.csv";

private static String readNonEmpty() {
    // read non-null, non-empty string;
    String s = kb.nextLine().trim();
    while (s == null || s.equals("")) {
        System.out.print("must not be blank -- try again: ");
        s = kb.nextLine().trim();
    }
    assert s != null && !s.trim().equals("");
  return s;
}

public static void main(String[] args) {       
    String option, load, name, affiliation;                  
    Member resp;
    kb = new Scanner(System.in);
    System.out.print("Load file? Y/N "); 
    load = readNonEmpty();
    if(load.charAt(0) == 'Y' || load.charAt(0) == 'y') 
        loadFile();
    System.out.print("D)isplay  P)ut  G)et  C)ontains  S)ize  R)emove  Q)uit? ");
    option = readNonEmpty();
    while(option.charAt(0)!= 'Q' && option.charAt(0)!= 'q') {
        switch (option.charAt(0)) {
            case 'D': case'd':  // display
              db.displayDB();
              break;

            case 'P': case'p':  // put
              System.out.print("Name? "); name = readNonEmpty();
              System.out.print("Affiliation? "); affiliation = readNonEmpty();
              Member member = new Member(name, affiliation);
              resp = db.put(member);
              System.out.print(name);
              if(resp == null) System.out.println(" : new member added");
              else System.out.println(" : member overridden");
              break; 

            case 'S': case's':  //size
              System.out.println("Size "+ db.size()); 
              break;

            case 'G': case'g':  // get
              System.out.print("Name? "); name = readNonEmpty();                   
              resp = db.get(name);
              if(resp != null) System.out.println(resp.toString());
              else System.out.println(name + " not found");
              break; 

            case 'C': case'c':  // contains
              System.out.print("Name? "); name = readNonEmpty();    
              System.out.print(name);
              if(db.containsName(name)) System.out.println(" found");
              else System.out.println(" not found");
              break;

            case 'R': case'r':  // remove
              System.out.print("Name? "); name = readNonEmpty();            
              resp = db.remove(name); 
              System.out.print(name);
              if(resp != null) System.out.println(" deleted");
              else System.out.println(" not found");
              break;
                           
            default: //?
              System.out.println("unknown option");

        } // switch
        System.out.print("D)isplay  P)ut  G)et  C)ontains  S)ize  R)emove  Q)uit? ");
        option = readNonEmpty();                    
     } // while
}

    private static void loadFile() {
        String cvsSplitBy = ",";
        Scanner file; 
        Member member;
        try {
          FileInputStream streamIn = new FileInputStream(fileName);
          file = new Scanner(streamIn);
          while (file.hasNextLine()) {
            String line = file.nextLine();
            String[] parts = line.split(cvsSplitBy);
            String surname = parts[0].trim();
            String firstNames = parts[1].trim();
            String affiliation = parts[2].trim();
            member = new Member(surname + ", " + firstNames, affiliation);
            db.put(member);
          }
          file.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Can't find file " + fileName);
        }
    }
}
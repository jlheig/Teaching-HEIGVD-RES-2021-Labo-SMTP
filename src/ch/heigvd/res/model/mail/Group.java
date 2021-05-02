package ch.heigvd.res.model.mail;

import java.util.ArrayList;
import java.util.List;

public class Group {

    private final List<Person> members = new ArrayList<>();

    /**
     *
     * @param person, adds a person to a group
     */
    public void addMember(Person person){
        members.add(person);
    }

    /**
     *
     * @return, the list of the members of the group
     */
    public List<Person> getMembers(){
        return members;
    }
}

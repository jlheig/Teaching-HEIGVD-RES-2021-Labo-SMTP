package config;

import model.mail.Person;

import java.util.ArrayList;

public interface IConfigurationManager {
    ArrayList<Person> getVictims();
    ArrayList<String> getMessages();
    int getNumberOfGroups();
    ArrayList<Person> getWitnessesToCC();
    int getSmtpServerPort();
    String getSmtpServerAdress();
}

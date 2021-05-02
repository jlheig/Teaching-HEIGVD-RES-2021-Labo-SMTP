package ch.heigvd.res.config;

import ch.heigvd.res.model.mail.Person;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConfigurationManager implements IConfigurationManager {

    private String smtpServerAdress;
    private int smtpServerPort;
    private final ArrayList<Person> victims;
    private final ArrayList<String> messages;
    private int numberOfGroups;
    private ArrayList<Person> witnessesToCC;

    public ConfigurationManager() throws IOException{
        victims = loadAddressesFromFile("./settings/victims.utf8");
        messages = loadMessagesFromFile("./settings/messages.utf8");
        loadProperties("./settings/config.properties");
    }

    private void loadProperties(String filename) throws IOException {
        FileInputStream fis = new FileInputStream(filename);
        Properties properties = new Properties();
        properties.load(fis);
        this.smtpServerAdress = properties.getProperty("smtpServerAddress");
        this.smtpServerPort = Integer.parseInt(properties.getProperty("smtpServerPort"));
        this.numberOfGroups = Integer.parseInt(properties.getProperty("numberOfGroups"));

        this.witnessesToCC = new ArrayList<>();
        String witnesses = properties.getProperty("witnessesToCC");
        String[] witnessesAddresses = witnesses.split(",");
        for(String address : witnessesAddresses){
            this.witnessesToCC.add(new Person(address));
        }
    }

    private ArrayList<Person> loadAddressesFromFile(String filename) throws IOException {
        ArrayList<Person> result;
        try(FileInputStream fis = new FileInputStream(filename)){
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            try(BufferedReader reader = new BufferedReader(isr)){
                result = new ArrayList<>();
                String address = reader.readLine();
                while (address != null){
                    result.add(new Person(address));
                    address = reader.readLine();
                }
            }
        }
        return result;
    }

    private ArrayList<String> loadMessagesFromFile(String filename) throws IOException {
        ArrayList<String> result;
        try(FileInputStream fis = new FileInputStream(filename)){
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            try(BufferedReader reader = new BufferedReader(isr)){
                result = new ArrayList<>();
                String line = reader.readLine();
                while(line != null){
                    StringBuilder body = new StringBuilder();
                    while((line != null) && (!line.equals("=="))){
                        body.append(line);
                        body.append("\r\n");
                        line = reader.readLine();
                    }
                    result.add(body.toString());
                    line = reader.readLine();
                }
            }
        }
        return result;
    }

    @Override
    public ArrayList<Person> getVictims(){
        return victims;
    }

    @Override
    public ArrayList<String> getMessages(){
        return messages;
    }

    @Override
    public int getNumberOfGroups(){
        return numberOfGroups;
    }

    @Override
    public ArrayList<Person> getWitnessesToCC(){
        return witnessesToCC;
    }

    @Override
    public int getSmtpServerPort(){
        return smtpServerPort;
    }

    @Override
    public String getSmtpServerAdress(){
        return smtpServerAdress;
    }
}

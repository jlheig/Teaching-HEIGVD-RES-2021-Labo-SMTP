package ch.heigvd.res;

import ch.heigvd.res.config.ConfigurationManager;
import ch.heigvd.res.config.IConfigurationManager;
import ch.heigvd.res.model.prank.Prank;
import ch.heigvd.res.model.prank.PrankGenerator;
import ch.heigvd.res.smtp.ISmtpClient;
import ch.heigvd.res.smtp.SmtpClient;

import java.util.List;

public class MailRobot {

    public static void main(String[] args) {
        try{
            IConfigurationManager configurationManager = new ConfigurationManager();

            //Getting the information to setup the smtp client
            String address = configurationManager.getSmtpServerAdress();
            int port = configurationManager.getSmtpServerPort();
            ISmtpClient smtpClient = new SmtpClient(address, port);

            //Generating the prank messages
            PrankGenerator pg = new PrankGenerator(configurationManager);
            List<Prank> pranks = pg.generatePranks();

            //sending the prank messages
            for(Prank prank : pranks) {
                smtpClient.sendMessage(prank.generateMailMessage());
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}


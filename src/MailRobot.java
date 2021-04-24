import config.ConfigurationManager;
import config.IConfigurationManager;
import model.prank.Prank;
import model.prank.PrankGenerator;
import smtp.ISmtpClient;
import smtp.SmtpClient;

import java.util.List;

public class MailRobot {
    public static void main(String[] args) {
        try{
            IConfigurationManager configurationManager = new ConfigurationManager();

            String address = configurationManager.getSmtpServerAdress();
            int port = configurationManager.getSmtpServerPort();
            ISmtpClient smtpClient = new SmtpClient(address, port);

            PrankGenerator pg = new PrankGenerator(configurationManager);

            List<Prank> pranks = pg.generatePranks();
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


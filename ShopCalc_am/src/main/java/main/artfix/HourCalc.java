package main.artfix;

import java.io.BufferedReader;
import java.io.FileReader;

public class HourCalc {
    public static void workHour(Boolean exceptionCheck) throws Exception {
        StringBuilder sb1 = new StringBuilder();
        BufferedReader br1 = new BufferedReader(new FileReader("day.txt"));
        String line1;
        while ((line1 = br1.readLine()) != null) {
            sb1.append(line1).append("\n");
        }
        br1.close();
        String dataHour = sb1.toString();

        Time.hour();



        switch (Time.hourTime) {
            case "20":
                if (Memory.Time20) {
                    Time.now();
                    MailSend.send(Memory.adminMail, "2 ժամը մեկվա հաշվարկ - " + Time.nowTime + "\n" + dataHour);
                    Memory.Time20 = false;
                }
                break;

            case "14":
                if (Memory.Time14) {
                    Time.now();
                    MailSend.send(Memory.adminMail, "2 ժամը մեկվա հաշվարկ - " + Time.nowTime + "\n" + dataHour);
                    Memory.Time14 = false;
                }
                break;

            case "16":
                if (Memory.Time16) {
                    Time.now();
                    MailSend.send(Memory.adminMail, "2 ժամը մեկվա հաշվարկ - " + Time.nowTime + "\n" + dataHour);
                    Memory.Time16 = false;
                }
                break;
            case "18":
                if (Memory.Time18) {
                    Time.now();
                    MailSend.send(Memory.adminMail, "2 ժամը մեկվա հաշվարկ - " + Time.nowTime + "\n" + dataHour);
                    Memory.Time18 = false;
                }
                break;
            case "12":
                if (Memory.Time12) {
                    Time.now();
                    MailSend.send(Memory.adminMail, "2 ժամը մեկվա հաշվարկ - " + Time.nowTime + "\n" + dataHour);
                    Memory.Time12 = false;
                }
                break;


            default:
                if (exceptionCheck) {
                    Time.now();
                    MailSend.send(Memory.adminMail, "Աշխատողի թույլ տված սխալի հաշվարկ - " + Time.nowTime + "\n" + dataHour);
                }
                break;

        }
    }
}

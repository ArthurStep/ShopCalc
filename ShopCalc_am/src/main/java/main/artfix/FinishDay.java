package main.artfix;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class FinishDay {
    public static void finish() throws Exception {
        try {
            System.out.print("Գրեք աշխատողների քանակը: ");
            Scanner scanStaffIn = new Scanner(System.in);
            int scanStaff = scanStaffIn.nextInt();
            int countStaffWhile = 0;
            boolean staffWhile = true;
            int staffMoneyFinal = 0;
            String staffMoneyFinalString;

            while (staffWhile) {
                System.out.print("Գրեք աշխատող  N" + countStaffWhile + "-ի աշխատած գումարը: ");
                Scanner scanStaffMoneyIn = new Scanner(System.in);
                int scanStaffMoney = scanStaffMoneyIn.nextInt();
                countStaffWhile++;

                staffMoneyFinal = staffMoneyFinal + scanStaffMoney;

                staffMoneyFinalString = Integer.toString(staffMoneyFinal);

                if (countStaffWhile == scanStaff) {
                    System.out.println("Օրվա փակում.");
                    StringBuilder sb = new StringBuilder();
                    BufferedReader br = new BufferedReader(new FileReader("day.txt"));
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line).append("\n");
                    }
                    br.close();
                    String data = sb.toString();
                    System.out.println(data);
                    Time.now();

                    System.out.println("Օրվա փակում..");

                    Memory.finalBillSt = Integer.toString(Memory.finalBill);

                    MailSend.send(Memory.adminMail, Time.nowTime + "\n" + data + "\n" + "\n" + "\n" + "\n" +
                            "\n" + " Աշխատողի աշխատավարձ - " + staffMoneyFinalString + "\n" + "\n" + "Վերջնական հաշվարկ - " + Memory.finalBillSt);
                    System.out.println("Օրվա փակում...");
                    FileWriter txtEnd = new FileWriter("day.txt");
                    txtEnd.write("");
                    txtEnd.close();
                    Memory.working = false;
                    System.out.println("Օրը փակված է");
                    Time.ymd();
                    Time.now();
                    FileWriter txt = new FileWriter(Time.ymdTime + ".txt", true);
                    txt.write(Time.nowTime + "\n" + data + "\n" + "\n" + "\n" + "\n" +
                            "\n" + " Աշխատողի աշխատավարձ - " + staffMoneyFinalString +
                            "\n" + "\n" + "Վերջնական հաշվարկ - " + Memory.finalBillSt + "\n" + "\n" + "\n" + "\n" + "\n" +
                            "\n" + "\n" + "\n" + "\n" + "\n" + "\n");
                    txt.close();


                    staffWhile = false;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Գրեք միայն թվեր. Սխալ");
            System.out.println("Համակարգն կաշխատի նորից միացրեք ծրագիրը սակայն հին գործարքները ուղարկվեցին տնօրենին և ջնջվեցին");
            MailSend.send(Memory.adminMail, "Սխալ աշխատողի մութքագրած տառ թվի դաշտում " +
                    "NumberFormatException Exception. Վերջնական հաշվարկում չի լինի այս պահին ձեզ ուղարկված գործարքները");
        } catch (Exception eMax) {
            System.out.println("Սխալ!");
            System.out.println("Համակարգն կաշխատի նորից միացրեք ծրագիրը սակայն հին գործարքները ուղարկվեցին տնօրենին և ջնջվեցին");
            MailSend.send(Memory.adminMail, "Սխալ աշխատողի մութքագրած տառ թվի դաշտում " +
                    "NumberFormatException Exception. Վերջնական հաշվարկում չի լինի այս պահին ձեզ ուղարկված գործարքները");
        }
    }
}

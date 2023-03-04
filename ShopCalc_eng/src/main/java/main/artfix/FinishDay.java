package main.artfix;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class FinishDay {
    public static void finish() throws Exception {
        try {
            System.out.print("Workers count: ");
            Scanner scanStaffIn = new Scanner(System.in);
            int scanStaff = scanStaffIn.nextInt();
            int countStaffWhile = 0;
            boolean staffWhile = true;
            int staffMoneyFinal = 0;
            String staffMoneyFinalString;

            while (staffWhile) {
                System.out.print("Worker N" + countStaffWhile + "worker received money: ");
                Scanner scanStaffMoneyIn = new Scanner(System.in);
                int scanStaffMoney = scanStaffMoneyIn.nextInt();
                countStaffWhile++;

                staffMoneyFinal = staffMoneyFinal + scanStaffMoney;

                staffMoneyFinalString = Integer.toString(staffMoneyFinal);

                if (countStaffWhile == scanStaff) {
                    System.out.println("Day closing.");
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

                    System.out.println("Day closing..");

                    Memory.finalBillSt = Integer.toString(Memory.finalBill);

                    MailSend.send(Memory.adminMail, Time.nowTime + "\n" + data + "\n" + "\n" + "\n" + "\n" +
                            "\n" + " Worker received money - " + staffMoneyFinalString + "\n" + "\n" + "Final count - " + Memory.finalBillSt);
                    System.out.println("Day closing...");
                    FileWriter txtEnd = new FileWriter("day.txt");
                    txtEnd.write("");
                    txtEnd.close();
                    Memory.working = false;
                    System.out.println("Day closed");
                    Time.ymd();
                    Time.now();
                    FileWriter txt = new FileWriter(Time.ymdTime + ".txt", true);
                    txt.write(Time.nowTime + "\n" + data + "\n" + "\n" + "\n" + "\n" +
                            "\n" + " Worker received money - " + staffMoneyFinalString +
                            "\n" + "\n" + "final count - " + Memory.finalBillSt + "\n" + "\n" + "\n" + "\n" + "\n" +
                            "\n" + "\n" + "\n" + "\n" + "\n" + "\n");
                    txt.close();


                    staffWhile = false;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Enter only numbers. Error");
            System.out.println("All old information are deleted and sent to admin mail address");
            MailSend.send(Memory.adminMail, "Worker Err " +
                    "NumberFormatException Exception. In final count you can't see now transactions");
        } catch (Exception eMax) {
            System.out.println("Error!");
            System.out.println("All old information are deleted and sent to admin mail address");
            MailSend.send(Memory.adminMail, "Worker Err " +
                    "NumberFormatException Exception. In final count you can't see now transactions");
        }
    }
}

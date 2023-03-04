package main.artfix;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;


public class Application {
    public static void start() throws Exception {
        FileWriter txtClearStart = new FileWriter("day.txt");
        txtClearStart.write("");
        txtClearStart.close();

        System.out.print("FOR START ENTER LOGIN PASSWORD: ");
        Scanner scanPassIn = new Scanner(System.in);
        String scanPass = scanPassIn.nextLine();
        if (scanPass.equals("1234")) {
            System.out.println("Starting.");
            MailSend.send(Memory.adminMail, "WARNING WORKER LOGINED SYSTEM APPROVED");
            System.out.println("Starting..");
            System.out.println("Starting...");
            try {
                while (Memory.working) {
                    System.out.println(" ");
                    System.out.println("For pay with card type 'card'.");
                    System.out.println("For out cassa money type 'pay'.");
                    System.out.println("For see day statistic type 'info'.");
                    System.out.println("For close day type 'end'.");
                    System.out.println(" ");

                    HourCalc.workHour(false);

                    System.out.print("Enter product name or command: ");
                    Scanner scanForShopIn = new Scanner(System.in);
                    String scanForShop = scanForShopIn.nextLine();

                    switch (scanForShop) {
                        case "end", "End", "finish", "Finish":
                            FinishDay.finish();
                            break;

                        case "pay", "Pay":
                            System.out.print("Enter why: ");
                            Scanner scanCassaMinusIn = new Scanner(System.in);
                            String scanCassaMinus = scanCassaMinusIn.nextLine();

                            System.out.print("Enter how many: ");
                            Scanner scanHowManyMinusIn = new Scanner(System.in);
                            String scanHowManyMinus = scanHowManyMinusIn.nextLine();

                            FileWriter txtMinus = new FileWriter("day.txt", true);
                            txtMinus.write("\n" + "\n" + "|||" + "Cassa out - Why - " + scanCassaMinus + " how many - " + scanHowManyMinus + " ||| " + "\n");
                            System.out.println("Done!");
                            txtMinus.close();

                            int scanHowManyMinusInt = Integer.parseInt(scanHowManyMinus);
                            Memory.finalBill = Memory.finalBill - scanHowManyMinusInt;
                            break;


                        case "info", "Data", "data", "Info":
                            StringBuilder sb2 = new StringBuilder();
                            BufferedReader br2 = new BufferedReader(new FileReader("day.txt"));
                            String line2;
                            while ((line2 = br2.readLine()) != null) {
                                sb2.append(line2).append("\n");
                            }
                            br2.close();
                            String dataSout = sb2.toString();
                            System.out.println(dataSout);

                            Memory.finalBillSt = Integer.toString(Memory.finalBill);

                            System.out.println("Final count - " + Memory.finalBillSt);

                            break;

                        case "card", "Card":
                            System.out.print("Enter product name: ");
                            Scanner scanForShopTerminalIn = new Scanner(System.in);
                            String scanForShopTerminal = scanForShopTerminalIn.nextLine();

                            System.out.print("Enter product price: ");
                            Scanner scanPriceInT = new Scanner(System.in);
                            String scanPriceT = scanPriceInT.nextLine();

                            FileWriter txtT = new FileWriter("day.txt", true);
                            txtT.write("\n" + " ||| Terminal, card |||" + " Product name - " + scanForShopTerminal + " Product price - " + scanPriceT + " ||| ");
                            System.out.println("Done!");
                            txtT.close();

                            int scanPriceTInt = Integer.parseInt(scanPriceT);
                            Memory.finalBill = Memory.finalBill + scanPriceTInt;
                            break;

                        case "sendOld", "sendold":
                            Time.ymd();
                            Time.now();
                            StringBuilder sbOld = new StringBuilder();
                            BufferedReader brOld = new BufferedReader(new FileReader(Time.ymdTime + ".txt"));
                            String lineOld;
                            while ((lineOld = brOld.readLine()) != null) {
                                sbOld.append(lineOld).append("\n");
                            }
                            brOld.close();
                            String dataOld = sbOld.toString();
                            MailSend.send(Memory.adminMail, Time.nowTime + " \n" + "\n" + "Requested transactions with sendOld command" + "\n" + "\n" + "\n" + dataOld);
                            System.out.println("Done!");
                            break;


                        default:
                            System.out.print("Enter price: ");
                            Scanner scanPriceIn = new Scanner(System.in);
                            String scanPrice = scanPriceIn.nextLine();

                            FileWriter txt = new FileWriter("day.txt", true);
                            txt.write("\n" + " ||| " + "Product name - " + scanForShop + "    " + "Product price - " + scanPrice + " ||| ");
                            System.out.println("Done!");
                            txt.close();

                            int scanPriceInt = Integer.parseInt(scanPrice);
                            Memory.finalBill = Memory.finalBill + scanPriceInt;
                            break;

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
        } else {
            MailSend.send(Memory.adminMail, "WARNING WORKER WRONG LOGIN CREDENTIALS");
            System.out.println("Wrong password");
        }

    }
}

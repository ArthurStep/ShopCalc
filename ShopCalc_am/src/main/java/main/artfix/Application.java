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

        System.out.print("Սկսելու համար մուտքագրեք ծածկագիրը: ");
        Scanner scanPassIn = new Scanner(System.in);
        String scanPass = scanPassIn.nextLine();
        if (scanPass.equals("1234")) {
            System.out.println("Սկսում.");
            MailSend.send(Memory.adminMail, "ՈՒՇԱԴՐՈՒԹՅՈՒՆ ԱՇԽԱՏՈՂԻ ՄՈՒՏՔԸ ՀԱՄԱԿԱՐԳ ՀԱՋՈՂՎԵԼ Է");
            System.out.println("Սկսում..");
            System.out.println("Սկսում...");
            try {
                while (Memory.working) {
                    System.out.println(" ");
                    System.out.println("Քարտով վճարելու համար գրեք 'card'.");
                    System.out.println("Դրամարկղից գումար հանելու համար գրեք 'pay'.");
                    System.out.println("Օրվա առևտուրը տեսնելու համար գրեք 'info'.");
                    System.out.println("Օրվա հաշվարկը հաշվելու և օրը փակելու համար գրեք 'end'.");
                    System.out.println(" ");

                    HourCalc.workHour(false);

                    System.out.print("Գրեք Ապրանքի անունը կամ հրամանը: ");
                    Scanner scanForShopIn = new Scanner(System.in);
                    String scanForShop = scanForShopIn.nextLine();

                    switch (scanForShop) {
                        case "end", "End", "finish", "Finish":
                            FinishDay.finish();
                            break;

                        case "pay", "Pay":
                            System.out.print("Գրեք պատճառը: ");
                            Scanner scanCassaMinusIn = new Scanner(System.in);
                            String scanCassaMinus = scanCassaMinusIn.nextLine();

                            System.out.print("Գրեք գինը: ");
                            Scanner scanHowManyMinusIn = new Scanner(System.in);
                            String scanHowManyMinus = scanHowManyMinusIn.nextLine();

                            FileWriter txtMinus = new FileWriter("day.txt", true);
                            txtMinus.write("\n" + "\n" + "|||" + "Ելք Դրամարկղից - Ելքի պատճառ - " + scanCassaMinus + " Ինչքան - " + scanHowManyMinus + " ||| " + "\n");
                            System.out.println("Հաջողուտյամբ!");
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

                            System.out.println("Վերջնական հաշվարկ - " + Memory.finalBillSt);

                            break;

                        case "card", "Card":
                            System.out.print("Գրեք ապրանքի անունը: ");
                            Scanner scanForShopTerminalIn = new Scanner(System.in);
                            String scanForShopTerminal = scanForShopTerminalIn.nextLine();

                            System.out.print("Գրեք ապրանքի գինը: ");
                            Scanner scanPriceInT = new Scanner(System.in);
                            String scanPriceT = scanPriceInT.nextLine();

                            FileWriter txtT = new FileWriter("day.txt", true);
                            txtT.write("\n" + " ||| Տերմինալ, Քարտ |||" + " Ապրանքի անուն - " + scanForShopTerminal + " Ապրանքի գին - " + scanPriceT + " ||| ");
                            System.out.println("Հաջողուտյամբ!");
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
                            MailSend.send(Memory.adminMail, Time.nowTime + " \n" + "\n" + "Հարցում կատարծ քաղվածք sendOld հրամանով" + "\n" + "\n" + "\n" + dataOld);
                            System.out.println("Հաջողությամբ!");
                            break;


                        default:
                            System.out.print("Գրեք գինը: ");
                            Scanner scanPriceIn = new Scanner(System.in);
                            String scanPrice = scanPriceIn.nextLine();

                            FileWriter txt = new FileWriter("day.txt", true);
                            txt.write("\n" + " ||| " + "Ապրանքի անուն - " + scanForShop + "    " + "Ապրանքի գին - " + scanPrice + " ||| ");
                            System.out.println("Հաջողությամբ!");
                            txt.close();

                            int scanPriceInt = Integer.parseInt(scanPrice);
                            Memory.finalBill = Memory.finalBill + scanPriceInt;
                            break;

                    }


                }
            } catch (NumberFormatException e) {
                System.out.println("Գրեք միայն թվեր. Սխալ");
                System.out.println("Համակարգն կաշխատի նորից միացրեք ծրագիրը սակայն հին գործարքները ուղարկվեցին տնօրենին և ջնջվեցին");
                MailSend.send(Memory.adminMail, "Սխալ աշխատողի մութքագրած տառ թվի դաշտում " +
                        "NumberFormatException Exception. Վերջնական հաշվարկում չի լինի այս պահին ձեզ ուղարկված գործարքները");
                HourCalc.workHour(true);
            } catch (Exception eMax) {
                System.out.println("Սխալ!");
                System.out.println("Համակարգն կաշխատի նորից միացրեք ծրագիրը սակայն հին գործարքները ուղարկվեցին տնօրենին և ջնջվեցին");
                MailSend.send(Memory.adminMail, "Սխալ աշխատողի մութքագրած տառ թվի դաշտում " +
                        "NumberFormatException Exception. Վերջնական հաշվարկում չի լինի այս պահին ձեզ ուղարկված գործարքները");
            }
        } else {
            MailSend.send(Memory.adminMail, "ՈՒՇԱԴՐՈՒԹՅՈՒՆ ԱՇԽԱՏՈՂԻ ՍԽԱԼ ՄՈՒՏՔԻ ՏՎՅԱԼՆԵՐ");
            System.out.println("Սխալ ծածկագիր");
        }

    }
}

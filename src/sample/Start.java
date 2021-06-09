package sample;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Start {

    public static void main(String[] args) {
        Start program = new Start();
        program.start();
    }

    //private boolean isTargetSetup = false;
    private boolean isXpSetup = false;

    // All the magic happens here
    // After initialisation, will loop all the choices and wait for the user's answer in order to proceed with the calculation in the User class
    private void start() {
        info();
        User user = new User();
        user.setCurrentLvl(initialInputCurrentLvl());
        user.setTargetLvl(initialInputTargetLvl(user.getCurrentLvl()));
        user.setCurrentXp(initialInputCurrentXp(user, user.getCurrentLvl()));
        while (true){
            int choice = choice();
            if(choice == 0)
                updateDetails(user);
            else if (choice == 1){
                print("Badges Required to achieve LVL "+user.getTargetLvl()+": "+user.calculateBadges()+"\n");
            }
            else if (choice == 2){
                print("Badges Required to achieve LVL "+user.getTargetLvl()+" based on your XP: "+user.calculateBadgesAccurate()+"\n");
            }
            else if (choice == 3){
                print("Experience required to achieve LVL "+user.getTargetLvl()+": "+user.calculateXPtoTarget()+"\n");
            }
            else if (choice == 4){
                print("Experience required to achieve LVL "+user.getTargetLvl()+" based on your XP: "+user.calculateXPtoTargetAccurate()+"\n");
            }
            else if (choice == 5){
                print("I recommend buying badges on festival sales (winter/summer/halloween/etc.) for better prices and better experience with the program");
                int cards;
                while (true){
                    cards = inputInt("How many cards does your badge require to be crafted? (minim 4)");
                    if (cards >= 4)
                        break;
                    else
                        print("A badge require a minimum of 4 cards. Please try again");
                }
                double price = user.calculateBadgePrice(cards);
                print("The price for 1 badge made out of "+cards+" cards is: £/$/€"+round(price,2));
                print("To achieve LVL "+user.getTargetLvl()+" you will have to pay £/$/€"+round((user.calculateBadges()*price),2)+" for "+(user.calculateBadges()*cards)+" cards ("+((user.calculateBadges()*cards)/cards)+" of each)\n");
            }
            else if (choice == 6){
                print("I recommend buying badges on festival sales (winter/summer/halloween/etc.) for better prices and better experience with the program");
                int cards;
                while (true){
                    cards = inputInt("How many cards does your badge require to be crafted? (minim 4)");
                    if (cards >= 4)
                        break;
                    else
                        print("A badge require a minimum of 4 cards. Please try again");
                }
                double price = user.calculateBadgePrice(cards);
                print("The price for 1 badge made out of "+cards+" cards is: £/$/€"+round(price,2));
                print("To achieve LVL "+user.getTargetLvl()+" you will have to pay £/$/€"+round((user.calculateBadgesAccurate()*price),2)+" for "+(user.calculateBadgesAccurate()*cards)+" cards ("+((user.calculateBadgesAccurate()*cards)/cards)+" of each)\n");
            }
            else {
                print("Something went wrong. The program will stop.");
                break;
            }
        }
        System.exit(0);
    }

    // Will update any details inputted by the user when the program start
    // If the steam level is updated, the target level and current Xp will be reset to prevent any bugs
    private void updateDetails(User user){
        while (true) {
            print("What do you want to update?");
            print("[1] Update Current LVL [Your LVL: " + user.getCurrentLvl() + "]");
            print("[2] Update Target LVL [Your Target: " + user.getTargetLvl() + "]");
            print("[3] Update Current XP [Your XP: " + user.getCurrentXp() + "]");
            print("\n[0] Nothing");
            int ans;
            while (true) {
                ans = inputInt("Chose one of the above options:");
                if (ans == 1) {
                    int currentLvl;
                    while (true) {
                        currentLvl = inputInt("What is your current Steam Level?");
                        if (currentLvl >= 0)
                            break;
                        else
                            print("Your Level cannot be lower than 0. Try Again.");
                    }
                    user.setCurrentLvl(currentLvl);
                    print("Because you changed your level, the target LVL and current XP was reset.\n");
                    user.setCurrentXp(0);
                    user.setTargetLvl(0);
                    break;
                } else if (ans == 2) {
                    int targetLvl;
                    int currentLvl = user.getCurrentLvl();
                    while (true) {
                        targetLvl = inputInt("What level do you want to achieve? ");
                        if (targetLvl > currentLvl) {
                            //isTargetSetup = true;
                            break;
                        }
                        else
                            print("Your target level can't be lower than your level (or equal)");
                    }
                    user.setTargetLvl(targetLvl);
                    break;
                } else if (ans == 3) {
                    int xp;
                    int lvl = user.getCurrentLvl();
                    int minXp = user.calculateMinXp(lvl);
                    int maxXp = user.calculateMaxXp(lvl);
                    print("What is your current XP? Input 0 if you don't want to check your XP");
                    while (true) {
                        xp = inputInt("Your XP should be between " + minXp + " and " + maxXp);
                        if (xp >= 0)
                            if (xp != 0) {
                                if (xp >= minXp && xp <= maxXp) {
                                    isXpSetup = true;
                                    break;
                                } else
                                    print("That's not a correct value. If you don't want to add your XP just add 0. Try again");
                            } else
                                break;
                        else
                            print("Your XP cannot be lower than 0. Try Again.");
                    }
                    user.setCurrentXp(xp);
                    break;
                } else if (ans == 0)
                    return;
                else
                    print("Option [" + ans + "] is not valid.");
            }
        }
    }

    // Will get all initial details from the user required to use the User class
    private int initialInputCurrentLvl() {
        int currentLvl;
        while (true) {
            currentLvl = inputInt("What is your current Steam Level?");
            if (currentLvl >= 0)
                break;
            else
                print("Your Level cannot be lower than 0. Try Again.");
        }
        return currentLvl;
    }
    private int initialInputTargetLvl(int currentLvl) {
        int targetLvl;
        while(true) {
            targetLvl = inputInt("What level do you want to achieve? ");
            if (targetLvl > currentLvl) {
                //isTargetSetup = true;
                break;
            }
            else
                print("Your target level can't be lower than your level (or equal)");
        }
        return targetLvl;
    }
    private int initialInputCurrentXp(User user, int lvl){
        int xp;
        int minXp = user.calculateMinXp(lvl);
        int maxXp = user.calculateMaxXp(lvl);
        print("What is your current XP? Input 0 if you don't want to check your XP");
        while(true) {
            xp = inputInt("Your XP should be between "+minXp+" and "+maxXp);
            if(xp >= 0)
                if (xp != 0) {
                    if(xp >= minXp && xp <= maxXp){
                        isXpSetup = true;
                        break;
                    }
                    else
                        print("That's not a correct value. If you don't want to add your XP just add 0. Try again");
                }
                else
                    break;
            else
                print("Your XP cannot be lower than 0. Try Again.");
        }
        return xp;
    }

    // Start information and a method which with all the options available to use
    private void info(){
    print("This is a short program create to help me remember how to program after the summer holidays.\nThe program is a short Steam Calculator which can give you information like how many badges you need to achieve a level, how much experience you need or how much a badge will cost.\nNothing to complicated.\n\n");
    }
    private int choice(){
        print("[1] Check how many badges you need to achieve your target level");
        if (isXpSetup)
            print("[2] Check how many badges you need to achieve your target level [Based on your Current XP]");
        else
            print("[2] Not available [Current XP Required. Choose option [0] to update]");
        print("[3] Check how much XP you need to achieve your target level");
        if (isXpSetup)
            print("[4] Check how much XP you need to achieve your target level [Based on your Current XP]");
        else
            print("[4] Not available [Current XP Required. Choose option [0] to update]");
        print("[5] Check how much will cost you to buy badges to achieve your target level");
        if (isXpSetup)
            print("[6] Check how much will cost you to buy badges to achieve your target level [Based on your Current XP]");
        else
            print("[6] Not available [Current XP Required. Choose option [0] to update]");
        //print("[7] All of the above [Adaptive]");
        print("\n[0] Update Details");
        print("[1337] Close the Program\n");
        int ans;
        while (true) {
            ans = inputInt("Choose one of the above options:");
            if (ans == 1337)
                System.exit(1337);
            else if (((ans==2 || ans==4) && !isXpSetup) || (ans<0 || ans>6))
                print("Option ["+ans+"] is not available. Please select something else");
            else
                break;
        }
        return ans;
    }

    // Will round a double to how many decimal places inputted
    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    // Methods for printing and inputting a number or string
    private void print(String p) {
        System.out.println(p); }
    private int inputInt(String message) {
        int i;
        Scanner scanner = new Scanner(System.in);
        print(message);
        while(true) {
            try {
                i = Integer.parseInt(scanner.nextLine());
                break;
            } catch (Exception e) {
                print("Please input a number.");
            }
        }
        return i; }
    double inputDouble(String message) {
        double d;
        Scanner scanner = new Scanner(System.in);
        print(message);
        while(true) {
            try {
                d = Double.parseDouble(scanner.nextLine());
                break;
            } catch (Exception e) {
                print("Please input a number. EX: 2.34 or 2.00");
            }
        }
        return d; }
}

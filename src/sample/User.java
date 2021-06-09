package sample;

class User extends Start{

    // Instance Variables
    private int currentLvl;
    private int currentXp;
    private int targetLvl;

    // Constructors
    User(){}
    User(int current, int target, int xp){
        currentLvl = current;
        targetLvl = target;
        currentXp = xp;
    }

    // All the calculations required when selecting an option is done here
    // CalculateMin/MaxXp will check how much XP you can have in each level
    // The "accurate" used at the end of the 2 methods represents the use of the current XP the user has
    // Non "accurate" methods will use the minimum XP to give an overall look at how many badges/xp you need
    int calculateMinXp(int lvl){
        int xpNeed = 100;
        int totalXp = 0;
        if(lvl != 0){
            for(int i=0; i<lvl; i++){
                totalXp += xpNeed;
                if(((i+1)%10 == 0) && (i != 0)){
                    xpNeed += 100;
                }
            }
            return totalXp;
        }
        else
            return 0;
    }
    int calculateMaxXp(int lvl){
        int xpNeed = 100;
        int totalXp = 0;
        if(lvl != 0){
            for(int i=0; i<lvl; i++){
                totalXp += xpNeed;
                if(((i+1)%10 == 0) && (i != 0)){
                    xpNeed += 100;
                }
            }
            return (totalXp+xpNeed);
        }
        else
            return 100;
    }
    int calculateBadges(){
        int targetXp = calculateMinXp(targetLvl);
        int curXp = calculateMinXp(currentLvl);
        return (targetXp - curXp)/100;
    }
    int calculateBadgesAccurate(){
        int targetXp = calculateMinXp(targetLvl);
        return (int)Math.ceil(((targetXp - currentXp)/100)+0.5);
    }
    int calculateXPtoTarget(){
        int targetXp = calculateMinXp(targetLvl);
        int curXp = calculateMinXp(currentLvl);
        return (targetXp - curXp);
    }
    int calculateXPtoTargetAccurate(){
        int targetXp = calculateMinXp(targetLvl);
        return (targetXp - currentXp);
    }
    double calculateBadgePrice(int cards){
        double cardPrices = 0.0;
        for(int i=1; i<=cards; i++){
            cardPrices+=inputDouble("What is the price of the card number "+i+"? [Format 2.34 or 2.00]");
        }
        return cardPrices;
    }

    // Setter Methods
    void setCurrentLvl(int currentLvl) {
        this.currentLvl = currentLvl;
    }
    void setTargetLvl(int targetLvl) {
        this.targetLvl = targetLvl;
    }
    void setCurrentXp(int currentXp) {
        this.currentXp = currentXp;
    }

    // Getter Methods
    int getCurrentLvl() {
        return currentLvl;
    }
    int getCurrentXp() {
        return currentXp;
    }
    int getTargetLvl() {
        return targetLvl;
    }

}
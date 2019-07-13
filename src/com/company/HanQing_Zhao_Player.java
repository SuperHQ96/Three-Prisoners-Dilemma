package com.company;

public class HanQing_Zhao_Player extends ThreePrisonersDilemma.Player {
    int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
        //Start off by cooperating
        if(n == 0) return 0;
        //Count the number of times which opponents 1 and 2 cooperates and defects
        int opponent1Coop = 0;
        int opponent1Defect = 0;
        int opponent2Coop = 0;
        int opponent2Defect = 0;
        //Opponent 1
        for (int i=0; i<n; i++) {
            if (oppHistory1[i] == 0)
                opponent1Coop = opponent1Coop + 1;
            else
                opponent1Defect = opponent1Defect + 1;
        }
        //Opponent 2
        for (int i=0; i<n; i++) {
            if (oppHistory2[i] == 0)
                opponent2Coop = opponent2Coop + 1;
            else
                opponent2Defect = opponent2Defect + 1;
        }
        //Don't hold grudges - reciprocate cooperation immediately
        if(oppHistory1[n-1] == 0 && oppHistory2[n-1] == 0) {
            return 0;
        }
        //Retaliate appropriately by checking for certain conditions
        else if (oppHistory1[n-1] == 1 && oppHistory2[n-1] == 0){
            //If one opponent defects while one opponent cooperates initially, be nice and cooperate as well
            if(n == 1) {
                return 0;
            } else {
                //Opponent 1 may defect out of retaliation. Hence, we check if opponent 1 is cooperating two rounds before.
                if(oppHistory1[n-2] == 0) {
                    //Cooperate if opponent 1 seems to be defecting only out of retaliation (unless random action performed by opponent 1)
                    return 0;
                } else {
                    //If opponent 1 does not seem to be defecting only out of retaliation, check if opponent 1 cooperated more than defecting in the past
                    //If so, cooperate, else retaliate by defecting
                    if(opponent1Coop > opponent1Defect)return 0;
                    else return 1;
                }
            }
        }
        //Retaliate appropriately by checking for certain conditions
        else if (oppHistory1[n-1] == 0 && oppHistory2[n-1] == 1) {
            //If opponent 1 cooperates while opponent 2 defects initially, be nice and cooperate as well
            if(n == 1) {
                return 0;
            } else {
                //Opponent 2 may defect out of retaliation. Hence, we check if opponent 2 is cooperating two rounds before.
                if(oppHistory2[n-2] == 0) {
                    //Cooperate if opponent 2 seems to be defecting only out of retaliation (unless random action performed by opponent 2)
                    return 0;
                } else {
                    //If opponent 2 does not seem to be defecting only out of retaliation, check if opponent 2 cooperated more than defecting in the past
                    //If so, cooperate, else retaliate by defecting
                    if(opponent2Coop > opponent2Defect) return 0;
                    else return 1;
                }
            }
        } else {
            //If both opponents defects in the first round, retaliate by defecting as well
            if(n == 1) {
                return 1;
            }
            //Retaliate appropriately by checking for certain conditions
            else {
                //If both opponents cooperated two rounds before, be nice and cooperate
                if(oppHistory1[n-2] == 0 && oppHistory2[n-2] == 0) return 0;
                else if(oppHistory1[n-2] == 1 && oppHistory2[n-2] == 0) {
                    //If opponent 1 defected while opponent 2 cooperated two rounds before, check if opponent 1 cooperated more than it defected in the past
                    //If so, cooperate, else retaliate by defecting
                    if (opponent1Coop > opponent1Defect) return 0;
                    else return 1;
                } else if(oppHistory1[n-2] == 0 && oppHistory2[n-2] == 1) {
                    //If opponent 2 cooperated while opponent 1 defected two rounds before, check if opponent 2 cooperated more than it defected in the past
                    //If so, cooperate, else retaliate by defecting
                    if(opponent2Coop > opponent2Defect) return 0;
                    else return 1;
                } else {
                    //If both opponents defected for the past two rounds, retaliate by defecting as well
                    return 1;
                }
            }
        }
    }
}

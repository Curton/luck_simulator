package com.liukedun.luck.simulator;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        // number of samples
        int people = 10000;
        Random random = new Random();
        List<People> peopleList = new ArrayList<>(lowerThan2PowerOf(people));
        for (int i = 0; i < people; i++) {
            int ability = (int) ((random.nextGaussian() * 0.3 + 0.5) * 100.0);
            if (ability < 0) {
                ability = 30;
            }
            if (ability > 200) {
                ability = 200;
            }
            peopleList.add(new People(ability));
        }

        // number of chance
        int chance = 10;
        for (int i = 0; i < chance; i++) {
            peopleList.forEach(o -> {
                int luck = (int) ((new Random().nextGaussian() * 0.3 + 0.5) * 100.0);
                if (luck < 0) {
                    luck = 0;
                }
                if (luck > 100) {
                    luck = 100;
                }
                o.setTotalAchievement(o.getTotalAchievement() + o.getAbility() * luck);
                o.setMaxAchievement(Math.max(o.getMaxAchievement(), (o.getAbility() * luck)));
            });
        }

        peopleList.sort(Comparator.comparingInt(People::getTotalAchievement));
        Collections.reverse(peopleList);

        for (int i = 0; i < 20; i++) {
            System.out.println("TotalAchievement : " + peopleList.get(i).getTotalAchievement());
            System.out.println("Ability : " + peopleList.get(i).getAbility());
            System.out.println("---------------------------------------");
        }

    }

    private static int lowerThan2PowerOf(int value) {
        for (int i = 0; i < 30; i++) {
            if (value < (2 << i)) {
                return i;
            }
        }
        return 30;
    }

    private static class People {

        int ability;

        int totalAchievement = 0;

        int maxAchievement = 0;

        public People(int ability) {
            this.ability = ability;
        }

        public int getAbility() {
            return ability;
        }

        public void setAbility(int ability) {
            this.ability = ability;
        }

        public int getTotalAchievement() {
            return totalAchievement;
        }

        public void setTotalAchievement(int totalAchievement) {
            this.totalAchievement = totalAchievement;
        }

        public int getMaxAchievement() {
            return maxAchievement;
        }

        public void setMaxAchievement(int maxAchievement) {
            this.maxAchievement = maxAchievement;
        }
    }
}

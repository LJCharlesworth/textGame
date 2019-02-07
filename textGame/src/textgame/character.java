package textgame;

public class character {
    private int attack;
    private int defence;
    private int agility;

    public character(int attack, int defence, int agility) {
        this.attack = attack;
        this.defence = defence;
        this.agility = agility;
    }
    
    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }
    
}

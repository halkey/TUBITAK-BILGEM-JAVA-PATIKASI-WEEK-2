import java.util.Scanner;

public class SafeHouse extends NormalLoc{
    Scanner input = new Scanner(System.in);

    public SafeHouse(Player player) {
        super(player);
    }

    @Override
    public boolean onLocation() {
        System.out.println("Welcome To Safe House");
        boolean forestCon = this.getPlayer().getInventory().getFirewoodCon();
        boolean caveCon = this.getPlayer().getInventory().getFoodCon();
        boolean riverCon = this.getPlayer().getInventory().getWaterCon();

        if (forestCon && caveCon && riverCon) {
            return true;
        }

        System.out.println("You are in safe.");

        CharName[] characters = {new Samurai(), new Archer(), new Knight()};
        int defaultPlayerHealth = 0;

        for (CharName character:characters) {
            if (character.getName().equals(this.getPlayer().getName())) {
                defaultPlayerHealth = character.getHealth();
                break;
            }
        }



        Armor[] armorList = new Armor[3];

        armorList[0] = new Armor("Light", 1, 1, 15);    // Pistol
        armorList[1] = new Armor("Medium", 2, 3, 25);     // Sword
        armorList[2] = new Armor("Heavy", 3, 5, 40);     // Rifle

        if (this.getPlayer().getHealth() < defaultPlayerHealth)  {
            this.getPlayer().setHealth(defaultPlayerHealth);

            if (this.getPlayer().getInventory().getBonusArmorCon()) {

                String playerArmorName = this.getPlayer().getArmorName();

                for (Armor armor: armorList)
                {
                    if (armor.getName().equals(playerArmorName)) {
                        int totalHealth = defaultPlayerHealth + Armor.selectArmor(armor.getId()).getShield();
                        this.getPlayer().setHealth(totalHealth);
                        break;
                    }
                }
                this.getPlayer().setArmorName(this.getPlayer().getArmorName());

            } else {
                this.getPlayer().setArmorName("No Shield");
            }

            System.out.println("Your health is fulled!!!");
            System.out.println("Your health is now " + this.getPlayer().getHealth());

        } else {
            System.out.println("Your health has already been fulled!!!");
        }

        System.out.println("You're leaving the safe house, Mr. " + this.getPlayer().getUsername());

        return true;
    }


}

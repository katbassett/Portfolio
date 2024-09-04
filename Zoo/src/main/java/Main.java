/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author katbassett
 */
public class Main {

    public static void main(String[] args) {
        Zoo myZoo = new Zoo("Denver Zoo", "Denver, CO", "10:00 am - 6:00 pm", "303-123-4567");

        Habitat primateHabitat = new Habitat("Mystic Jungle", "Tropical", "Apes", 12, "Medium");
        Habitat catHabitat = new Habitat("Big Cats", "Savannah", "Lions and Tigers", 6, "Large");
        Habitat birdHabitat = new Habitat("Bird Paradise", "Woodlands", "Eagle", 8, "Medium");

        myZoo.addHabitat(primateHabitat);
        myZoo.addHabitat(catHabitat);
        myZoo.addHabitat(birdHabitat);

        myZoo.zooInfo();
        System.out.println();

        primateHabitat.habitatInfo();
        System.out.println();
        catHabitat.habitatInfo();
        System.out.println();
        birdHabitat.habitatInfo();
        System.out.println();

        Tiger tiger = new Tiger("Tiger", "Big Cats", "Carnivore", "Asia");
        Lion lion = new Lion("Lion", "Big Cats", "Carnivore", "Africa");

        myZoo.addAnimal(tiger);
        myZoo.addAnimal(lion);

        tiger.eat();
        tiger.purr();
        System.out.println();
        lion.sleep();
        lion.stalk();
        System.out.println();

        Ape ape = new Ape("Ape", "Mystic Jungle", "Omnivore", "Africa");

        myZoo.addAnimal(ape);

        ape.swing();
        ape.climb();
        System.out.println();

        Eagle eagle = new Eagle("Bald Eagle", "Bird Paradise", "Carnivore", "North America");

        eagle.fly();
        eagle.buildNest();

    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

/**
 *
 * @author katbassett
 */
import java.util.ArrayList;
import java.util.List;

public class Zoo {

    private String name;
    private String location;
    private String hours;
    private String contact;
    private List<Habitat> habitats;
    private List<Animal> animals;

    public Zoo(String name, String location, String hours, String contact) {
        this.name = name;
        this.location = location;
        this.hours = hours;
        this.contact = contact;
        this.habitats = new ArrayList<>();
        this.animals = new ArrayList<>();
    }

    public void addHabitat(Habitat habitat) {
        habitats.add(habitat);
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void zooInfo() {
        System.out.println("Name: " + name);
        System.out.println("Location: " + location);
        System.out.println("Hours: " + hours);
        System.out.println("Contact: " + contact);
    }

}

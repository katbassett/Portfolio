/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author katbassett
 */
public class Habitat {

    String name;
    String climate;
    String inhabitants;
    int capacity;
    String size;

    public Habitat(String name, String climate, String inhabitants, int capacity, String size) {
        this.name = name;
        this.climate = climate;
        this.inhabitants = inhabitants;
        this.capacity = capacity;
        this.size = size;
    }

    public void habitatInfo() {
        System.out.println("Habitat Name: " + name);
        System.out.println("Climate: " + climate);
        System.out.println("Animals: " + inhabitants);
        System.out.println("Capacity: " + capacity);
        System.out.println("Size: " + size);
    }
}

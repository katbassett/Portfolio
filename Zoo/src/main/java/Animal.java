/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author katbassett
 */
public class Animal {

    String species;
    String habitat;
    String diet;
    String nativeRegion;
    boolean isPredator;
    boolean isPrey;

    public Animal(String species, String habitat, String diet, String nativeRegion) {
        this.species = species;
        this.habitat = habitat;
        this.diet = diet;
        this.nativeRegion = nativeRegion;
        this.isPredator = false;
        this.isPrey = false;
    }

    public void eat() {
        System.out.println(species + " is eating.");
    }

    public void sleep() {
        System.out.println(species + " is sleeping.");
    }

}

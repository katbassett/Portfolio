/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author katbassett
 */
public class Eagle extends Animal implements Bird {

    public Eagle(String species, String habitat, String diet, String nativeRegion) {
        super(species, habitat, diet, nativeRegion);
    }

    @Override
    public void fly() {
        System.out.println(species + "is flying.");
    }

    @Override
    public void buildNest() {
        System.out.println(species + "is building a nest.");
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author katbassett
 */
public class Ape extends Animal implements Primate {

    public Ape(String species, String habitat, String diet, String nativeRegion) {
        super(species, habitat, diet, nativeRegion);
    }

    @Override
    public void climb() {
        System.out.println(species + " is climbing.");
    }

    @Override
    public void swing() {
        System.out.println(species + " is swinging.");
    }
}

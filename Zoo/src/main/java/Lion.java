/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author katbassett
 */
public class Lion extends Animal implements Cat {

    public Lion(String species, String habitat, String diet, String nativeRegion) {
        super(species, habitat, diet, nativeRegion);
    }

    @Override
    public void purr() {
        System.out.println(species + " is purring.");
    }

    @Override
    public void stalk() {
        System.out.println(species + " is stalking.");
    }
}

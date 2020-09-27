package com.tbonas.assignment2.model;

/**
 * Represent Lens and its make, max aperture, and focal length
 */
public class Lens {
    private String make;
    private double max_aperture;
    private int focal_length;

    public Lens(String make, double max_aperture, int focal_length) {
        this.make = make;
        this.max_aperture = max_aperture;
        this.focal_length = focal_length;
    }
    // Setters
    public void setMake(String make) {
        this.make = make;
    }

    public void setMax_aperture(double max_aperture) {
        this.max_aperture = max_aperture;
    }

    public void setFocal_length(int focal_length) {
        this.focal_length = focal_length;
    }
    // Getters
    public String getMake() {
        return make;
    }

    public double getMax_aperture() {
        return max_aperture;
    }

    public int getFocal_length() {
        return focal_length;
    }

    @Override
    public String toString() {
        return "Lens{" +
                "make='" + make + '\'' +
                ", max_aperture=" + max_aperture +
                ", focal_length=" + focal_length +
                '}';
    }
}

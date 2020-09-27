package com.tbonas.assignment2.model;

/**
 * Calculate many depth-of-field values like nearest point in focus,
 * farthest point in focus, the hyperfocal distance, and a general depth of
 * field range, given a lens and other relevant input information like chosen
 * aperture or distance
 */

public class DOFCalculator {
    private static final double COC = 0.029;

    // Calculates the hyperfocal distance given a lens and chosen aperture
    // Returns its value in mm as other methods, calc_far and calc_near, do their
    // calculations in mm and need the hyperfocal distance
    // TODO Make calc_hyperfocal return value be in metres and still work with near and far
    public static double calc_hyperfocal (Lens lens, double aperture) throws IllegalArgumentException {
        // All exception handling cases with case-specific messages
        if (lens == null) {
            throw new IllegalArgumentException("DOFCalculator.calc_hyperfocal error: lens = null");
        }
        if (lens.getMake() == null) {
            throw new IllegalArgumentException("DOFCalculator.calc_hyperfocal error: lens make = null");
        }
        if (lens.getMax_aperture() <= 0) {
            throw new IllegalArgumentException("DOFCalculator.calc_hyperfocal error: lens max aperture <= 0");
        }
        if (lens.getFocal_length() <= 0) {
            throw new IllegalArgumentException("DOFCalculator.calc_hyperfocal error: lens focal length  <= 0");
        }
        if (aperture < lens.getMax_aperture()) {
            throw new IllegalArgumentException("DOFCalculator.calc_hyperfocal error: input aperture < lens max aperture");
        }

        double result = ((Math.pow(lens.getFocal_length(), 2) ) / (aperture * COC)) + lens.getFocal_length();

        return result;
    }
    // Calculates the nearest point that will be in focus given the lens, distance, and aperture
    // Returns value in mm
    public static double calc_near(Lens lens, double distance, double aperture) throws IllegalArgumentException {
        // All exception handling cases with case-specific messages
        if (lens == null) {
            throw new IllegalArgumentException("DOFCalculator.calc_near error: lens = null");
        }
        if (lens.getMake() == null) {
            throw new IllegalArgumentException("DOFCalculator.calc_near error: lens make = null");
        }
        if (distance < 0) {
            throw new IllegalArgumentException("DOFCalculator.calc_near error: distance < 0");
        }
        if (lens.getMax_aperture() <= 0) {
            throw new IllegalArgumentException("DOFCalculator.calc_near error: lens max aperture <= 0");
        }
        if (lens.getFocal_length() <= 0) {
            throw new IllegalArgumentException("DOFCalculator.calc_near error: lens focal length  <= 0");
        }
        if (aperture < lens.getMax_aperture()) {
            throw new IllegalArgumentException("DOFCalculator.calc_near error: input aperture < lens max aperture");
        }

        double hyperfocal = calc_hyperfocal(lens, aperture);

        distance = distance * 1000;

        double result = (distance * (hyperfocal - lens.getFocal_length())) /
                (hyperfocal + distance - 2 * lens.getFocal_length());

        return result/1000.0;
    }
    // Calculates the farthest point that will be in focus given the lens, distance, and aperture
    // Returns value in mm
    public static double calc_far(Lens lens, double distance, double aperture) throws IllegalArgumentException {
        // All exception handling cases with case-specific messages
        if (lens == null) {
            throw new IllegalArgumentException("DOFCalculator.calc_far error: lens = null");
        }
        if (lens.getMake() == null) {
            throw new IllegalArgumentException("DOFCalculator.calc_far error: lens make = null");
        }
        if (distance < 0) {
            throw new IllegalArgumentException("DOFCalculator.calc_far error: distance < 0");
        }
        if (lens.getMax_aperture() <= 0) {
            throw new IllegalArgumentException("DOFCalculator.calc_far error: lens max aperture <= 0");
        }
        if (lens.getFocal_length() <= 0) {
            throw new IllegalArgumentException("DOFCalculator.calc_far error: lens focal length  <= 0");
        }
        if (aperture < lens.getMax_aperture()) {
            throw new IllegalArgumentException("DOFCalculator.calc_far error: input aperture < lens max aperture");
        }

        double hyperfocal = calc_hyperfocal(lens, aperture);
        distance = distance * 1000;

        if (distance > hyperfocal) {
            return Double.POSITIVE_INFINITY;
        }

        double result = (distance * (hyperfocal - lens.getFocal_length())) / (hyperfocal - distance);

        return result/1000.0;
    }
    // Calculates the distance that will be in focus given the lens, distance, and aperture
    // by subtracting the farthest in-focus point from the nearest in-focus point
    // Returns value in mm
    public static double dof(Lens lens, double distance, double aperture) {
        // All exception handling cases with case-specific messages
        if (lens == null) {
            throw new IllegalArgumentException("DOFCalculator.calc_far error: lens = null");
        }
        if (lens.getMake() == null) {
            throw new IllegalArgumentException("DOFCalculator.calc_far error: lens make = null");
        }
        if (distance < 0) {
            throw new IllegalArgumentException("DOFCalculator.calc_far error: distance < 0");
        }
        if (lens.getMax_aperture() <= 0) {
            throw new IllegalArgumentException("DOFCalculator.calc_far error: lens max aperture <= 0");
        }
        if (lens.getFocal_length() <= 0) {
            throw new IllegalArgumentException("DOFCalculator.calc_far error: lens focal length  <= 0");
        }
        if (aperture < lens.getMax_aperture()) {
            throw new IllegalArgumentException("DOFCalculator.calc_far error: input aperture < lens max aperture");
        }

        double far = calc_far(lens, distance, aperture);
        if (far == Double.POSITIVE_INFINITY) {
            return Double.POSITIVE_INFINITY;
        }
        double near = calc_near(lens, distance, aperture);

        return far - near;
    }
}

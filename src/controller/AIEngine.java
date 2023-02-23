package controller;

/**
 * Class representing the Artificial Intelligence engine The AI engine is the part of the program that deals with all
 * calculations and measurements using artificial intelligence
 */
public abstract class AIEngine {
    public static boolean endOfTheGame; // cette variable indique la fin de la partie

    /**
     * When components of the part change, this function is called to update all the attributes of the IA part.
     */
    public abstract void update();
}

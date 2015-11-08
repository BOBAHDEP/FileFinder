package ru.filefinder;

import java.util.InputMismatchException;

/**
 * The ConsoleArgsParse class is auxiliary class
 * for console arguments parsing, making them fit
 * some standard order
 *
 * @author  Ryzhikh Vladimir
 */
public class ConsoleArgsParse {
    /**
     * Makes console arguments fit standard order
     * and changes '*' to * in regex expression
     *
     * @param args given arguments of console
     * @return     parsed and sorted arguments
     */
    static public String[] getNormalisedArgs(String[] args) {

        String[] res = new String[3];   // [0] regex [1] -d  [2] -s. If -d or -s is not found -> null

        //to make normal regex expression
        res[0] = args[0].replaceAll("'\\*'", "*");

        for (int i = 1; i < args.length; i++) {
            if (args[i].equals("-d") && res[1] == null) {
                res[1] = "-d";
                continue;
            }
            if (args[i].equals("-s") && res[2] == null) {
                res[2] = "-s";
                continue;
            }
            throw new InputMismatchException();
        }
        return res;
    }
}

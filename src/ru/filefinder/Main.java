package ru.filefinder;

/**
 * Just a class for test.
 */
public class Main {
    public static void main(String[] args) {

        FileFinder fileFinder = new FileFinder(System.getProperty("user.dir"));
        fileFinder.findFiles(ConsoleArgsParse.getNormalisedArgs(args)[0], ConsoleArgsParse.getNormalisedArgs(args)[1], ConsoleArgsParse.getNormalisedArgs(args)[2]);

    }
}

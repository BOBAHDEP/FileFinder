package ru.filefinder;

/**
 * Just a class for test.
 */
public class Main {
    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("You should call this program with following arguments:\n" +
                    "regex expression; (optionally) -s; (optionally) -d.\n" +
                    "-s will print additional information about size, -d about date");
            return;
        }

        FileFinder fileFinder = new FileFinder(System.getProperty("user.dir"));
        fileFinder.findFiles(ConsoleArgsParse.getNormalisedArgs(args)[0], ConsoleArgsParse.getNormalisedArgs(args)[1], ConsoleArgsParse.getNormalisedArgs(args)[2]);

    }
}

package ru.filefinder;

import java.io.File;
import java.rmi.server.ServerRef;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The FileFinder class provides file search in
 * specific directory {@link #fileFolder} using
 * regex as a name identifier in {@link #findFiles(String[] fileNameExpr)}
 *
 * @author  Ryzhikh Vladimir
 */
public class FileFinder {
    /** Pathname string of a directory where search is being conducted*/
    private String fileFolder;

    /**
     * Creates FileFinder with a defined directory of search
     *
     * @param fileFolder directory of search
     */
    public FileFinder(String fileFolder) {
        this.fileFolder = fileFolder;
    }

    /**
     * Writes an information about one file to console in one
     * line. Depending on flags needData ans needSize can provide
     * additional information about last date of modification of
     * file and it's size in bytes respectively.
     *
     * @param file          file of interest
     * @param needData      flag of additional information about last date of modification.
     *                      Can be either "-d" or null
     *                      if "-d" then information is printed. If null, it is not printed.
     * @param needSize      flag of additional information about file size in bytes.
     *                      Can be either "-s" or null
     *                      if "-s" then information is printed. If null, it is not printed.
     */
    private void writeFileInformation(File file, String needData, String needSize) {
        if (needData.equals("-d")) {
            Date lastModified = new Date(file.lastModified());
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            System.out.print(formatter.format(lastModified) + " ");
        }
        if (needSize != null && needSize.equals("-s")) {
            System.out.printf("%20d  ", file.getTotalSpace());
        }
        if (file.isDirectory()) {
            System.out.print(" isDir ");
        } else {
            System.out.print("notDir ");
        }
        System.out.printf(" %30s %-100s%n", file.getName(), file.getParent());
    }

    /**
     * Searches for files in folderPath according to regexp, given as fileNameExp[0]
     * and prints information about them to console
     *
     * @param folderPath    path of folder where fies are being searched
     * @param fileNameExp   regex expression defines file name and date, size
     *                      flags
     */
    private void findFiles(String folderPath, String regex, String needDate, String needSize) {

        File mainFolder = new File(folderPath);

        File[] files = mainFolder.listFiles();

        if (files == null) {
            return;
        }

        for (File f : files) {
            if (fileNameFits(f.getName(), regex)) {
                writeFileInformation(f, needDate, needSize);
            }
            if (f.isDirectory()) {
                findFiles(f.getAbsolutePath(), regex, needDate, needSize);
            }
        }
    }

    /**
     * Searches for files in folderPath according to regexp, given as fileNameExp[0]
     * and prints information about them to console. Starts in {@link #fileFolder}
     * folder and then acts recursively for folders
     *
     * @param fileNameExpr  regex expression defines file name and date, size
     *                      flags
     */
    public void findFiles(String regex, String needDate, String needSize) {
        System.out.println("Date of last change        Size(bytes)    isDirection?      Name                         Folder\n");
        findFiles(fileFolder, regex, needDate, needSize);
    }

    /**
     * Defines whether file name fits regex parameter
     *
     * @param fileName      name of the file
     * @param nameExpr      regex expression filename is compared to
     * @return              true if fileName fits nameExpr
     *                      false if does not fit
     */
    private boolean fileNameFits(String fileName, String nameExpr) {
        return fileName.matches(nameExpr);
    }

}

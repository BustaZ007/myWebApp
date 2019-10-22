package org.mycompany.myname.model;

import java.io.File;
import java.util.ArrayList;

public class FilesList {
    private ArrayList<File> Directories;
    private ArrayList<File> Files;
    private String ParentPath;

    public FilesList(String parentPath, ArrayList dirs, ArrayList files){
        this.ParentPath = parentPath;
        this.Directories = dirs;
        this.Files = files;
    }

    public ArrayList<File> getDirectories() {
        return this.Directories;
    }

    public ArrayList<File> getFiles() {
        return this.Files;
    }

    public String getParent() {
        return this.ParentPath;
    }
}
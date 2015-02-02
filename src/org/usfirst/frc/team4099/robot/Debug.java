package org.usfirst.frc.team4099.robot;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Debug {
    private File debugFile;
    private FileWriter fw;
    private BufferedWriter bw;

    public Debug(String fileName) {
        try {
            debugFile = new File(fileName);
            if (!debugFile.exists())
                debugFile.createNewFile();

            fw = new FileWriter(debugFile.getAbsoluteFile());
            bw = new BufferedWriter(fw);
        } catch (IOException e) {};
    }

    public void println(String s) {
        try {
            bw.write(s + "\n");
            bw.flush();
        } catch (IOException e) {};
    }

    public void close() {
        try {
            bw.close();
        } catch (IOException e) {};
    }
}

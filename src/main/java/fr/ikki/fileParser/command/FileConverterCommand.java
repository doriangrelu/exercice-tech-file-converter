package fr.ikki.fileParser.command;

import picocli.CommandLine;

@CommandLine.Command(name = "convert")
public class FileConverterCommand implements Runnable {

    @Override
    public void run() {
        System.out.println("COUCOU");
    }

}

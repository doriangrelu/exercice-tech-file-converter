package fr.ikki.fileParser.command;

import picocli.CommandLine;

@CommandLine.Command(subcommands = {
        FileConverterCommand.class
})
public class MainCommand {
}

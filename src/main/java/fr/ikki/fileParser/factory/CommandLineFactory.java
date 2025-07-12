package fr.ikki.fileParser.factory;

import fr.ikki.fileParser.command.MainCommand;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import picocli.CommandLine;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommandLineFactory {

    public static CommandLine create() {
        return new CommandLine(new MainCommand());
    }

}

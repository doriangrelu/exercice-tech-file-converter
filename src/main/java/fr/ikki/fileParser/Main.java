package fr.ikki.fileParser;

import fr.ikki.fileParser.factory.CommandLineFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    public static void main(final String[] args) {
        log.info("Starting File parser with {} argument(s)", args.length);
        log.debug("-- Arguments: {}", (Object) args);

        CommandLineFactory.create().execute(args);
    }

}

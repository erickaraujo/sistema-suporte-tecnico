package br.com.sankhya.projeto;

import br.com.sankhya.projeto.model.*;
import br.com.sankhya.projeto.system.SupportSystem;
import org.apache.log4j.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        String issue = "The application crashes inconsistently when users attempt to submit their forms. " +
                "The crashes occur after the form has been filled out and the 'Submit' button is clicked. " +
                "Users reported that they occasionally receive a generic error message stating 'An unexpected error has occurred.'";
        logger.info("Initializing default service chain");
        SupportRequestInfo request = new SupportSystem().processSupportRequest(
                issue
        );
        logger.info(request.toString());
        logger.info("--------------------------------------------");

        logger.info("Initializing personalized service chain");

        SupportRequestInfo request2 = new SupportSystem(
                SupportLevel.chain(
                        new CallCenterLevel(),
                        new SystemAnalystLevel(),
                        new SystemAnalystLevel(),
                        new EngineeringLevel()
                )
        ).processSupportRequest(issue);

        logger.info(request2);
    }
}

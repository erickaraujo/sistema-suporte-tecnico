package br.com.sankhya.projeto.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.log4j.Logger;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
@ToString
public class CallCenterLevel extends SupportLevel{
    private long level;

    private static final Logger logger = Logger.getLogger(CallCenterLevel.class);

    public CallCenterLevel() {
    }

    public CallCenterLevel(Long level) {
        this.level = level;
    }

    @Override
    public boolean resolveIssue(SupportRequestInfo request) {
        level = getActualLevel();
        logger.info(String.format("Level %d call center support handling request issue", level));
        // Retrieve previous analyses
        List<String> previousAnalyses = request.getAnalyses();

        previousAnalyses.forEach(prevAnalyses ->
            logger.info(String.format("Showing previous analyses: %s", prevAnalyses))
        );

        // Simulate problem-solving based on previous analyses
        boolean resolved = anTypeOfResolver(request.getIssue());

        if (resolved) {
            //Could return analysis at resolved step or only if sent to next level?
            logger.info(String.format("Issue resolved at call center Level %d support.", level));
            return true; // Issue resolved
        } else {
            logger.warn(String.format("Issue not resolved by call center Level %d support. Escalating...", level));
            request.addAnalysis("Resume Analysis call center Level " + level +" Support...");
            return hasNextLevel(request); // Issue not resolved
        }
    }

    private boolean anTypeOfResolver(String issue){
        //TODO: implement a business rule to an specific analysis rule
        //Based on support level It could build a json parser to interpret some specific rules on this type level

        //Created a random bit to return true or false for scope;
        return false;
    }
}

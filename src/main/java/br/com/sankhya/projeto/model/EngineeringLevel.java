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
public class EngineeringLevel extends SupportLevel{
    private long level;

    private static final Logger logger = Logger.getLogger(EngineeringLevel.class);

    public EngineeringLevel(){
        this.level = getActualLevel();
    }

    public EngineeringLevel(Long level) {
        this.level = level;
    }

    @Override
    public boolean resolveIssue(SupportRequestInfo request) {
        level = getActualLevel();
        logger.info(String.format("Level %d Engineering support handling request issue", level));
        // Retrieve previous analyses
        List<String> previousAnalyses = request.getAnalyses();

        previousAnalyses.forEach(prevAnalyses ->
            logger.info(String.format("Showing previous analyses: %s", prevAnalyses))
        );

        // Simulate problem-solving based on previous analyses
        boolean resolved = anTypeOfResolver(request.getIssue());

        if (resolved) {
            logger.info(String.format("Issue resolved at Engineering Level %d support.", level));
            return true; // Issue resolved
        } else {
            logger.warn(String.format("Issue not resolved at Engineering Level %d support.", level));
            request.addAnalysis("Resume Analysis Engineering Level " + level +" Support...");
            return hasNextLevel(request); // Issue not resolved
        }
    }

    private boolean anTypeOfResolver(String issue){
        //TODO: implement a business rule to an specific analysis rule
        //Based on support level It could build a json parser to interpret some specific rules on this type level
        return ThreadLocalRandom.current().nextBoolean();
    }
}

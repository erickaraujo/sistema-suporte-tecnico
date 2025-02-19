package br.com.sankhya.projeto.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class SupportLevel {
    private static final Logger logger = LoggerFactory.getLogger(SupportLevel.class);
    private SupportLevel nextLevel;

    private Long actualLevel = 0L;

    public static SupportLevel chain(SupportLevel first, SupportLevel... chain){
        first.actualLevel = 1L;
        SupportLevel head = first;
        for(SupportLevel nextSupportLevel : chain){
            nextSupportLevel.actualLevel = head.actualLevel == 0L ? 2L : head.actualLevel+1L;
            head.nextLevel = nextSupportLevel;
            head = nextSupportLevel;
        }

        return first;
    }

    public Long getActualLevel() {
        return actualLevel;
    }

    public abstract boolean resolveIssue(SupportRequestInfo request);

    public boolean hasNextLevel(SupportRequestInfo request){
        if (nextLevel == null){
            logger.warn("Unknown support level...");
            request.addAnalysis("Resume Analysis not resolved :( ...");
            return false;
        }
        return nextLevel.resolveIssue(request);
    }
}
package br.com.sankhya.projeto.system;

import br.com.sankhya.projeto.model.*;

import java.util.HashMap;

public class SupportSystem {

    private final SupportLevel supportLevel;

    public SupportSystem(){
        //For this scope case and to not overengineering, it'll start always in same sequence
        supportLevel = SupportLevel.chain(
                new CallCenterLevel(),
                new SystemAnalystLevel(),
                new EngineeringLevel()
        );
    }

    public SupportSystem(SupportLevel supportLevel) {
        //Class which could populate chain of support levels ordered from source
        //HashMap<Long, SupportLevel> supportLevels = getSupportLevels("someParameter");
        //TODO: Build a strategy for make an dynamic support levels
        this.supportLevel = supportLevel;
    }

    public SupportRequestInfo processSupportRequest(String issue) {
        SupportRequestInfo request = new SupportRequestInfo(issue);
        supportLevel.resolveIssue(request);
        return request;
    }

}
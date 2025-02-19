package br.com.sankhya.projeto.model;

import java.util.ArrayList;
import java.util.List;

public class SupportRequestInfo {
    private final String issue;
    private final List<String> analyses;

    public SupportRequestInfo(String issue) {
        this.issue = issue;
        this.analyses = new ArrayList<>();
    }

    public String getIssue() {
        return issue;
    }

    public void addAnalysis(String analysis) {
        analyses.add(analysis);
    }

    public List<String> getAnalyses() {
        return new ArrayList<>(analyses);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Support Request Information:\n");
        sb.append("Issue: ").append(issue).append("\n");
        sb.append("Analyses:\n");

        if (analyses.isEmpty()) {
            sb.append("  No analyses available.\n");
        } else {
            for (String analysis : analyses) {
                sb.append("  - ").append(analysis).append("\n");
            }
        }

        return sb.toString();
    }
}

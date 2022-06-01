package org.acme.demo;

import java.io.IOException;
import java.util.Locale;

import io.quarkiverse.githubapp.ConfigFile;
import io.quarkiverse.githubapp.event.Issue;
import org.kohsuke.github.GHEventPayload;

public class AutoRejectIssue {
    void onIssueOpened(@Issue.Opened GHEventPayload.Issue payload,
                        @ConfigFile("demoapp.yaml") RepositoryConfig repoConfig) throws IOException {
        var issue = payload.getIssue();
        for (String keyword : repoConfig.autoreject) {
            if (issue.getTitle().toLowerCase(Locale.ROOT).contains(keyword)
                    || issue.getBody() != null && issue.getBody().toLowerCase(Locale.ROOT).contains(keyword)) {
                payload.getIssue().comment("Sorry, " + keyword + " integration is not supported at the moment.");
                payload.getIssue().close();
            }
        }
    }
}

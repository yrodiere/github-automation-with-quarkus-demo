package org.acme.demo;

import java.io.IOException;
import java.util.Locale;

import io.quarkiverse.githubapp.event.Issue;
import org.kohsuke.github.GHEventPayload;

public class AutoRejectIssue {
    void onIssueOpened(@Issue.Opened GHEventPayload.Issue payload) throws IOException {
        var issue = payload.getIssue();
        if (issue.getTitle().toLowerCase(Locale.ROOT).contains("osgi")
                || issue.getBody() != null && issue.getBody().toLowerCase(Locale.ROOT).contains("osgi")) {
            payload.getIssue().comment("Sorry, OSGi integration is not supported at the moment.");
            payload.getIssue().close();
        }
    }
}

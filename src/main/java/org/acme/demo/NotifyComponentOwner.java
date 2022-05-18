package org.acme.demo;

import java.io.IOException;

import io.quarkiverse.githubapp.event.Issue;
import org.kohsuke.github.GHEventPayload;

public class NotifyComponentOwner {
	void onIssueLabeled(@Issue.Labeled GHEventPayload.Issue payload) throws IOException {
		if (payload.getLabel().getName().equals("component/hibernate")) {
			payload.getIssue().comment("Hey @yrodiere, you might want to have a look at this ^");
		}
	}
}

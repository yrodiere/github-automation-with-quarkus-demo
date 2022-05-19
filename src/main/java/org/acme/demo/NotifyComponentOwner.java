package org.acme.demo;

import java.io.IOException;
import java.util.Map;

import org.kohsuke.github.GHEventPayload;

import io.quarkiverse.githubapp.ConfigFile;
import io.quarkiverse.githubapp.event.Issue;

public class NotifyComponentOwner {
    void onIssueLabeled(@Issue.Labeled GHEventPayload.Issue payload,
                        @ConfigFile("demoapp.yaml") RepositoryConfig repositoryConfig) throws IOException {
        for (Map.Entry<String, RepositoryConfig.ComponentConfig> entry : repositoryConfig.components.entrySet()) {
            var componentName = entry.getKey();
            var componentConfig = entry.getValue();
            if (payload.getLabel().getName().equals("component/" + componentName)) {
                payload.getIssue().comment("Hey @" + componentConfig.owner + ", you might want to have a look at this ^");
            }
        }
    }
}

package org.acme.demo;

import java.io.IOException;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import io.quarkiverse.githubapp.ConfigFile;
import io.quarkiverse.githubapp.event.Issue;
import org.kohsuke.github.GHEventPayload;

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

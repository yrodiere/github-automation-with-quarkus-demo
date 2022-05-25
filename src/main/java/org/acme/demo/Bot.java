package org.acme.demo;

import java.io.IOException;
import java.util.List;

import org.acme.demo.Bot.HelpCommand;
import org.acme.demo.Bot.SayHelloCommand;
import org.kohsuke.github.GHEventPayload;
import org.kohsuke.github.GHEventPayload.IssueComment;

import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Cli;
import com.github.rvesse.airline.annotations.Command;

import io.quarkiverse.githubapp.command.airline.AbstractHelpCommand;

@Cli(name = "@bot", commands = { SayHelloCommand.class, HelpCommand.class }, description = "A polite bot")
public class Bot {

    @Command(name = "say-hello", description = "Says hello")
    static class SayHelloCommand implements Commands {

        @Arguments
        List<String> names;

        @Override
        public void run(IssueComment issueComment) throws IOException {
            issueComment.getIssue().comment("Hello " + String.join(", ", names));
        }
    }

    @Command(name = "help", description = "Prints help")
    static class HelpCommand extends AbstractHelpCommand implements Commands {

    }

    interface Commands {

        public void run(GHEventPayload.IssueComment issueComment) throws IOException;
    }
}

package fr.lecanal.listeroles;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Application extends ListenerAdapter {
    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        String receivedmessage = event.getMessage().getContentRaw();

        //sendMessage(event.getAuthor(), "message lu!");

        if (event.getAuthor().isBot()) {
            return;
        }

        if (receivedmessage.length() < 2) {
            receivedmessage += "admin";
        }

        if (Character.toString(receivedmessage.charAt(0)).equals("!")){

            String askedRole = receivedmessage.substring(1);
            User author = event.getAuthor();
            List<String> roleList = event
                                        .getGuild()
                                        .getRoles()
                                        .stream()
                                        .map(Role::getName)
                                        .collect(Collectors.toList());

            if (roleList.contains(askedRole)) {
                List<Role> validRoleList = event
                                            .getGuild()
                                            .getRolesByName(askedRole, true);

                List<Member> listOfUsers = new ArrayList<>(event
                                        .getGuild()
                                        .getMembersWithRoles(validRoleList));

                String listOfUsersAsStr = listOfUsers
                                        .stream()
                                        .map(Member::getEffectiveName)
                                        .collect(Collectors.joining("\n"));

                String message =
                        listOfUsers.size()
                                + " utilisateur(s) avec le rôle "
                                + askedRole
                                + "\n"
                                + listOfUsersAsStr;

                sendMessage(author, message);
            }
            else {
                sendMessage(author, "Ce rôle n'existe pas!");
            }
        }
    }

    public void sendMessage(User user, String message) {
        user.openPrivateChannel()
        .flatMap(channel -> channel.sendMessage(message))
        .queue();
    }
}

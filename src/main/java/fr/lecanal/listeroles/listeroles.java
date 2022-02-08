package fr.lecanal.listeroles;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import javax.security.auth.login.LoginException;

public class listeroles extends ListenerAdapter {

    public static void main(String[] args) throws LoginException {

        String TOKEN = "ODkyNjk5NjUyMTMzMTAxNTY4.YVQtVg.ear-o_nJB_T5jlo451wr24iUKTY";

        JDA api = JDABuilder.createDefault(TOKEN)
                .setChunkingFilter(ChunkingFilter.ALL)
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .enableIntents(GatewayIntent.GUILD_MEMBERS)
                .addEventListeners(new Application())
                .build();
    }
}
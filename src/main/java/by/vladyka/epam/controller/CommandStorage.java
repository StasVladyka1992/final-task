package by.vladyka.epam.controller;

import by.vladyka.epam.controller.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandStorage {
    private Map<String, Command> commands = new HashMap<>();

    public CommandStorage() {
        commands.put("go_to_default", new GoToDefault());
        commands.put("go_to_authorization", new GoToAutorization());
        commands.put("go_to_registration", new GoToRegistration());
        commands.put("change_language", new ChangeLanguage());
        commands.put("authorization", new UserAuthorization());
        commands.put("registration", new UserRegistration());
        commands.put("user_info_after_registration", new UserInfoAfterRegistration());
        commands.put("authorized_user_main_page", new GoToAuthorizedMain());
        commands.put("sign_out", new UserSignOut());
        commands.put("go_to_client_main", new GoToClientMain());
        commands.put("go_to_remedy", new GoToRemedy());
        commands.put("find_remedy", new FindRemedy());
    }

    public Command getCommand(String command) {
        return commands.get(command);
    }

}

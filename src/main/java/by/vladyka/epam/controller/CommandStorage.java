package by.vladyka.epam.controller;

import by.vladyka.epam.controller.impl.*;

import java.util.HashMap;
import java.util.Map;

import static by.vladyka.epam.controller.util.JSPNavigation.ADD_REMEDY;
import static by.vladyka.epam.controller.util.JSPNavigation.GO_TO_REMEDY_MODIFICATION_RESULT;

public class CommandStorage {

    private static final String GO_TO_DEFAULT = "go_to_default";
    private static final String GO_TO_AUTHORIZATION = "go_to_authorization";
    private static final String GO_TO_REGISTRATION = "go_to_registration";
    private static final String CHANGE_LANGUAGE = "change_language";
    private static final String AUTHORIZATION = "authorization";
    private static final String REGISTRATION = "registration";
    private static final String USER_INFO_AFTER_REGISTRATION = "user_info_after_registration";
    private static final String AUTHORIZED_USER_MAIN_PAGE = "authorized_user_main_page";
    private static final String SIGN_OUT = "sign_out";
    private static final String GO_TO_CLIENT_MAIN = "go_to_client_main";
    private static final String GO_TO_PHARMACIST_MAIN = "go_to_pharmacist_main";
    private static final String GO_TO_REMEDY = "go_to_remedy";
    private static final String FIND_REMEDY = "find_remedy";
    private static final String GO_TO_REMEDY_ADMINISTRATION = "go_to_remedy_administration";
    private static final String ADD_REMEDY = "add_remedy";
    private static final String GO_TO_REMEDY_MODIFICATION_RESULT = "go_to_remedy_modification_result";

    private Map<String, Command> commands = new HashMap<>();

    public CommandStorage() {
        commands.put(GO_TO_DEFAULT, new GoToDefault());
//        commands.put(GO_TO_AUTHORIZATION, new GoToAutorization());
        commands.put(GO_TO_REGISTRATION, new GoToRegistration());
        commands.put(CHANGE_LANGUAGE, new ChangeLanguage());
        commands.put(AUTHORIZATION, new UserAuthorization());
        commands.put(REGISTRATION, new UserRegistration());
        commands.put(USER_INFO_AFTER_REGISTRATION, new UserInfoAfterRegistration());
        commands.put(AUTHORIZED_USER_MAIN_PAGE, new GoToAuthorizedMain());
        commands.put(SIGN_OUT, new UserSignOut());
        commands.put(GO_TO_CLIENT_MAIN, new GoToClientMain());
        commands.put(GO_TO_PHARMACIST_MAIN, new GoToPharmacistMain());
        commands.put(GO_TO_REMEDY, new GoToRemedy());
        commands.put(FIND_REMEDY, new FindRemedy());
        commands.put(GO_TO_REMEDY_ADMINISTRATION, new GoToRemedyAdministration());
        commands.put(ADD_REMEDY, new AddRemedy());
        commands.put(GO_TO_REMEDY_MODIFICATION_RESULT, new GoToRemedyModificationResult());
    }

    public Command getCommand(String command) {
        return commands.get(command);
    }

}

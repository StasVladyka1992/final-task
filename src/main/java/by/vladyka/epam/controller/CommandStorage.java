package by.vladyka.epam.controller;

import by.vladyka.epam.controller.impl.lang.ChangeLanguage;
import by.vladyka.epam.controller.impl.pharmacist.*;
import by.vladyka.epam.controller.impl.user.*;

import java.util.HashMap;
import java.util.Map;

public class CommandStorage {

    private static final String COMMAND_VALUE_GO_TO_DEFAULT = "go_to_default";
    private static final String COMMAND_VALUE_GO_TO_AUTHORIZATION = "go_to_authorization";
    private static final String COMMAND_VALUE_GO_TO_REGISTRATION = "go_to_registration";
    private static final String COMMAND_VALUE_CHANGE_LANGUAGE = "change_language";
    private static final String COMMAND_VALUE_AUTHORIZATION = "authorization";
    private static final String COMMAND_VALUE_REGISTRATION = "registration";
    private static final String COMMAND_VALUE_USER_INFO_AFTER_REGISTRATION = "user_info_after_registration";
    private static final String COMMAND_VALUE_GO_TO_AUTHORIZED_USER_MAIN_PAGE = "go_to_authorized_user_main_page";
    private static final String COMMAND_VALUE_SIGN_OUT = "sign_out";
    private static final String COMMAND_VALUE_GO_TO_REMEDY = "go_to_remedy";
    private static final String COMMAND_VALUE_FIND_REMEDY = "find_remedy";
    private static final String COMMAND_VALUE_GO_TO_REMEDY_ADMINISTRATION = "go_to_remedy_administration";
    private static final String COMMAND_VALUE_ADD_REMEDY = "add_remedy";
    private static final String COMMAND_VALUE_DELETE_REMEDY = "delete_remedy";
    private static final String COMMAND_VALUE_UPDATE_REMEDY = "update_remedy";
    private static final String COMMAND_VALUE_GO_TO_UPDATE_REMEDY = "go_to_update_remedy";
    private static final String COMMAND_VALUE_GO_TO_UPDATE_REMEDY_QUANTITY = "go_to_update_remedy_quantity";

    private Map<String, Command> commands = new HashMap<>();

    public CommandStorage() {
        commands.put(COMMAND_VALUE_GO_TO_DEFAULT, new GoToDefault());
        commands.put(COMMAND_VALUE_GO_TO_AUTHORIZATION, new GoToAutorization());
        commands.put(COMMAND_VALUE_GO_TO_REGISTRATION, new GoToRegistration());
        commands.put(COMMAND_VALUE_CHANGE_LANGUAGE, new ChangeLanguage());
        commands.put(COMMAND_VALUE_AUTHORIZATION, new UserAuthorization());
        commands.put(COMMAND_VALUE_REGISTRATION, new UserRegistration());
        commands.put(COMMAND_VALUE_USER_INFO_AFTER_REGISTRATION, new UserInfoAfterRegistration());
        commands.put(COMMAND_VALUE_GO_TO_AUTHORIZED_USER_MAIN_PAGE, new GoToAuthorizedMain());
        commands.put(COMMAND_VALUE_SIGN_OUT, new UserSignOut());
        commands.put(COMMAND_VALUE_GO_TO_REMEDY, new GoToRemedy());
        commands.put(COMMAND_VALUE_FIND_REMEDY, new FindRemedy());
        commands.put(COMMAND_VALUE_GO_TO_REMEDY_ADMINISTRATION, new GoToRemedyAdministration());
        commands.put(COMMAND_VALUE_ADD_REMEDY, new AddRemedy());
        commands.put(COMMAND_VALUE_DELETE_REMEDY, new DeleteRemedy());
        commands.put(COMMAND_VALUE_UPDATE_REMEDY, new UpdateRemedy());
        commands.put(COMMAND_VALUE_GO_TO_UPDATE_REMEDY, new GoToUpdateRemedy());
        commands.put(COMMAND_VALUE_GO_TO_UPDATE_REMEDY_QUANTITY, new GoToUpdateRemedyQuantity());
    }

    public Command getCommand(String command) {
        return commands.get(command);
    }

}

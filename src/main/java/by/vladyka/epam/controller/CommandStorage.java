package by.vladyka.epam.controller;

import by.vladyka.epam.controller.impl.client.*;
import by.vladyka.epam.controller.impl.doctor.*;
import by.vladyka.epam.controller.impl.lang.ChangeLanguage;
import by.vladyka.epam.controller.impl.pharmacist.*;
import by.vladyka.epam.controller.impl.user.*;

import java.util.HashMap;
import java.util.Map;

public final class CommandStorage {
    private static final String COMMAND_VALUE_GO_TO_DEFAULT = "go_to_default";
    private static final String COMMAND_VALUE_GO_TO_AUTHORIZATION = "go_to_authorization";
    private static final String COMMAND_VALUE_GO_TO_REGISTRATION = "go_to_registration";
    private static final String COMMAND_VALUE_CHANGE_LANGUAGE = "change_language";
    private static final String COMMAND_VALUE_AUTHORIZATION = "authorization";
    private static final String COMMAND_VALUE_REGISTRATION = "registration";
    private static final String COMMAND_VALUE_USER_INFO_AFTER_REGISTRATION = "user_info_after_registration";
    private static final String COMMAND_VALUE_GO_TO_AUTHORIZED_USER_MAIN = "go_to_authorized_user_main_page";
    private static final String COMMAND_VALUE_SIGN_OUT = "sign_out";
    private static final String COMMAND_VALUE_GO_TO_SEARCHING = "go_to_remedy";
    private static final String COMMAND_VALUE_FIND_STORAGE_POSITION = "find_storage_position";
    private static final String COMMAND_VALUE_GO_TO_REMEDY_ADMINISTRATION = "go_to_remedy_administration";
    private static final String COMMAND_VALUE_ADD_REMEDY = "add_remedy";
    private static final String COMMAND_VALUE_DELETE_REMEDY = "delete_remedy";
    private static final String COMMAND_VALUE_UPDATE_REMEDY = "update_remedy";
    private static final String COMMAND_VALUE_GO_TO_UPDATE_REMEDY = "go_to_update_remedy";
    private static final String COMMAND_VALUE_SET_STORAGE_QUANTITY = "set_storage_quantity";
    private static final String COMMAND_VALUE_ADD_TO_STORAGE = "add_to_storage";
    private static final String COMMAND_VALUE_ADD_TO_BASKET = "add_to_basket";
    private static final String COMMAND_VALUE_ASK_FOR_RECEIPT = "ask_for_receipt";
    private static final String COMMAND_VALUE_GO_TO_CLIENT_SEARCHING_PAGE = "go_to_client_searching_page";
    private static final String COMMAND_VALUE_GO_TO_PRESCRIPTION_APPLICATION_PAGE = "go_to_prescription_application";
    private static final String COMMAND_VALUE_SHOW_UNHANDLED_APPLICATIONS = "show_unhandled_applications";
    private static final String COMMAND_VALUE_WRITE_PRESCRIPTION = "write_prescription";
    private static final String COMMAND_VALUE_REJECT = "reject_application";
    private static final String COMMAND_VALUE_SHOW_WRITTEN_PRESCRIPTIONS = "show_written_prescriptions";
    private static final String COMMAND_VALUE_SHOW_REJECTED_APPLICATIONS = "show_rejected_applications";
    private static final String COMMAND_VALUE_GO_TO_REJECTION_LIST = "go_to_rejection_list";
    private static final String COMMAND_VALUE_GO_TO_PRESCRIPTION_LIST = "go_to_prescription_list";
    private static final String COMMAND_VALUE_GO_TO_BASKET = "go_to_basket";
    private static final String COMMAND_VALUE_UPDATE_PERSONAL_INFO = "update_personal_info";
    private static final String COMMAND_VALUE_GO_TO_PERSONAL_INFO = "go_to_personal_info";
    private static final String COMMAND_VALUE_SHOW_REMEDY_INFO = "show_remedy_info";
    private static final String COMMAND_VALUE_DELETE_FROM_BASKET = "delete_from_basket";
    private static final String COMMAND_VALUE_CHANGE_PURCHASING_QUANTITY = "change_purchasing_quantity";
    private static final String COMMAND_VALUE_CLEAN_BASKET = "clean_basket";
    private static final String COMMAND_VALUE_BUY = "buy";
    private static final String COMMAND_VALUE_GO_TO_ORDER_STATUS = "go_to_order_status";
    private static final String COMMAND_VALUE_GO_TO_PURCHASE_ADMINISTRATION = "go_to_purchase_administration";
    private static final String COMMAND_VALUE_SHOW_PRESCRIPTION_APPLICATION_LIST = "show_prescription_application_list";
    private static final String COMMAND_VALUE_GO_TO_PRESCRIPTION_APPLICATION_LIST = "go_to_prescription_application_list";
    private static final String COMMAND_VALUE_SHOW_CLIENT_WRITTEN_PRESCRIPTION_LIST = "show_client_written_prescription_list";
    private static final String COMMAND_VALUE_GO_TO_CLIENT_WRITTEN_PRESCRIPTION_LIST = "go_to_client_written_prescription_list";
    private static final String COMMAND_VALUE_SHOW_CLIENT_REJECTED_APPLICATION_LIST = "show_client_rejected_application_list";
    private static final String COMMAND_VALUE_GO_TO_CLIENT_REJECTED_APPLICATION_LIST = "go_to_client_rejected_application_list";
    private static final String COMMAND_VALUE_SHOW_UNHANDLED_ORDER_LIST = "show_unhandled_order_list";
    private static final String COMMAND_VALUE_SHOW_CLIENT_ORDER = "show_client_order";
    private static final String COMMAND_VALUE_GO_TO_CLIENT_ORDER = "go_to_client_order";
    private static final String COMMAND_VALUE_GO_TO_HANDLED_ORDER_LIST = "go_to_handled_order_list";
    private static final String COMMAND_VALUE_SHOW_HANDLED_ORDER_LIST = "show_handled_order_list";
    private static final String COMMAND_VALUE_CONFIRM_ORDER = "confirm_order";
    private static final String COMMAND_VALUE_REJECT_ORDER = "reject_order";
    private static final String COMMAND_VALUE_SHOW_CLIENT_UNHANDLED_ORDERS = "show_client_unhandled_orders";
    private static final String COMMAND_VALUE_GO_TO_CLIENT_UNHANDLED_ORDER_LIST = "go_to_client_unhandled_order_list";
    private static final String COMMAND_VALUE_SHOW_CLIENT_HANDLED_ORDERS = "show_client_handled_orders";
    private static final String COMMAND_VALUE_GO_TO_CLIENT_HANDLED_ORDER_LIST = "go_to_client_handled_order_list";

    private Map<String, Command> commands = new HashMap<>();

    public CommandStorage() {
        commands.put(COMMAND_VALUE_GO_TO_DEFAULT, new GoToDefault());
        commands.put(COMMAND_VALUE_GO_TO_AUTHORIZATION, new GoToAutorization());
        commands.put(COMMAND_VALUE_GO_TO_REGISTRATION, new GoToRegistration());
        commands.put(COMMAND_VALUE_CHANGE_LANGUAGE, new ChangeLanguage());
        commands.put(COMMAND_VALUE_AUTHORIZATION, new UserAuthorization());
        commands.put(COMMAND_VALUE_REGISTRATION, new UserRegistration());
        commands.put(COMMAND_VALUE_USER_INFO_AFTER_REGISTRATION, new UserInfoAfterRegistration());
        commands.put(COMMAND_VALUE_GO_TO_AUTHORIZED_USER_MAIN, new GoToAuthorizedMain());
        commands.put(COMMAND_VALUE_SIGN_OUT, new UserSignOut());
        commands.put(COMMAND_VALUE_GO_TO_SEARCHING, new GoToSearchingPage());
        commands.put(COMMAND_VALUE_FIND_STORAGE_POSITION, new FindStoragePosition());
        commands.put(COMMAND_VALUE_GO_TO_REMEDY_ADMINISTRATION, new GoToRemedyAdministration());
        commands.put(COMMAND_VALUE_ADD_REMEDY, new AddRemedy());
        commands.put(COMMAND_VALUE_DELETE_REMEDY, new DeleteRemedy());
        commands.put(COMMAND_VALUE_UPDATE_REMEDY, new UpdateRemedy());
        commands.put(COMMAND_VALUE_GO_TO_UPDATE_REMEDY, new GoToUpdateRemedy());
        commands.put(COMMAND_VALUE_SET_STORAGE_QUANTITY, new SetStorageQuantity());
        commands.put(COMMAND_VALUE_ADD_TO_STORAGE, new AddToStorage());
        commands.put(COMMAND_VALUE_ASK_FOR_RECEIPT, new AskForPrescription());
        commands.put(COMMAND_VALUE_ADD_TO_BASKET, new AddToBasket());
        commands.put(COMMAND_VALUE_GO_TO_CLIENT_SEARCHING_PAGE, new GoToClientSearchingPage());
        commands.put(COMMAND_VALUE_GO_TO_PRESCRIPTION_APPLICATION_PAGE, new GoToPrescriptionApplication());
        commands.put(COMMAND_VALUE_SHOW_UNHANDLED_APPLICATIONS, new ShowUnhandledApplication());
        commands.put(COMMAND_VALUE_WRITE_PRESCRIPTION, new WritePrescription());
        commands.put(COMMAND_VALUE_REJECT, new RejectApplication());
        commands.put(COMMAND_VALUE_SHOW_WRITTEN_PRESCRIPTIONS, new ShowWrittenPrescription());
        commands.put(COMMAND_VALUE_SHOW_REJECTED_APPLICATIONS, new ShowRejectedApplication());
        commands.put(COMMAND_VALUE_GO_TO_REJECTION_LIST, new GoToRejectedList());
        commands.put(COMMAND_VALUE_GO_TO_PRESCRIPTION_LIST, new GoToPrescriptionList());
        commands.put(COMMAND_VALUE_GO_TO_BASKET, new GoToBasket());
        commands.put(COMMAND_VALUE_UPDATE_PERSONAL_INFO, new UpdatePersonalInfo());
        commands.put(COMMAND_VALUE_GO_TO_PERSONAL_INFO, new GoToPersonalInfo());
        commands.put(COMMAND_VALUE_SHOW_REMEDY_INFO, new ShowRemedyInfo());
        commands.put(COMMAND_VALUE_DELETE_FROM_BASKET, new DeleteFromBasket());
        commands.put(COMMAND_VALUE_CHANGE_PURCHASING_QUANTITY, new ChangeQuantity());
        commands.put(COMMAND_VALUE_CLEAN_BASKET, new CleanBasket());
        commands.put(COMMAND_VALUE_BUY, new Buy());
        commands.put(COMMAND_VALUE_GO_TO_ORDER_STATUS, new GoToOrderStatus());
        commands.put(COMMAND_VALUE_GO_TO_PURCHASE_ADMINISTRATION, new GoToPurchaseAdministration());
        commands.put(COMMAND_VALUE_SHOW_PRESCRIPTION_APPLICATION_LIST, new ShowPrescriptionApplicationList());
        commands.put(COMMAND_VALUE_GO_TO_PRESCRIPTION_APPLICATION_LIST, new GoToPrescriptionApplicationList());
        commands.put(COMMAND_VALUE_SHOW_CLIENT_WRITTEN_PRESCRIPTION_LIST, new ShowClientWrittenPrescriptionList());
        commands.put(COMMAND_VALUE_SHOW_CLIENT_REJECTED_APPLICATION_LIST, new ShowRejectedApplicationList());
        commands.put(COMMAND_VALUE_GO_TO_CLIENT_WRITTEN_PRESCRIPTION_LIST, new GoToClientWrittenPrescriptionList());
        commands.put(COMMAND_VALUE_GO_TO_CLIENT_REJECTED_APPLICATION_LIST, new GoToClientRejectedApplicationList());
        commands.put(COMMAND_VALUE_SHOW_UNHANDLED_ORDER_LIST, new ShowUnhandledOrderList());
        commands.put(COMMAND_VALUE_SHOW_CLIENT_ORDER, new ShowClientOrder());
        commands.put(COMMAND_VALUE_GO_TO_CLIENT_ORDER, new GoToClientOrder());
        commands.put(COMMAND_VALUE_SHOW_HANDLED_ORDER_LIST, new ShowHandledOrderList());
        commands.put(COMMAND_VALUE_GO_TO_HANDLED_ORDER_LIST, new GoToHandledOrderList());
        commands.put(COMMAND_VALUE_CONFIRM_ORDER, new ConfirmOrder());
        commands.put(COMMAND_VALUE_REJECT_ORDER, new RejectOrder());
        commands.put(COMMAND_VALUE_SHOW_CLIENT_UNHANDLED_ORDERS, new ShowUnhandledClientOrderList());
        commands.put(COMMAND_VALUE_GO_TO_CLIENT_UNHANDLED_ORDER_LIST, new GoToClientUnhandledOrderList());
        commands.put(COMMAND_VALUE_SHOW_CLIENT_HANDLED_ORDERS, new ShowHandledClientOrderList());
        commands.put(COMMAND_VALUE_GO_TO_CLIENT_HANDLED_ORDER_LIST, new GoToClientHandledOrderList());
    }

    public Command getCommand(String command) {
        return commands.get(command);
    }

}

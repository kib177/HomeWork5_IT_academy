package by.HomeWork.service.factory;

import by.HomeWork.service.AuthService;
import by.HomeWork.service.MessageService;
import by.HomeWork.service.RegistrationService;
import by.HomeWork.service.StatisticsService;
import by.HomeWork.service.api.IAuthService;
import by.HomeWork.service.api.IMessageService;
import by.HomeWork.service.api.IRegistrationService;
import by.HomeWork.service.api.IStatisticsService;

public class ServiceFactory {
    private static final IStatisticsService statisticsService = new StatisticsService();
    private static final IAuthService authService = new AuthService();
    private static final IMessageService messageService = new MessageService();
    private static final IRegistrationService userService = new RegistrationService();

    public static IAuthService getAuthService() {
        return authService;
    }
    public static IMessageService getMessageService() {
        return messageService;
    }
    public static IStatisticsService getStatisticsService() {
        return statisticsService;
    }
    public static IRegistrationService getRegistrationService() {
        return userService;
    }
}

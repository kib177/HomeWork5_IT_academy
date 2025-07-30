package by.IT_academy.HomeWork.service;

import by.IT_academy.HomeWork.repository.RepositoryFactory;
import by.IT_academy.HomeWork.service.api.IAuthService;
import by.IT_academy.HomeWork.service.api.IMessageService;
import by.IT_academy.HomeWork.service.api.IRegistrationService;
import by.IT_academy.HomeWork.service.api.IStatisticsService;

public class ServiceFactory {
    private static final IStatisticsService statisticsService = new StatisticsService(
            RepositoryFactory.getMessageRepository(),
            RepositoryFactory.getUserRepository());
    private static final IAuthService authService = new AuthService(
            RepositoryFactory.getUserRepository());
    private static final IMessageService messageService = new MessageService(
            RepositoryFactory.getMessageRepository(),
            RepositoryFactory.getUserRepository());
    private static final IRegistrationService userService = new RegistrationService(
            RepositoryFactory.getUserRepository());

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

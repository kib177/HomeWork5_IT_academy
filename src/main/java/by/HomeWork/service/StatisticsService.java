package by.HomeWork.service;

import by.HomeWork.service.api.IStatisticsService;
import by.HomeWork.storage.MessageRepository;
import by.HomeWork.storage.UserRepository;
import jakarta.servlet.ServletContext;

import java.util.Map;
/**
 * Сервис для получения статистики по пользователям и сообщениям.
 * Реализует интерфейс {@link IStatisticsService}.
 * Предоставляет данные о количестве зарегистрированных пользователей, сообщений и активных сессий.
 */
public class StatisticsService implements IStatisticsService {

    @Override
    public Map<String, Integer> getStatistics(ServletContext context) {
        int totalUsers = UserRepository.getInstUserRep().getAll().size();
        int totalMessages = MessageRepository.getInstMsgRepository().getAll().size();
        int activeUsers = (int) context.getAttribute("activeUsersCount");

        return Map.of(
                "totalUsers", totalUsers,
                "totalMessages", totalMessages,
                "activeUsers", activeUsers
        );
    }
}

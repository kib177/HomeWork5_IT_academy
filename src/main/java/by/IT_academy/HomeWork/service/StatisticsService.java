package by.IT_academy.HomeWork.service;

import by.IT_academy.HomeWork.repository.api.IUserRepository;
import by.IT_academy.HomeWork.service.api.IStatisticsService;
import by.IT_academy.HomeWork.repository.MessageRepository;
import by.IT_academy.HomeWork.repository.UserRepository;
import by.IT_academy.HomeWork.repository.api.IMessageRepository;

import java.util.Map;
/**
 * Сервис для получения статистики по пользователям и сообщениям.
 * Реализует интерфейс {@link IStatisticsService}.
 * Предоставляет данные о количестве зарегистрированных пользователей, сообщений и активных сессий.
 */
public class StatisticsService implements IStatisticsService {
    private final IMessageRepository messageRepository;
    private final IUserRepository userRepository;

    public StatisticsService(IMessageRepository messageRepository,
                             IUserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Map<String, Integer> getStatistics(int activeUsers) {

        return Map.of(
                "totalUsers", userRepository.getAll().size(),
                "totalMessages", messageRepository.getAll().size(),
                "activeUsers", activeUsers
        );
    }
}

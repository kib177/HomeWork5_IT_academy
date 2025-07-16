package by.HomeWork.service;

import by.HomeWork.storage.MessageRepository;
import by.HomeWork.storage.UserRepository;
import jakarta.servlet.ServletContext;
import java.util.Map;

public class StatisticsService {
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

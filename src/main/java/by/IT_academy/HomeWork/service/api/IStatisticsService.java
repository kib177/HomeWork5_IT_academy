package by.IT_academy.HomeWork.service.api;

import java.util.Map;

public interface IStatisticsService {
    Map<String, Integer> getStatistics(int activeUsers);
}

package by.HomeWork.service.api;

import jakarta.servlet.ServletContext;

import java.util.Map;

public interface IStatisticsService {
    Map<String, Integer> getStatistics(ServletContext context);
}

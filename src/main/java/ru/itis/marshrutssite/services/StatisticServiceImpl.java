package ru.itis.marshrutssite.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.itis.marshrutssite.models.User;
@Service
public class StatisticServiceImpl implements StatisticService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void logInfoAboutUser(User user) {
        logger.info(user.toString());
    }
}

package ru.itis.marshrutssite.Schedulers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.marshrutssite.models.User;
import ru.itis.marshrutssite.repositories.UsersRepository;
import ru.itis.marshrutssite.services.StatisticService;

import java.util.List;


@Configuration
@EnableScheduling
@Slf4j
public class UserScheduler {
    @Autowired
    private StatisticService statisticService;

    @Autowired
    private UsersRepository usersRepository;

    @Transactional
    @Scheduled(cron = "0 0 10 20 * ?")
    public void run() {
        List<User> users = usersRepository.findAll();
        for (User user : users) {
            statisticService.logInfoAboutUser(user);
        }
    }
}
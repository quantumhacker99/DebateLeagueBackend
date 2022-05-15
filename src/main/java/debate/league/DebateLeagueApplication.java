package debate.league;

import debate.league.controller.InvitationController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
@SpringBootApplication
public class DebateLeagueApplication {
	private static final Logger log_file = LogManager.getLogger(DebateLeagueApplication.class.getName());
	public static void main(String[] args)
	{
		log_file.info("Starting DebLeague");
		SpringApplication.run(DebateLeagueApplication.class, args);
	}

}

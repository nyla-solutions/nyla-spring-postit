package nyla.solutions.net.postit;

import org.apache.geode.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.gemfire.GemfireTemplate;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import nyla.solutions.core.util.settings.Settings;
import nyla.solutions.net.postit.exception.PostItException;
import nyla.solutions.spring.settings.EnvironmentSettings;

@RestController
@EnableAutoConfiguration
@ComponentScan
@PropertySource("classpath:application.yml")
//@PropertySource("application.yml")
@ImportResource(locations = {"classpath:cache-config.xml"})
@ConfigurationProperties
@EnableGemfireRepositories(basePackageClasses= {RecipientDAORepository.class})
@Configuration
public class PostItApp
{
	private static Settings settings = null;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
	  PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
	  YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
	  yaml.setResources(new ClassPathResource("application.yml"));
	  propertySourcesPlaceholderConfigurer.setProperties(yaml.getObject());
	  return propertySourcesPlaceholderConfigurer;
	}//------------------------------------------------
	
	@Bean
	@Autowired
	public GemfireTemplate getGemFireTemplate(Cache cache)
	{
		GemfireTemplate template = new  GemfireTemplate(cache.getRegion("/UserProfile"));
		return template;
	}//------------------------------------------------
	/**
	 * 
	 * @return the setting object
	 */
	@Bean
	public Settings getSettings()
	{
		if(settings == null)
		{
			settings = new EnvironmentSettings(new StandardEnvironment());
		}
		
		return settings;
	}//------------------------------------------------
	
	@RequestMapping("/postIt")
	@ResponseBody
	public void postIt()
	throws PostItException
	{
		
		this.postItService.post(null);
	}//--------------------------------------------------------
    

	/**
	 * @param pack
	 * @see nyla.solutions.net.postit.PostItMgr#sendIt(nyla.solutions.net.postit.Package)
	 */
	@PostMapping(path = "/sendIt", consumes = "application/json")
	public void sendIt(@RequestBody Package pack)
	throws Exception
	{
		postItMgr.sendIt(pack);
	}//------------------------------------------------
	
	public static void main(String[] args)
	{
		System.out.println(System.getProperty("java.class.path"));
		SpringApplication.run(PostItApp.class, args);
	}

	@Autowired
	private PostItMgr postItMgr;
	
	@Autowired
	private PostItService postItService;
}

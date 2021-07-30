package com.dfwcomputech.cpoint.service.acceptance.users;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

import javax.annotation.PostConstruct;

import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.dfwcomputech.cpoint.service.resource.CPointEndPoints;
import com.dfwcomputech.cpoint.service.resource.dto.UserDto;
import com.dfwcomputech.cpoint.service.util.HttpClient;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class UserHttpClient extends HttpClient<UserDto>{
    @LocalServerPort
    private int port;
    
    public UserHttpClient() {
    	super(CPointEndPoints.USERS,UserDto.class);
    }
    
    @PostConstruct
    public void init() {
    	setPort(port);
    }
}

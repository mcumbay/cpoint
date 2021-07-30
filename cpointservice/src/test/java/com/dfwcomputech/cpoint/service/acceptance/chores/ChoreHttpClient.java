package com.dfwcomputech.cpoint.service.acceptance.chores;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

import javax.annotation.PostConstruct;

import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.dfwcomputech.cpoint.service.resource.CPointEndPoints;
import com.dfwcomputech.cpoint.service.resource.dto.ChoreDto;
import com.dfwcomputech.cpoint.service.util.HttpClient;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class ChoreHttpClient extends HttpClient<ChoreDto>{
    @LocalServerPort
    private int port;
    
    public ChoreHttpClient() {
    	super(CPointEndPoints.CHORES,ChoreDto.class);
    }
    
    @PostConstruct
    public void init() {
    	setPort(port);
    }
}

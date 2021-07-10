package com.dfwcomputech.cpoint.service;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectPackages("com.dfwcomputech.cpoint.service")
@IncludeTags("UnitTest")
public class ServiceUnitTestSuite {

}
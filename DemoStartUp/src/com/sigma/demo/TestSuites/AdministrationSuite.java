package com.sigma.demo.TestSuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.sigma.demo.TestCases.AdministrationTest;
import com.sigma.demo.TestCases.ConnectPlusLoginTest;
import com.sigma.demo.TestCases.PolicyGuidelinesTest;
import com.sigma.demo.TestCases.PriorApprovalTest;
import com.sigma.demo.TestCases.ResourceCenterTest;
import com.sigma.demo.TestCases.TrainingCenterTest;

/**
 * Administration testsuite with Login
 * 
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ ConnectPlusLoginTest.class,
		ResourceCenterTest.class, PriorApprovalTest.class, TrainingCenterTest.class, PolicyGuidelinesTest.class})
public class AdministrationSuite {
	// Nothing to add as content
}
